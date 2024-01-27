package org.rama.queryengine.logicalplan.expr.math;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class AddExpr extends MathExpr {

  public AddExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("add", "+", leftLogicalExpr, rightLogicalExpr);
  }

  public static AddExpr add(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new AddExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
