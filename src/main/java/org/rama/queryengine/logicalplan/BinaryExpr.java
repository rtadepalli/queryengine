package org.rama.queryengine.logicalplan;

public abstract class BinaryExpr implements LogicalExpr {

  public final String name;
  public final String op;
  public final LogicalExpr leftLogicalExpr;
  public final LogicalExpr rightLogicalExpr;

  public BinaryExpr(String name, String op, LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    this.name = name;
    this.op = op;
    this.leftLogicalExpr = leftLogicalExpr;
    this.rightLogicalExpr = rightLogicalExpr;
  }

  @Override
  public String toString() {
    return String.format("%s %s %s", leftLogicalExpr, op, rightLogicalExpr);
  }
}
