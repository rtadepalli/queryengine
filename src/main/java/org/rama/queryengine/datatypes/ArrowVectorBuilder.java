package org.rama.queryengine.datatypes;

import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.VarCharVector;

import java.nio.charset.StandardCharsets;

public class ArrowVectorBuilder {

  private final FieldVector fieldVector;

  public ArrowVectorBuilder(FieldVector fieldVector) {
    assert fieldVector != null;
    this.fieldVector = fieldVector;
  }

  public void set(int index, Object value) {
    if (value == null) {
      fieldVector.setNull(index);
      return;
    }

    switch (fieldVector) {
      case VarCharVector varCharVector -> {
        if (value instanceof byte[]) {
          varCharVector.set(index, (byte[]) value);
        } else if (value instanceof String) {
          varCharVector.set(index,
              value.toString().getBytes(StandardCharsets.UTF_8));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non bytes/String type into VarCharVector.");
        }
      }
      case TinyIntVector tinyIntVector -> {
        if (value instanceof Number) {
          tinyIntVector.set(index, ((Number) value).byteValue());
        } else if (value instanceof String) {
          tinyIntVector.set(index, Byte.parseByte((String) value));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non byte type into TinyIntVector.");
        }
      }
      case SmallIntVector smallIntVector -> {
        if (value instanceof Number) {
          smallIntVector.set(index, ((Number) value).shortValue());
        } else if (value instanceof String) {
          smallIntVector.set(index, Short.parseShort((String) value));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non short type into SmallIntVector.");
        }
      }
      case IntVector intVector -> {
        if (value instanceof Number) {
          intVector.set(index, ((Number) value).intValue());
        } else if (value instanceof String) {
          intVector.set(index, Integer.parseInt((String) value));
        } else {
          throw new IllegalArgumentException("Attempted to insert non int type into IntVector.");
        }
      }
      case BigIntVector bigIntVector -> {
        if (value instanceof Number) {
          bigIntVector.set(index, ((Number) value).longValue());
        } else if (value instanceof String) {
          bigIntVector.set(index, Long.parseLong((String) value));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non long type into BigIntVector.");
        }
      }
      case Float4Vector float4Vector -> {
        if (value instanceof Number) {
          float4Vector.set(index, ((Number) value).floatValue());
        } else if (value instanceof String) {
          float4Vector.set(index, Float.parseFloat((String) value));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non float type into Float4Vector.");
        }
      }
      case Float8Vector float8Vector -> {
        if (value instanceof Number) {
          float8Vector.set(index, ((Number) value).doubleValue());
        } else if (value instanceof String) {
          float8Vector.set(index, Double.parseDouble((String) value));
        } else {
          throw new IllegalArgumentException(
              "Attempted to insert non double type into Float8Vector.");
        }
      }
      case null, default -> throw new IllegalArgumentException(
          String.format("Received unexpected type of FieldVector: %s",
              fieldVector.getClass().getSimpleName()));
    }
  }

  public void setValueCount(int valueCount) {
    fieldVector.setValueCount(valueCount);
  }

  public ColumnVector build() {
    return new ArrowFieldVector(fieldVector);
  }
}
