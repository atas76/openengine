package org.openengine.vanilla;

import java.util.ArrayList;
import java.util.List;

public class Tactic {
    private static final int Y_SIZE = 7;

    private boolean [][] positionalMatrix;

    public Tactic(boolean [][] positionalMatrix) {
        this.positionalMatrix = positionalMatrix;
    }

    public List<Integer> getPlayerPositionsIndices() {

        List<Integer> playerPositions = new ArrayList<>();

        for (int i = 0; i < positionalMatrix.length; i++) {
            for (int j = 0; j < positionalMatrix[i].length; j++) {
                if (positionalMatrix[i][j])
                    playerPositions.add(i * Y_SIZE + j + 1);
            }
        }

        return playerPositions;
    }

    private static int getXFromIndex(int index) {
        return index / Y_SIZE;
    }

    private static int getYFromIndex(int index) {
        return index % Y_SIZE;
    }

    public static double computeDistanceUnitFactor(int sourceIndex, int targetIndex) {

        int xSource = (sourceIndex >= 0 ? getXFromIndex(sourceIndex) : 0);
        int ySource = (sourceIndex >= 0 ? getYFromIndex(sourceIndex) : getYFromIndex(targetIndex));
        int xTarget = getXFromIndex(targetIndex);
        int yTarget = getYFromIndex(targetIndex);

        int verticalDistance = xTarget - xSource;
        double verticalDistanceFactor = State.DISTANCE_UNIT_FACTORS.get(verticalDistance);

        int horizontalDistance = yTarget - ySource;
        double horizontalDistanceFactor = switch(horizontalDistance) {
            case 0 -> 1.0;
            case 1 -> State.HORIZONTAL_DISTANCE_UNIT_FACTOR;
            default -> State.HORIZONTAL_DISTANCE_UNIT_FACTOR * 1.5;
        };

        return verticalDistanceFactor * horizontalDistanceFactor;
    }
}
