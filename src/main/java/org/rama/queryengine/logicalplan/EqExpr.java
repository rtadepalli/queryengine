package org.rama.queryengine.logicalplan;

public class EqExpr extends BooleanBinaryExpr {

  public EqExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("eq", "=", leftLogicalExpr, rightLogicalExpr);
  }

  public static EqExpr eq(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new EqExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
