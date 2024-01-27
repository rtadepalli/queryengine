package org.rama.queryengine.logicalplan;

import org.rama.queryengine.datatypes.Field;

public class SumExpr extends AggregateExpr {

  public SumExpr(LogicalExpr logicalExpr) {
    super("SUM", logicalExpr);
  }

  public static SumExpr sum(LogicalExpr logicalExpr) {
    return new SumExpr(logicalExpr);
  }
}
