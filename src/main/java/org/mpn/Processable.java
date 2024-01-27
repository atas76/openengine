package org.mpn;

import java.nio.file.Paths;

public interface Processable {

    String datasource = "src/main/resources/data/mpn/cl_v2.mpn";

    static Dataset loadData() {
        return loadData(datasource);
    }
    static Dataset loadData(String datasource) {
        return new Dataset(new Processor().process(Paths.get(datasource)));
    }
}
