package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class CountExpr extends AggregateExpr {

  public CountExpr(LogicalExpr logicalExpr) {
    super("COUNT", logicalExpr);
  }

  public static CountExpr count(LogicalExpr logicalExpr) {
    return new CountExpr(logicalExpr);
  }
}
