package org.rama.queryengine.logicalplan;

import java.util.List;
import java.util.stream.IntStream;
import org.rama.queryengine.datatypes.Schema;

/**
 * A logical plan represents a data transformation or action that returns a relation (a set of
 * tuples).
 */
public interface LogicalPlan {

  /**
   * Returns the schema of the data that will be produced by this logical plan.
   */
  Schema schema();

  /**
   * Returns the children (inputs) of this logical plan. This method is used to enable use of the
   * visitor pattern to walk a query tree.
   */
  List<LogicalPlan> children();

  String pretty();

  default String format(LogicalPlan logicalPlan, int indent) {
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, indent).forEach(idx -> sb.append("\t"));
    sb.append(logicalPlan.toString()).append("\n");
    logicalPlan.children().forEach(child -> sb.append(format(child, indent + 2)));
    return sb.toString();
  }

  default String format(LogicalPlan logicalPlan) {
    return format(logicalPlan, 0);
  }
}
