package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class SumExpr extends AggregateExpr {

  public SumExpr(LogicalExpr logicalExpr) {
    super("SUM", logicalExpr);
  }

  public static SumExpr sum(LogicalExpr logicalExpr) {
    return new SumExpr(logicalExpr);
  }
}
