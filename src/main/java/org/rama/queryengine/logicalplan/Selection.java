package org.rama.queryengine.logicalplan;

import java.util.List;
import org.rama.queryengine.datatypes.Schema;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;

public class Selection implements LogicalPlan {

  public final LogicalPlan input;
  public final LogicalExpr expr;

  public Selection(LogicalPlan input, LogicalExpr expr) {
    this.input = input;
    this.expr = expr;
  }

  @Override
  public Schema schema() {
    return input.schema();
  }

  @Override
  public List<LogicalPlan> children() {
    return List.of(input);
  }
}
