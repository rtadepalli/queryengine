package org.rama.queryengine.logicalplan.expr.bool;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class EqExpr extends BooleanBinaryExpr {

  public EqExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("eq", "=", leftLogicalExpr, rightLogicalExpr);
  }

  public static EqExpr eq(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new EqExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
