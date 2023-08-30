package org.openengine.vanilla;

import java.util.*;

public class Tactic {
    public static final int X_SIZE = 5;
    public static final int Y_SIZE = 7;

    private static final int SHOOTING_RANGE = 2;

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

    public record Distance (int verticalDistance, int horizontalDistance) {}

    public Map<Integer, Distance> getAdjacentPlayersPositions(int index) {
        int x = getXFromIndex(index);
        int y = getYFromIndex(index);
        Map<Integer, Distance> retVal = new TreeMap<>();

        for (int j = y + 1; j < Y_SIZE; j++) {
            if (positionalMatrix[x][j]) {
                retVal.put(x * Y_SIZE + j + 1, new Distance(0, 0));
                break;
            }
        }
        for (int j = y - 1; j >= 0; j--) {
            if (positionalMatrix[x][j]) {
                retVal.put(x * Y_SIZE + j + 1, new Distance(0, 0));
                break;
            }
        }
        for (int i = x + 1; i < X_SIZE; i++) {
            if (y < Y_SIZE / 2) {
                for (int j = y; j <= Y_SIZE / 2; j++) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, j - y));
                    }
                }
                for (int j = y - 1; j >= 0; j--) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, 0));
                    }
                }
            } else if (y > Y_SIZE / 2) {
                for (int j = y + 1; j < Y_SIZE; j++) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, 0));
                    }
                }
                for (int j = y; j >= Y_SIZE / 2; j--) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, y - j));
                    }
                }
            } else {
                for (int j = y; j <= Y_SIZE / 2; j++) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, 0));
                    }
                }
                for (int j = y - 1; j >= 0; j--) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, 0));
                    }
                }
                for (int j = y + 1; j < Y_SIZE; j++) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, 0));
                    }
                }
                for (int j = y; j >= Y_SIZE / 2; j--) {
                    if (positionalMatrix[i][j]) {
                        retVal.put(i * Y_SIZE + j + 1, new Distance(i - x, y - j));
                    }
                }
            }
        }
        return retVal;
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

    public static boolean isWithinShootingRange(int position) {
        return position >= Tactic.X_SIZE - Tactic.SHOOTING_RANGE;
    }
}
