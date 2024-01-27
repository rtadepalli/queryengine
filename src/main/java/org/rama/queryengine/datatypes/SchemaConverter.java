package org.rama.queryengine.datatypes;

public final class SchemaConverter {

  public static Schema fromArrow(org.apache.arrow.vector.types.pojo.Schema arrowSchema) {
    return new Schema(
        arrowSchema.getFields().stream().map(field -> new Field(field.getName(), field.getType()))
            .toList());
  }

  public static org.apache.arrow.vector.types.pojo.Schema toArrow(Schema schema) {
    return new org.apache.arrow.vector.types.pojo.Schema(
        schema.getFields().stream().map(Field::toArrow).toList());
  }
}
