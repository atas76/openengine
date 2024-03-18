package org.mpn;

public class PenaltyDataProcessor extends Processor {

    @Override
    public ProcessUnit process(String line) throws Exception {
        return new Parser().parse(line);
    }
}
