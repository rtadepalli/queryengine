package org.rama.queryengine.logicalplan.expr.math;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class DivExpr extends MathExpr {

  public DivExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("div", "/", leftLogicalExpr, rightLogicalExpr);
  }

  public static DivExpr div(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new DivExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
