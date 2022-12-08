package org.openengine.openfootie;

import java.util.HashMap;
import java.util.Map;

public class MatchFlowMatrix {

    private Map<MatchDataElementType, MatchSequence> flowMatrixMap = new HashMap<>();

    public void addRow(MatchDataElementType type, MatchSequence sequence) {
        flowMatrixMap.put(type, sequence);
    }

    public MatchSequence getRow(MatchDataElementType type) {
        return flowMatrixMap.get(type);
    }

    public MatchSequence getMatchSequence(MatchDataElementType dataElementType) {
        return flowMatrixMap.get(dataElementType);
    }
}
