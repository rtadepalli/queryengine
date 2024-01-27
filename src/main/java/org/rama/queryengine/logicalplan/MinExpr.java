package org.rama.queryengine.logicalplan;

public class MinExpr extends AggregateExpr {

  public MinExpr(LogicalExpr logicalExpr) {
    super("MIN", logicalExpr);
  }

  public static MinExpr min(LogicalExpr logicalExpr) {
    return new MinExpr(logicalExpr);
  }
}
