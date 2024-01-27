package org.rama.queryengine.logicalplan;

public class AndExpr extends BooleanBinaryExpr {

  public AndExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("and", "AND", leftLogicalExpr, rightLogicalExpr);
  }

  public static AndExpr and(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new AndExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
