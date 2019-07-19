package org.openfootie.openengine.engine.log;

import org.openfootie.openengine.engine.Action;
import org.openfootie.openengine.engine.Coordinates;

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
