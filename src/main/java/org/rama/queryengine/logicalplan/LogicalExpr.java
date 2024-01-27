package org.rama.queryengine.logicalplan;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;

public interface LogicalExpr {

  /**
   * Return meta-data about the value that will be produced by this expression when evaluated
   * against a particular input.
   */
  Field toField(LogicalPlan logicalPlan) throws SQLException;
}
