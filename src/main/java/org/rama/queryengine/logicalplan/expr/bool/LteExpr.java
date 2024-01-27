package org.rama.queryengine.logicalplan.expr.bool;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class LteExpr extends BooleanBinaryExpr {

  public LteExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("lte", "<=", leftLogicalExpr, rightLogicalExpr);
  }

  public static LteExpr lte(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new LteExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
