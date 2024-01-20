package org.mpn;

public record Time(int minutes, int seconds) {
    public int getAbsoluteTime() {
        return minutes * 60 + seconds;
    }
}
