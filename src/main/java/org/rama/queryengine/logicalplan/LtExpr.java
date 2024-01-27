package org.rama.queryengine.logicalplan;

public class LtExpr extends BooleanBinaryExpr {

  public LtExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("lt", "<", leftLogicalExpr, rightLogicalExpr);
  }

  public static LtExpr lt(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new LtExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
