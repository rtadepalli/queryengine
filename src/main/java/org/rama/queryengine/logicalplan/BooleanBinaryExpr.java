package org.rama.queryengine.logicalplan;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;

public abstract class BooleanBinaryExpr extends BinaryExpr {

  public BooleanBinaryExpr(String name, String op, LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super(name, op, leftLogicalExpr, rightLogicalExpr);
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(name, ArrowTypes.BooleanType);
  }
}
