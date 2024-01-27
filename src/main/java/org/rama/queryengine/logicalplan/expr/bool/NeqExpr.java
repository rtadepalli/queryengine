package org.rama.queryengine.logicalplan.expr.bool;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class NeqExpr extends BooleanBinaryExpr {

  public NeqExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("neq", "!=", leftLogicalExpr, rightLogicalExpr);
  }

  public static NeqExpr neq(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new NeqExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
