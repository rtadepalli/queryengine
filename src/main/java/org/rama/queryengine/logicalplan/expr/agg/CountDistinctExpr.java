package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class CountDistinctExpr extends AggregateExpr {

  public CountDistinctExpr(LogicalExpr logicalExpr) {
    super("COUNT DISTINCT", logicalExpr);
  }

  public static CountDistinctExpr countDistinct(LogicalExpr logicalExpr) {
    return new CountDistinctExpr(logicalExpr);
  }
}
