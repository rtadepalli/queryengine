package org.rama.queryengine.datatypes;

import org.apache.arrow.vector.types.pojo.ArrowType;

public interface ColumnVector {

  ArrowType getType();

  Object getValue(int index);

  int size();
}
