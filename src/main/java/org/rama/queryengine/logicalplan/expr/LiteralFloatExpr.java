package org.rama.queryengine.logicalplan.expr;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

public class LiteralFloatExpr implements LogicalExpr {

  public final Float f;

  public LiteralFloatExpr(Float f) {
    if (f == null) {
      throw new IllegalStateException("Expected non-null float in LiteralFloatExpr.");
    }
    this.f = f;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(f.toString(), ArrowTypes.FloatType);
  }

  @Override
  public String toString() {
    return f.toString();
  }

  public static LiteralFloatExpr lit(Float f) {
    return new LiteralFloatExpr(f);
  }
}
