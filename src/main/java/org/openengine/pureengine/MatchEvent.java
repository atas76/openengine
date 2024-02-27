package org.openengine.pureengine;

public class MatchEvent {

    private String teamName;
    private MatchEventType eventType;
    private int period;

    public MatchEvent(String teamName, MatchEventType eventType, int period) {
        this.teamName = teamName;
        this.eventType = eventType;
        this.period = period;
    }

}
