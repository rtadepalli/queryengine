package org.rama.queryengine.logicalplan;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;

public abstract class MathExpr extends BinaryExpr {

  public MathExpr(String name, String op, LogicalExpr leftLogicalExpr, LogicalExpr rightLogicalExpr) {
    super(name, op, leftLogicalExpr, rightLogicalExpr);
  }

  // Default to the type of the first operand.
  @Override
  public Field toField(LogicalPlan logicalPlan) throws SQLException {
    return new Field(name, leftLogicalExpr.toField(logicalPlan).getDataType());
  }
}
