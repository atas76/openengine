package org.openengine.prototype.engine.log;

import org.openengine.prototype.engine.Action;
import org.openengine.prototype.engine.Coordinates;

public class MatchLogRecord implements Reportable {

    private String teamName;
    private Coordinates ball;
    private Action action;
    private boolean goalScored;

    public MatchLogRecord(String teamName, Coordinates ball, Action action) {
        this.teamName = teamName;
        this.ball = ball;
        this.action = action;
    }

    public void setGoalScored() {
        this.goalScored = true;
    }

    public String getMessage() {
        return toString();
    }

    @Override
    public String toString() {

        StringBuilder currentOutput = new StringBuilder();

        currentOutput.append(teamName).append(": ").append(ball).append("->").append(action);

        if (this.goalScored) {
            currentOutput.append(" => Goal!");
        }

        return currentOutput.toString();
    }
}
