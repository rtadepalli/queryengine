package org.rama.queryengine.datatypes;

import java.util.List;

public class RecordBatch {

  private final Schema schema;
  private final List<ColumnVector> fields;

  public RecordBatch(Schema schema, List<ColumnVector> fields) {
    this.schema = schema;
    this.fields = fields;
  }

  public int rowCount() {
    return fields.getFirst().size();
  }

  public int columnCount() {
    return fields.size();
  }

  public ColumnVector field(int index) {
    return fields.get(index);
  }

  public List<ColumnVector> getFields() {
    return fields;
  }

  // TODO: Copy the toCsv() method from the Kotlin implementation.
}
