package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class MinExpr extends AggregateExpr {

  public MinExpr(LogicalExpr logicalExpr) {
    super("MIN", logicalExpr);
  }

  public static MinExpr min(LogicalExpr logicalExpr) {
    return new MinExpr(logicalExpr);
  }
}
