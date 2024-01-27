package org.rama.queryengine.datatypes;


import org.apache.arrow.vector.BigIntVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.Float4Vector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.SmallIntVector;
import org.apache.arrow.vector.TinyIntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.types.pojo.ArrowType;

public class ArrowFieldVector implements ColumnVector {

  private final FieldVector fieldVector;

  public ArrowFieldVector(FieldVector fieldVector) {
    this.fieldVector = fieldVector;
  }

  @Override
  public ArrowType getType() {
    return switch (fieldVector) {
      case BitVector valueVectors -> ArrowTypes.BooleanType;
      case TinyIntVector valueVectors -> ArrowTypes.Int8Type;
      case SmallIntVector valueVectors -> ArrowTypes.Int16Type;
      case IntVector valueVectors -> ArrowTypes.Int32Type;
      case BigIntVector valueVectors -> ArrowTypes.Int64Type;
      case Float4Vector valueVectors -> ArrowTypes.FloatType;
      case Float8Vector valueVectors -> ArrowTypes.DoubleType;
      case VarCharVector valueVectors -> ArrowTypes.StringType;
      case null, default -> throw new IllegalArgumentException("Field is not of a suitable type.");
    };
  }

  @Override
  public Object getValue(int index) {
    if (fieldVector.isNull(index)) {
      return null;
    }

    switch (fieldVector) {
      case BitVector valueVectors -> {
        return valueVectors.get(index) == 1;
      }
      case TinyIntVector valueVectors -> {
        return valueVectors.get(index);
      }
      case SmallIntVector valueVectors -> {
        return valueVectors.get(index);
      }
      case IntVector valueVectors -> {
        return valueVectors.get(index);
      }
      case BigIntVector valueVectors -> {
        return valueVectors.get(index);
      }
      case Float4Vector valueVectors -> {
        return valueVectors.get(index);
      }
      case Float8Vector valueVectors -> {
        return valueVectors.get(index);
      }
      case VarCharVector valueVectors -> {
        byte[] bytes = valueVectors.get(index);
        if (bytes == null) {
          return null;
        } else {
          return new String(bytes);
        }
      }
      default -> throw new IllegalArgumentException("Field is not of a suitable type.");
    }
  }

  @Override
  public int size() {
    return fieldVector.getValueCount();
  }
}
