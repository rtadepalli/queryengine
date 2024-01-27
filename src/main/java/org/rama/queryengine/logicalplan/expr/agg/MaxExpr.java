package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class MaxExpr extends AggregateExpr {

  public MaxExpr(LogicalExpr logicalExpr) {
    super("MAX", logicalExpr);
  }

  public static MaxExpr max(LogicalExpr logicalExpr) {
    return new MaxExpr(logicalExpr);
  }
}
