package org.rama.queryengine.logicalplan.expr.bool;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class LtExpr extends BooleanBinaryExpr {

  public LtExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("lt", "<", leftLogicalExpr, rightLogicalExpr);
  }

  public static LtExpr lt(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new LtExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
