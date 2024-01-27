package org.rama.queryengine.datasource;

import java.io.IOException;
import java.util.List;
import org.rama.queryengine.datatypes.RecordBatch;
import org.rama.queryengine.datatypes.Schema;

public interface DataSource {

  Schema schema();

  Iterable<RecordBatch> scan(List<String> projection) throws IOException;
}
