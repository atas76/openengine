package org.openengine.vanilla;

import java.util.Optional;

public class ActionOutcome {

    private Event event;
    private boolean possessionChange = false;
    private Player possessionPlayer;

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setPossessionChange(boolean possessionChange) {
        this.possessionChange = possessionChange;
    }

    public void setPossessionPlayer(Player player) {
        this.possessionPlayer = player;
    }

    public Optional<Event> getEvent() {
        return Optional.ofNullable(this.event);
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
                "event = " + (event != null ? event.getType().name() : "no event") +
                ", possessionChange = " + possessionChange +
                ", possessionPlayer = " + (possessionPlayer != null ? possessionPlayer.getName() : "none") +
                '}';
    }
}
