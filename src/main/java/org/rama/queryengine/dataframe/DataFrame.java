package org.rama.queryengine.dataframe;

import java.util.List;
import org.rama.queryengine.datatypes.Schema;
import org.rama.queryengine.logicalplan.LogicalPlan;
import org.rama.queryengine.logicalplan.expr.LogicalExpr;
import org.rama.queryengine.logicalplan.expr.agg.AggregateExpr;

public interface DataFrame {

  DataFrame project(List<LogicalExpr> exprs);

  DataFrame filter(LogicalExpr expr);

  DataFrame aggregate(List<LogicalExpr> groupBy, List<AggregateExpr> aggregates);

  Schema schema();

  LogicalPlan logicalPlan();
}
