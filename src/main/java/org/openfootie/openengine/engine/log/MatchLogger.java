package org.openfootie.openengine.engine.log;

import java.util.ArrayList;
import java.util.List;

public class MatchLogger {

    private List<Reportable> matchReport = new ArrayList<>();

    public void report(MatchLogRecord record) {
        matchReport.add(record);
    }

    public void info(MatchLogInfo matchLogInfo) {
        matchReport.add(matchLogInfo);
    }

    public void displayReport() {
        matchReport.forEach(reportable -> System.out.println(reportable.getMessage()));
    }
}
