package org.rama.queryengine.logicalplan.expr.math;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class MulExpr extends MathExpr {

  public MulExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("mul", "*", leftLogicalExpr, rightLogicalExpr);
  }

  public static MulExpr mul(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new MulExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
