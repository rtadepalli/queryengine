package org.rama.queryengine.logicalplan;

import java.util.List;
import org.rama.queryengine.datatypes.Schema;

public class Limit implements LogicalPlan {

  public LogicalPlan logicalPlan;
  public int limit;

  public Limit(LogicalPlan logicalPlan, int limit) {
    this.logicalPlan = logicalPlan;
    this.limit = limit;
  }

  @Override
  public Schema schema() {
    return logicalPlan.schema();
  }


  @Override
  public List<LogicalPlan> children() {
    return List.of(logicalPlan);
  }

  @Override
  public String toString() {
    return String.format("Limit: %d", limit);
  }
}
