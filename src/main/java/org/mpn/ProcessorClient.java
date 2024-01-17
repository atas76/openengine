package org.mpn;

import java.nio.file.Paths;
import java.util.List;

public class ProcessorClient {

    private static String datasource = "src/main/resources/data/mpn/cl_v2.mpn";
    private static Processor processor = new Processor();

    public static void main(String[] args) {
        List<ProcessUnit> dataset = processor.process(Paths.get(datasource));

        System.out.println("Raw dataset size: " + dataset.size());
    }
}
