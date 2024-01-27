package org.rama.queryengine.logicalplan;

public class CountDistinctExpr extends AggregateExpr {

  public CountDistinctExpr(LogicalExpr logicalExpr) {
    super("COUNT DISTINCT", logicalExpr);
  }

  public static CountDistinctExpr countDistinct(LogicalExpr logicalExpr) {
    return new CountDistinctExpr(logicalExpr);
  }
}
