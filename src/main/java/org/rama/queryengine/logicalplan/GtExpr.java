package org.rama.queryengine.logicalplan;

public class GtExpr extends BooleanBinaryExpr {

  public GtExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("gt", ">", leftLogicalExpr, rightLogicalExpr);
  }

  public static GtExpr gt(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new GtExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
