package org.mpn.clients;

import org.mpn.PenaltiesDataset;
import org.mpn.ProcessUnit;
import org.mpn.Processor;

import java.nio.file.Paths;
import java.util.List;

public class PenaltiesDataProcessorClient {

    private static String datasource = "src/main/resources/data/mpn/penalties.mpn";

    private static Processor processor = new Processor();

    public static void main(String[] args) {
        List<ProcessUnit> data = processor.process(Paths.get(datasource));
        PenaltiesDataset dataset = new PenaltiesDataset(data);

        System.out.println("Raw dataset size: " + dataset.size());
    }
}
