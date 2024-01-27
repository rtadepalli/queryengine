package org.rama.queryengine.logicalplan;

public class OrExpr extends BooleanBinaryExpr {

  public OrExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("or", "OR", leftLogicalExpr, rightLogicalExpr);
  }

  public static OrExpr or(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new OrExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
