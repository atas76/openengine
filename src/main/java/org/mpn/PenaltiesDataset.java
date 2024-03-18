package org.mpn;

import java.util.List;

public class PenaltiesDataset extends Dataset {

    public PenaltiesDataset(List<ProcessUnit> data) {
        super(data);
    }

    public List<Statement> getGoalkeeperSavesList() {
        return listStateTransitionsByEndState(State.SAVE);
    }

    public List<Statement> getPostHits() {
        return listStateTransitionsByEndState(State.POST);
    }

    public List<Statement> getOffTargetShots() {
        return listStateTransitionsByEndState(State.OFF_TARGET);
    }
}
