package org.rama.queryengine.logicalplan;

public class CountExpr extends AggregateExpr {

  public CountExpr(LogicalExpr logicalExpr) {
    super("COUNT", logicalExpr);
  }

  public static CountExpr count(LogicalExpr logicalExpr) {
    return new CountExpr(logicalExpr);
  }
}
