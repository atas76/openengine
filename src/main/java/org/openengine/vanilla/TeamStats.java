package org.openengine.vanilla;

public class TeamStats {

    private int shotsAtGoal = 0;
    private int attackingTouches = 0;

    public void addShotAtGoal() {
        ++shotsAtGoal;
    }

    public void addAttackingTouch() {
        ++attackingTouches;
    }

    public int getShotsAtGoal() {
        return shotsAtGoal;
    }

    public int getAttackingTouches() {
        return attackingTouches;
    }
}
