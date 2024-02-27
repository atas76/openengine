package org.openengine.pureengine;

import java.util.ArrayList;
import java.util.List;

public class Match {

    private List<MatchEvent> events = new ArrayList<>();

    public void addEvent(MatchEvent matchEvent) {
        events.add(matchEvent);
    }
}
