package org.fgn.parser;

import java.util.Objects;

public class MatchTime {

    private int minutes;
    private int seconds;
    private final int totalSeconds;

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    public MatchTime() {
        this.minutes = 0;
        this.seconds = 0;
        this.totalSeconds = 0;
    }

    public MatchTime(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.totalSeconds = this.minutes * 60 + this.seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchTime)) return false;
        MatchTime matchTime = (MatchTime) o;
        return minutes == matchTime.minutes &&
                seconds == matchTime.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, seconds);
    }
}
