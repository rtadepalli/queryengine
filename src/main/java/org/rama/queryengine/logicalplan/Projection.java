package org.rama.queryengine.logicalplan;

import java.sql.SQLException;
import java.util.List;
import org.rama.queryengine.datatypes.Schema;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class Projection implements LogicalPlan {

  public LogicalPlan logicalPlan;
  public List<LogicalExpr> logicalExprs;

  public Projection(LogicalPlan logicalPlan, List<LogicalExpr> logicalExprs) {
    this.logicalPlan = logicalPlan;
    this.logicalExprs = logicalExprs;
  }

  @Override
  public Schema schema() {
    return new Schema(this.logicalExprs.stream().map(expr -> {
      try {
        return expr.toField(logicalPlan);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }).toList());
  }

  @Override
  public List<LogicalPlan> children() {
    return List.of(logicalPlan);
  }

  @Override
  public String toString() {
    return String.format("Projection: %s", String.join(", ", this.logicalExprs.stream().map(LogicalExpr::toString).toList()));
  }
}
