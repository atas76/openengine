package org.openfootie.openengine.engine.log;

public class MatchLogger {

    public void info(MatchLogRecord record) {
        // TODO Writing to console by default for now
        System.out.println(record);
    }
}
