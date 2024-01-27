package org.rama.queryengine.datatypes;

import org.apache.arrow.vector.types.pojo.ArrowType;

public class LiteralValueVector implements ColumnVector {

  private final ArrowType arrowType;
  private final Object value;
  private final int size;

  public LiteralValueVector(ArrowType arrowType, Object value, int size) {
    this.arrowType = arrowType;
    this.value = value;
    this.size = size;
  }

  @Override
  public ArrowType getType() {
    return arrowType;
  }

  @Override
  public Object getValue(int index) {
    if (index < 0 || index >= size) {
      return new IndexOutOfBoundsException("Requesting value outside of vector's boundaries.");
    }

    return value;
  }

  @Override
  public int size() {
    return size;
  }
}
