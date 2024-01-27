package org.rama.queryengine.logicalplan.expr;

import java.sql.SQLException;
import java.util.Optional;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

/**
 * Logical expression representing a reference to a column by name.
 */
public class ColumnExpr implements LogicalExpr {

  private final String name;

  public ColumnExpr(String name) {
    this.name = name;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) throws SQLException {
    Optional<Field> f = logicalPlan.schema().getFields().stream()
        .filter(field -> field.getName().equals(name)).findFirst();
    if (f.isEmpty()) {
      throw new SQLException("No column named " + name + "exists in given schema.");
    }

    return f.get();
  }

  @Override
  public String toString() {
    return "#" + name;
  }

  public static ColumnExpr col(String name) {
    return new ColumnExpr(name);
  }
}
