package org.rama.queryengine.logicalplan.expr.agg;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public abstract class AggregateExpr implements LogicalExpr {

  public String name;
  public LogicalExpr logicalExpr;

  public AggregateExpr(String name, LogicalExpr logicalExpr) {
    this.name = name;
    this.logicalExpr = logicalExpr;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) throws SQLException {
    return new Field(name, logicalExpr.toField(logicalPlan).getDataType());
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", name, logicalExpr);
  }
}
