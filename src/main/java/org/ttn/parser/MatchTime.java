package org.ttn.parser;

public class MatchTime {

    public static int getTimeInSeconds(int minutes, int seconds) {
        return minutes * 60 + seconds;
    }
}
