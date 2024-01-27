package org.rama.queryengine.logicalplan;

public class MulExpr extends MathExpr {

  public MulExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("mul", "*", leftLogicalExpr, rightLogicalExpr);
  }

  public static MulExpr mul(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new MulExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
