package org.rama.queryengine.logicalplan.expr.math;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;
import org.rama.queryengine.logicalplan.expr.BinaryExpr;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;

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
