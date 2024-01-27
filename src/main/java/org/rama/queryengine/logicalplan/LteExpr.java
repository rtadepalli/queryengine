package org.rama.queryengine.logicalplan;

public class LteExpr extends BooleanBinaryExpr {

  public LteExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("lte", "<=", leftLogicalExpr, rightLogicalExpr);
  }

  public static LteExpr lte(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new LteExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
