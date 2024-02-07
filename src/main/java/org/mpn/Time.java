package org.mpn;

public record Time(int minutes, int seconds) {
    public int getAbsoluteTime() {
        return minutes * 60 + seconds;
    }

    @Override
    public String toString() {
        return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }
}
