package org.openengine.vanilla;

public class Event {

    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
