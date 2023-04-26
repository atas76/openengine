package org.openengine.vanilla;

public class TeamStats {

    private int shotsAtGoal = 0;

    public void addShotAtGoal() {
        ++shotsAtGoal;
    }

    public int getShotsAtGoal() {
        return shotsAtGoal;
    }
}
