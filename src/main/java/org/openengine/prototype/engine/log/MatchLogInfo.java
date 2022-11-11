package org.openengine.prototype.engine.log;

public class MatchLogInfo implements Reportable {

    public enum MatchInfoType {

        HT("Half time"), FT("Full time");

        private String description;

        MatchInfoType(String description) {
            this.description = description;
        }
    }
    private MatchInfoType type;

    public String getMessage() {
        return this.type.description;
    }

    public MatchLogInfo(MatchInfoType type) {
        this.type = type;
    }
}
