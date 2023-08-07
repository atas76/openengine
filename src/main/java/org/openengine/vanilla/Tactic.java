package org.openengine.vanilla;

import java.util.ArrayList;
import java.util.List;

public class Tactic {

    private final int X_SIZE = 5;
    private final int Y_SIZE = 7;

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
}
