package org.rama.queryengine.logicalplan;

public class GteExpr extends BooleanBinaryExpr {

  public GteExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("gte", ">=", leftLogicalExpr, rightLogicalExpr);
  }

  public static GteExpr gte(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new GteExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
