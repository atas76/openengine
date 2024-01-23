package org.openengine.abstractmodel;

import java.util.Arrays;

public class Tactic {

    private final TacticalPosition [] positions = new TacticalPosition[10];
    private double [][] weightLayout = new double[5][7];

    public Tactic(TacticalPosition [] positions) {
        for (int i = 0; i < 10; i++) {
            this.positions[i] = positions[i];
            weightLayout[positions[i].getX()][positions[i].getY()] = 1.0;
        }
    }

    @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < weightLayout.length; i++) {
           for (int j = 0; j < weightLayout[i].length; j++) {
               sb.append(String.format("%.2f", weightLayout[i][j]));
               sb.append(" ");
           }
           sb.append("\n");
        }
        return sb.toString();
    }
}
