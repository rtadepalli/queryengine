package org.rama.queryengine.logicalplan;

public class ModExpr extends MathExpr {

  public ModExpr(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super("mod", "%", leftLogicalExpr, rightLogicalExpr);
  }

  public static ModExpr mod(LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    return new ModExpr(leftLogicalExpr, rightLogicalExpr);
  }
}
