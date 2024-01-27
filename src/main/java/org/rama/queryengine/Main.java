package org.rama.queryengine;

import java.util.List;
import org.rama.queryengine.datasource.CsvDataSource;
import org.rama.queryengine.datasource.DataSource;
import org.rama.queryengine.datatypes.ColumnVector;
import org.rama.queryengine.datatypes.RecordBatch;

public class Main {
  public static void main(String... args) throws Exception {
    String filename = "/Users/ramasai/organizations-100.csv";
    DataSource csvDataSource = new CsvDataSource(filename, null, true, 20);
    Iterable<RecordBatch> iterable = csvDataSource.scan(List.of());

    for (RecordBatch batch : iterable) {
      System.out.println(batch.columnCount());
      for (ColumnVector fieldVector : batch.getFields()) {
        System.out.println(fieldVector.size());
      }
    }
  }
}
