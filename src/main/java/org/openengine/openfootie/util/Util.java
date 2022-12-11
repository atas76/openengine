package org.openengine.openfootie.util;

public class Util {

    public static String convertForTimer(int totalSeconds) {

        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }
}
