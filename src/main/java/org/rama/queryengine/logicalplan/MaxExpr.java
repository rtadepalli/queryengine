package org.rama.queryengine.logicalplan;

public class MaxExpr extends AggregateExpr {

  public MaxExpr(LogicalExpr logicalExpr) {
    super("MAX", logicalExpr);
  }

  public static MaxExpr max(LogicalExpr logicalExpr) {
    return new MaxExpr(logicalExpr);
  }
}
