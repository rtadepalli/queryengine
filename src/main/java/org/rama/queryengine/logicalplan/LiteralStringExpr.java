package org.rama.queryengine.logicalplan;

import org.rama.queryengine.datatypes.ArrowTypes;
import org.rama.queryengine.datatypes.Field;

/** Logical expression representing a literal string value. */
public class LiteralStringExpr implements LogicalExpr {

  private final String str;

  public LiteralStringExpr(String str) {
    if (str == null) {
      throw new IllegalStateException("Expected non-null str in LiteralStringExpr.");
    }
    this.str = str;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    return new Field(str, ArrowTypes.StringType);
  }

  @Override
  public String toString() {
    return "#" + str;
  }
}
