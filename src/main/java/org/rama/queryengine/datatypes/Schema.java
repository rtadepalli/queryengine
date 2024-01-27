package org.rama.queryengine.datatypes;

import java.util.ArrayList;
import java.util.List;

public class Schema {

  private final List<Field> fields;

  public Schema(List<Field> fields) {
    this.fields = fields;
  }

  public List<Field> getFields() {
    return fields;
  }

  public Schema project(List<Integer> indices) {
    return new Schema(indices.stream().map(fields::get).toList());
  }

  public Schema select(List<String> names) {
    List<Field> selectedFields = new ArrayList<>();

    names.forEach(name -> {
      List<Field> filteredFields = fields.stream().filter(field -> field.getName().equals(name))
          .toList();
      if (filteredFields.size() > 1) {
        throw new IllegalStateException(
            "Should not match more than one field name while selecting.");
      } else if (filteredFields.size() == 1) {
        selectedFields.add(filteredFields.getFirst());
      }
    });

    return new Schema(selectedFields);
  }
}
