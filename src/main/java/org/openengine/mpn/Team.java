package org.openengine.mpn;

import org.mpn.ActionChainsMap;
import org.mpn.Dataset;
import org.mpn.State;

public class Team {

    private String name;
    private ActionChainsMap actionChains;
    private int goalsScored;

    public Team(String name, ActionChainsMap actionChains) {
        this.name = name;
        this.actionChains = actionChains;
    }

    public void score() {
        this.goalsScored++;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public Dataset getActionsByState(State state) {
        return actionChains.getMappingsByState(state);
    }

    @Override
    public String toString() {
        return name;
    }

}
