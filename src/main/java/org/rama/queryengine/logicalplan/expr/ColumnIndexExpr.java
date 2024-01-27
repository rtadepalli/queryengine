package org.rama.queryengine.logicalplan.expr;

import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

/**
 * Logical expression representing a reference to a column by index.
 */
public class ColumnIndexExpr implements LogicalExpr {

  private final int columnIndex;

  public ColumnIndexExpr(int columnIndex) {
    this.columnIndex = columnIndex;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) {
    if (columnIndex < 0 || columnIndex >= logicalPlan.schema().getFields().size()) {
      throw new IllegalStateException("Requested column is bigger than the schema size.");
    }
    return logicalPlan.schema().getFields().get(columnIndex);
  }

  @Override
  public String toString() {
    return "#" + columnIndex;
  }

  public static ColumnIndexExpr colIdx(int columnIndex) {
    return new ColumnIndexExpr(columnIndex);
  }
}
