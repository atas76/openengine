package org.mpn;

import java.nio.file.Paths;

public interface Processable {

    String datasource = "src/main/resources/data/mpn/cl_v2.mpn";
    String penaltiesDatasource = "src/main/resources/data/mpn/penalties.mpn";

    static Dataset loadData() {
        return loadData(datasource, true);
    }

    static Dataset loadData(boolean preprocess) {
        return loadData(datasource, preprocess);
    }

    static PenaltiesDataset loadPenaltiesData() {
        return loadPenaltiesData(penaltiesDatasource);
    }
    static Dataset loadData(String datasource, boolean preprocess) {
        return new Dataset(new Processor().process(Paths.get(datasource)), preprocess);
    }

    static PenaltiesDataset loadPenaltiesData(String datasource) {
        return new PenaltiesDataset(new PenaltyDataProcessor().process(Paths.get(datasource)));
    }
}
