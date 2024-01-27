package org.rama.queryengine.logicalplan;

public class SubExpr extends MathExpr {

  public SubExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("sub", "-", leftLogicalExpr, rightLogicalExpr);
  }

  public static SubExpr sub(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new SubExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
