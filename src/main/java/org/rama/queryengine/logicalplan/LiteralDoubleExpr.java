package org.rama.queryengine.logicalplan;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;

public class LiteralDoubleExpr implements LogicalExpr {

  public final Double d;

  public LiteralDoubleExpr(Double d) {
    if (d == null) {
      throw new IllegalStateException("Expected non-null double in LiteralDoubleExpr.");
    }
    this.d = d;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(d.toString(), ArrowTypes.DoubleType);
  }

  @Override
  public String toString() {
    return d.toString();
  }
}
