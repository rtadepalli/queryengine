package org.rama.queryengine.logicalplan;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;

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
