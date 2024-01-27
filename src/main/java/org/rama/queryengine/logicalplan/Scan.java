package org.rama.queryengine.logicalplan;

import java.util.List;
import org.rama.queryengine.datasource.DataSource;
import org.rama.queryengine.datatypes.Schema;

public class Scan implements LogicalPlan {

  public final String path;
  public final DataSource dataSource;
  public final List<String> projection;
  public final Schema schema;

  public Scan(String path, DataSource dataSource, List<String> projection) {
    this.path = path;
    this.dataSource = dataSource;
    this.projection = projection;
    this.schema = deriveSchema();
  }

  @Override
  public Schema schema() {
    return schema;
  }

  @Override
  public List<LogicalPlan> children() {
    return List.of();
  }

  @Override
  public String toString() {
    return projection.isEmpty() ? String.format("%s; projection=None", path)
        : String.format("%s; projection=%s", path, projection);
  }

  private Schema deriveSchema() {
    Schema schema = dataSource.schema();
    return projection.isEmpty() ? schema : schema.select(projection);
  }
}
