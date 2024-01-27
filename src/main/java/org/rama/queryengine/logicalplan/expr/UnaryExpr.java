package org.rama.queryengine.logicalplan.expr;

public abstract class UnaryExpr implements LogicalExpr {

  public final String name;
  public final String op;
  public final LogicalExpr logicalExpr;

  public UnaryExpr(String name, String op, LogicalExpr logicalExpr) {
    this.name = name;
    this.op = op;
    this.logicalExpr = logicalExpr;
  }

  @Override
  public String toString() {
    return String.format("%s %s", op, logicalExpr);
  }
}
