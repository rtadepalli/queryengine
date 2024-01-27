package org.rama.queryengine.logicalplan.expr;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

public class LiteralLongExpr implements LogicalExpr {

  public final Long l;

  public LiteralLongExpr(Long l) {
    if (l == null) {
      throw new IllegalStateException("Expected non-null long in LiteralLongExpr.");
    }
    this.l = l;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(l.toString(), ArrowTypes.Int64Type);
  }

  @Override
  public String toString() {
    return l.toString();
  }

  public static LiteralLongExpr lit(Long l) {
    return new LiteralLongExpr(l);
  }
}
