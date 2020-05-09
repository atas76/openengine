package org.fgn.domain;

import java.util.ArrayList;
import java.util.List;

public class EventSequence {

    private List<Event> events = new ArrayList<>();

    public void add(Event event) {
        events.add(event);
    }
}
