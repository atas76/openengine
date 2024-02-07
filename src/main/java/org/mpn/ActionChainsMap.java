package org.mpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionChainsMap {

    private Map<State, List<Statement>> map = new HashMap<>();

    public void addStateTransitionMapping(State state, Statement statement) {
        map.computeIfAbsent(state, k -> new ArrayList<>());
        map.get(state).add(statement);
    }

    public void displayStateTransitionMappings() {
        map.forEach(((state, statements) -> {
            System.out.println(state);
            statements.forEach(statement -> {
                System.out.println(statement.getStartTime() + ": " + statement.getDuration()
                        + " -> " + (!statement.isPossessionRetained() ? "!" : "") + statement.getEndState());
            });
            System.out.println();
        }));
    }

    public Dataset getMappingsByState(State state) {
        return new Dataset(map.get(state));
    }
}
