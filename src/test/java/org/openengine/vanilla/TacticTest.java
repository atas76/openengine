package org.openengine.vanilla;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TacticTest {

    @Test
    public void testReadWriteTactic() {
        Tactic tactic442 = new Tactic(new boolean [][]
                {
                        new boolean[] {true, false, true, false, true, false, true},
                        new boolean[] {false, false, false, false, false, false, false},
                        new boolean[] {true, false, true, false, true, false, true},
                        new boolean[] {false, false, false, false, false, false, false},
                        new boolean[] {false, false, true, false, true, false, false},
                }
        );

        List<Integer> playerPositionsIndices = tactic442.getPlayerPositionsIndices();

        assertEquals(10, tactic442.getPlayerPositionsIndices().size());
        assertEquals(1, playerPositionsIndices.get(0).intValue());
        assertEquals(3, playerPositionsIndices.get(1).intValue());
        assertEquals(5, playerPositionsIndices.get(2).intValue());
        assertEquals(7, playerPositionsIndices.get(3).intValue());
        assertEquals(15, playerPositionsIndices.get(4).intValue());
        assertEquals(17, playerPositionsIndices.get(5).intValue());
        assertEquals(19, playerPositionsIndices.get(6).intValue());
        assertEquals(21, playerPositionsIndices.get(7).intValue());
        assertEquals(31, playerPositionsIndices.get(8).intValue());
        assertEquals(33, playerPositionsIndices.get(9).intValue());
    }

    @Test
    public void testAdjacentPlayersPositions_442_D_R() {
        Tactic tactic442 = TacticsRepository.get(Tactics._4_4_2);

        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic442.getAdjacentPlayersPositions(0);

        assertEquals(4, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(3));
        assertTrue(adjacentPlayersPositions.containsKey(15));
        assertTrue(adjacentPlayersPositions.containsKey(17));
        assertTrue(adjacentPlayersPositions.containsKey(31));
        assertEquals(0, adjacentPlayersPositions.get(3).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(3).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(15).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(15).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(17).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(17).horizontalDistance());
        assertEquals(4, adjacentPlayersPositions.get(31).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(31).horizontalDistance());
    }
}
