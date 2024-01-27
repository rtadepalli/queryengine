package org.rama.queryengine.datasource;

import com.google.common.base.Suppliers;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.rama.queryengine.datatypes.ArrowFieldVector;
import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.ColumnVector;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.datatypes.RecordBatch;
import org.rama.queryengine.datatypes.Schema;
import org.rama.queryengine.datatypes.SchemaConverter;

public class CsvDataSource implements DataSource {

  private final String filename;
  private final Schema schema;
  private final boolean hasHeaders;
  private final int batchSize;

  public CsvDataSource(String filename, Schema schema, boolean hasHeaders, int batchSize) {
    this.filename = filename;
    this.schema = schema;
    this.hasHeaders = hasHeaders;
    this.batchSize = batchSize;
  }

  private CsvParser buildParser(CsvParserSettings settings) {
    return new CsvParser(settings);
  }

  private CsvParserSettings defaultParserSettings() {
    CsvParserSettings settings = new CsvParserSettings();
    settings.setDelimiterDetectionEnabled(true);
    settings.setLineSeparatorDetectionEnabled(true);
    settings.setSkipEmptyLines(true);
    settings.setAutoClosingEnabled(true);

    return settings;
  }

  private Schema inferSchema() throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      throw new FileNotFoundException("Supplied file name does not exist.");
    }

    CsvParser parser = buildParser(defaultParserSettings());
    try (FileInputStream stream = new FileInputStream(file)) {
      parser.beginParsing(stream);
      String[] headers = parser.parseNext();
      // String[] headers = (String[]) Arrays.stream(parser.getContext().parsedHeaders()).filter(Objects::nonNull).toArray();

      if (hasHeaders) {
        return new Schema(
            Arrays.stream(headers).map(header -> new Field(header, ArrowTypes.StringType))
                .toList());
      } else {
        return new Schema(IntStream.range(9, headers.length)
            .mapToObj(index -> new Field("field_" + index, ArrowTypes.StringType)).toList());
      }
    }
  }

  // TODO: Replace with lazy schema.
  @Override
  public Schema schema() {
    try {
      return schema != null ? schema : inferSchema();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Iterable<RecordBatch> scan(List<String> projection) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      throw new FileNotFoundException("Could not open file: " + filename);
    }

    Schema schemaToRead = !projection.isEmpty() ? schema().select(projection) : schema();
    CsvParserSettings settings = defaultParserSettings();
    if (!projection.isEmpty()) {
      settings.selectFields(projection.toArray(new String[]{}));
    }
    settings.setHeaderExtractionEnabled(hasHeaders);
    if (!hasHeaders) {
      settings.setHeaders((String[]) schemaToRead.getFields().stream().map(Field::getName).toArray());
    }

    CsvParser parser = buildParser(settings);
    parser.beginParsing(file);
    return new CsvIterable(schemaToRead, parser, batchSize);
  }

  private static class CsvIterable implements Iterable<RecordBatch> {

    private final Schema schema;
    private final CsvParser parser;
    private final int batchSize;

    CsvIterable(Schema schema, CsvParser parser, int batchSize) {
      this.schema = schema;
      this.parser = parser;
      this.batchSize = batchSize;
    }

    @Override
    public Iterator<RecordBatch> iterator() {
      return new CsvIterator(schema, parser, batchSize);
    }
  }

  private static class CsvIterator implements Iterator<RecordBatch> {

    private final Schema schema;
    private final CsvParser parser;
    private final int batchSize;
    private boolean started = false;
    private RecordBatch next = null;

    CsvIterator(Schema schema, CsvParser parser, int batchSize) {
      this.schema = schema;
      this.parser = parser;
      this.batchSize = batchSize;
    }

    @Override
    public boolean hasNext() {
      if (!started) {
        started = true;
        next = readNextBatch();
      }

      return next != null;
    }

    @Override
    public RecordBatch next() {
      if (!started) {
        hasNext();
      }

      RecordBatch response = next;
      next = readNextBatch();

      if (response == null) {
        throw new NoSuchElementException("Cannot read past the end of given file.");
      }

      return response;
    }

    private RecordBatch readNextBatch() {
      List<Record> rows = new ArrayList<>();
      Record record;

      while ((record = parser.parseNextRecord()) != null && rows.size() < batchSize) {
        rows.add(record);
      }
      if (rows.isEmpty()) {
        return null;
      }

      return createRecordBatch(rows);
    }

    private RecordBatch createRecordBatch(List<Record> rows) {
      VectorSchemaRoot root = VectorSchemaRoot.create(SchemaConverter.toArrow(schema), new RootAllocator(Long.MAX_VALUE));
      root.getFieldVectors().forEach(fieldVector -> fieldVector.setInitialCapacity(rows.size()));
      root.allocateNew();

      for (int index = 0; index < rows.size(); index++) {
        int finalIndex = index;
        root.getFieldVectors().forEach(fieldVector -> {
          fieldVector.setValueCount(rows.size());
          String value = rows.get(finalIndex).getValue(fieldVector.getName(), "").trim();
          switch (fieldVector) {
            case VarCharVector varCharVector -> {
              varCharVector.setSafe(finalIndex, value.getBytes(StandardCharsets.UTF_8));
            }
            case TinyIntVector tinyIntVector -> {
              if (value.isEmpty()) {
                tinyIntVector.setNull(finalIndex);
              } else {
                tinyIntVector.setSafe(finalIndex, Byte.parseByte(value));
              }
            } case SmallIntVector smallIntVector -> {
              if (value.isEmpty()) {
                smallIntVector.setNull(finalIndex);
              } else {
                smallIntVector.setSafe(finalIndex, Short.parseShort(value));
              }
            }
            case IntVector intVector -> {
              if (value.isEmpty()) {
                intVector.setNull(finalIndex);
              } else {
                intVector.setSafe(finalIndex, Integer.parseInt(value));
              }
            }
            case BigIntVector bigIntVector -> {
              if (value.isEmpty()) {
                bigIntVector.setNull(finalIndex);
              } else {
                bigIntVector.setSafe(finalIndex, Long.parseLong(value));
              }
            }
            case Float4Vector float4Vector -> {
              if (value.isEmpty()) {
                float4Vector.setNull(finalIndex);
              } else {
                float4Vector.setSafe(finalIndex, Float.parseFloat(value));
              }
            }
            case Float8Vector float8Vector -> {
              if (value.isEmpty()) {
                float8Vector.setNull(finalIndex);
              } else {
                float8Vector.setSafe(finalIndex, Double.parseDouble(value));
              }
            }
            default -> throw new IllegalStateException("No support for reading vector of type: " + fieldVector);
          }
        });
      }

      return new RecordBatch(schema, root.getFieldVectors().stream().map(ArrowFieldVector::new).collect(Collectors.toList()));
    }
  }
}
