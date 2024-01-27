package org.rama.queryengine.dataframe;

import java.util.List;
import org.rama.queryengine.datatypes.Schema;
import org.rama.queryengine.logicalplan.LogicalPlan;
import org.rama.queryengine.logicalplan.Projection;
import org.rama.queryengine.logicalplan.Selection;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;
import org.rama.queryengine.logicalplan.expr.agg.AggregateExpr;

public class DataFrameImpl implements DataFrame {

  private final LogicalPlan logicalPlan;

  public DataFrameImpl(LogicalPlan logicalPlan) {
    this.logicalPlan = logicalPlan;
  }

  @Override
  public DataFrame project(List<LogicalExpr> logicalExprs) {
    return new DataFrameImpl(new Projection(logicalPlan, logicalExprs));
  }

  @Override
  public DataFrame filter(LogicalExpr logicalExpr) {
    return new DataFrameImpl(new Selection(logicalPlan, logicalExpr));
  }

  public DataFrame aggregate(List<LogicalExpr> groupBy, List<AggregateExpr> aggreagates) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Schema schema() {
    return logicalPlan.schema();
  }

  @Override
  public LogicalPlan logicalPlan() {
    return logicalPlan;
  }
}
