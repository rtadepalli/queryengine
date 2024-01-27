package org.rama.queryengine.logicalplan.expr.bool;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;
import org.rama.queryengine.logicalplan.expr.UnaryExpr;

public class NotExpr extends UnaryExpr {

  public NotExpr(LogicalExpr logicalExpr) {
    super("not", "NOT", logicalExpr);
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(name, ArrowTypes.BooleanType);
  }

  public static NotExpr not(LogicalExpr logicalExpr) {
    return new NotExpr(logicalExpr);
  }
}
