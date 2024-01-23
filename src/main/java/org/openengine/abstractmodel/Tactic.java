package org.openengine.abstractmodel;

public class Tactic {

    private final TacticalPosition [] positions = new TacticalPosition[10];
    private boolean [][] tacticalLayout = new boolean[5][7];
    private TacticalRegionHeatmap [][] weightLayout = new TacticalRegionHeatmap[5][7];

    public Tactic(TacticalPosition [] positions) {
        for (int i = 0; i < 10; i++) {
            this.positions[i] = positions[i];
            tacticalLayout[positions[i].getX()][positions[i].getY()] = true;
        }
    }

    @Override
    public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < weightLayout.length; i++) {
           for (int j = 0; j < weightLayout[i].length; j++) {
               sb.append(tacticalLayout[i][j] ? "x" : "_");
               // sb.append(String.format("%.2f", tacticalLayout[i][j]));
               sb.append(" ");
           }
           sb.append("\n");
        }
        return sb.toString();
    }

    // TODO commenting-out solution of the wrong problem; to be removed after feature completed
    /*
    private void assignPositionalWeights() {
        for (int i = 0; i < this.positions.length; i++) {
            propagatePositionalWeight(positions[i].getX(), positions[i].getY());
        }
    }

    private static final double WEIGHT_ATTENUATION = 0.5;

    private void propagatePositionalWeight(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (isXInBound(i) && isYInBound(j)) {
                    if (!isWeightAssigned(i, j)) {
                        weightLayout[i][j] = WEIGHT_ATTENUATION * weightLayout[x][y];
                        propagatePositionalWeight(i, j);
                    }
                }
            }
        }
    }

    private boolean isWeightAssigned(int x, int y) {
        return weightLayout[x][y] > 0;
    }

    private boolean isXInBound(int x) {
        return (x >= 0 && x < weightLayout.length);
    }

    private boolean isYInBound(int y) {
        return (y >= 0 && y < weightLayout[0].length);
    }
     */
}
