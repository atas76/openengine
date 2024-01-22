package org.openengine.abstractmodel;

public class Tactic {

    private final TacticalPosition [] positions = new TacticalPosition[10];
    private Double [][] weightLayout = new Double[5][7];

    public Tactic(TacticalPosition [] positions) {
        for (int i = 0; i < 10; i++) {
            this.positions[i] = positions[i];
        }
    }
}
