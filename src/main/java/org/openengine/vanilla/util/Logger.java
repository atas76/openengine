package org.openengine.vanilla.util;

public class Logger {

    public static void log(String logEntry) {
        if (Flags.LOGGING) {
            System.out.println(logEntry);
        }
    }
}
