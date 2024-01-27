package org.rama.queryengine.logicalplan.expr;

import java.sql.SQLException;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

public class CastExpr implements LogicalExpr {

  public final LogicalExpr logicalExpr;
  public final ArrowType arrowType;

  public CastExpr(LogicalExpr logicalExpr, ArrowType arrowType) {
    this.logicalExpr = logicalExpr;
    this.arrowType = arrowType;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) throws SQLException {
    return new Field(logicalExpr.toField(logicalPlan).getName(), arrowType);
  }

  @Override
  public String toString() {
    return String.format("CAST (%s AS %s)", logicalExpr, arrowType);
  }

  public static CastExpr cast(LogicalExpr logicalExpr, ArrowType arrowType) {
    return new CastExpr(logicalExpr, arrowType);
  }
}
