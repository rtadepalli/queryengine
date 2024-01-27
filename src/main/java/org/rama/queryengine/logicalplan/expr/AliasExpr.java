package org.rama.queryengine.logicalplan.expr;

import java.sql.SQLException;
import org.rama.queryengine.datatypes.Field;
import org.rama.queryengine.logicalplan.LogicalPlan;

public class AliasExpr implements LogicalExpr {

  public final String alias;
  public final LogicalExpr logicalExpr;

  public AliasExpr(String alias, LogicalExpr logicalExpr) {
    this.alias = alias;
    this.logicalExpr = logicalExpr;
  }

  @Override
  public Field toField(LogicalPlan logicalPlan) throws SQLException {
    return new Field(alias, logicalExpr.toField(logicalPlan).getDataType());
  }

  @Override
  public String toString() {
    return String.format("(%s AS %s)", logicalExpr, alias);
  }

  public static AliasExpr alias(String alias, LogicalExpr logicalExpr) {
    return new AliasExpr(alias, logicalExpr);
  }
}
