package org.rama.queryengine.logicalplan.expr;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

public interface LogicalExpr {

  /**
   * Return meta-data about the value that will be produced by this expression when evaluated
   * against a particular input.
   */
  Field toField(LogicalPlan logicalPlan) throws SQLException;
}
