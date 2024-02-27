package org.openengine.pureengine;

public class TeamStats {
    private int possession;

    public void addPossession() {
        this.possession++;
    }

    public int getPossession() {
        return this.possession;
    }
}
