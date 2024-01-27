package org.rama.queryengine.datatypes;

import java.util.List;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.FieldType;

public class Field {

  private final String name;
  private final ArrowType dataType;

  public Field(String name, ArrowType dataType) {
    this.name = name;
    this.dataType = dataType;
  }

  public String getName() {
    return name;
  }

  public ArrowType getDataType() {
    return dataType;
  }

  public org.apache.arrow.vector.types.pojo.Field toArrow() {
    FieldType fieldType = new FieldType(true, dataType, null);
    return new org.apache.arrow.vector.types.pojo.Field(name, fieldType, List.of());
  }
}
