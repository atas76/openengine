package org.openengine.vanilla;

import java.util.ArrayList;
import java.util.List;

public class ActionOutcome {

    private boolean possessionChange = false;
    private Player possessionPlayer;
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public List<Event> getEvents() {
        return this.events;
    }


    public void setPossessionChange(boolean possessionChange) {
        this.possessionChange = possessionChange;
    }

    public void setPossessionPlayer(Player player) {
        this.possessionPlayer = player;
    }

    public Player getPossessionPlayer() {
        return possessionPlayer;
    }

    public boolean isPossessionChange() {
        return possessionChange;
    }

    @Override
    public String toString() {
        return "ActionOutcome{" +
                "event = " + (!events.isEmpty() ? events.get(0).getType().name() : "no event") +
                ", possessionChange = " + possessionChange +
                ", possessionPlayer = " + (possessionPlayer != null ? possessionPlayer.getName() : "none") +
                '}';
    }
}
