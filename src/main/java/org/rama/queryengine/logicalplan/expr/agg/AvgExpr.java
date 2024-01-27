package org.rama.queryengine.logicalplan.expr.agg;

import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class AvgExpr extends AggregateExpr {

  public AvgExpr(LogicalExpr logicalExpr) {
    super("AVG", logicalExpr);
  }

  public static AvgExpr avg(LogicalExpr logicalExpr) {
    return new AvgExpr(logicalExpr);
  }
}
