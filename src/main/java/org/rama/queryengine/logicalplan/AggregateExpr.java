package org.rama.queryengine.logicalplan;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;

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
