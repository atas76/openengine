package org.openengine.abstractmodel;

import org.openengine.abstractmodel.util.Point;
import org.openengine.abstractmodel.util.TacticalPitchCalculations;

public class Tactic {

    private static final int PITCH_SIZE_X = 5;
    private static final int PITCH_SIZE_Y = 7;

    private final TacticalPosition [] positions = new TacticalPosition[10];
    private boolean [][] tacticalLayout = new boolean[PITCH_SIZE_X][PITCH_SIZE_Y];
    private TacticalRegionHeatmap [][] heatmapRepresentation = new TacticalRegionHeatmap[PITCH_SIZE_X][PITCH_SIZE_Y];

    private double [][] weightlayout = new double[PITCH_SIZE_X][PITCH_SIZE_Y];

    public Tactic(TacticalPosition [] positions) {
        for (int i = 0; i < 10; i++) {
            this.positions[i] = positions[i];
            tacticalLayout[positions[i].getX()][positions[i].getY()] = true;
            weightlayout =
                    TacticalPitchCalculations.add(weightlayout, calculateWeightLayoutByPosition(this.positions[i]),
                            PITCH_SIZE_X, PITCH_SIZE_Y);
        }
    }

    @Override
    public String toString() {
        return getTacticalLayoutRepresentation(this.tacticalLayout);
    }

    public static String getWeightLayoutRepresentationByPosition(TacticalPosition tacticalPosition) {
        return getWeightLayoutRepresentation(calculateWeightLayoutByPosition(tacticalPosition));
    }

    private static String getWeightLayoutRepresentation(double [][] weightLayout) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PITCH_SIZE_X; i++) {
            for (int j = 0; j < PITCH_SIZE_Y; j++) {
                sb.append(String.format("%.2f", weightLayout[i][j]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getWeightLayoutRepresentation() {
        return getWeightLayoutRepresentation(this.weightlayout);
    }

    private static String getTacticalLayoutRepresentation(boolean [][] tacticalLayout) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PITCH_SIZE_X; i++) {
            for (int j = 0; j < PITCH_SIZE_Y; j++) {
                sb.append(tacticalLayout[i][j] ? "x" : "_");
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static double [][] calculateWeightLayoutByPosition(TacticalPosition tacticalPosition) {
        double [][] weightLayout = new double[PITCH_SIZE_X][PITCH_SIZE_Y];

        weightLayout[tacticalPosition.getX()][tacticalPosition.getY()] = 1.0;

        Point referencePoint = new Point(tacticalPosition.getX(), tacticalPosition.getY());

        for (int i = 0; i < PITCH_SIZE_X; i++) {
            for (int j = 0; j < PITCH_SIZE_Y; j++) {
                int distance = TacticalPitchCalculations.getDistance(referencePoint, new Point(i, j));
                weightLayout[i][j] = 1 / Math.pow(2, distance);
            }
        }

        return weightLayout;
    }

    private static boolean [][] getIndividualPositionTacticalLayout(TacticalPosition tacticalPosition) {
        boolean [][] tacticalLayout = new boolean[PITCH_SIZE_X][PITCH_SIZE_Y];
        tacticalLayout[tacticalPosition.getX()][tacticalPosition.getY()] = true;
        return tacticalLayout;
    }

    public static String getTacticalLayoutByPosition(TacticalPosition tacticalPosition) {
        return getTacticalLayoutRepresentation(getIndividualPositionTacticalLayout(tacticalPosition));
    }
}
