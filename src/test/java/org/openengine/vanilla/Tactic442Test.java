package org.openengine.vanilla;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tactic442Test {

    private Tactic tactic = TacticsRepository.get(Tactics._4_4_2);

    @Test
    public void testReadWriteTactic() {
        List<Integer> playerPositionsIndices = tactic.getPlayerPositionsIndices();

        assertEquals(10, tactic.getPlayerPositionsIndices().size());
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
    public void testAdjacentPlayersPositions_D_R() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(0);

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

    @Test
    public void testAdjacentPlayersPositions_D_CR() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(2);

        assertEquals(5, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(1));
        assertTrue(adjacentPlayersPositions.containsKey(5));
        assertTrue(adjacentPlayersPositions.containsKey(15));
        assertTrue(adjacentPlayersPositions.containsKey(17));
        assertTrue(adjacentPlayersPositions.containsKey(31));
        assertEquals(0, adjacentPlayersPositions.get(1).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(1).horizontalDistance());
        assertEquals(0, adjacentPlayersPositions.get(5).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(5).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(15).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(15).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(17).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(17).horizontalDistance());
        assertEquals(4, adjacentPlayersPositions.get(31).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(31).horizontalDistance());
    }

    @Test
    public void testAdjacentPlayersPositions_D_CL() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(4);

        assertEquals(5, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(3));
        assertTrue(adjacentPlayersPositions.containsKey(7));
        assertTrue(adjacentPlayersPositions.containsKey(19));
        assertTrue(adjacentPlayersPositions.containsKey(21));
        assertTrue(adjacentPlayersPositions.containsKey(33));
        assertEquals(0, adjacentPlayersPositions.get(3).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(3).horizontalDistance());
        assertEquals(0, adjacentPlayersPositions.get(7).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(7).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(19).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(19).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(21).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(21).horizontalDistance());
        assertEquals(4, adjacentPlayersPositions.get(33).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(33).horizontalDistance());
    }

    @Test
    public void testAdjacentPlayersPositions_D_L() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(6);

        assertEquals(4, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(5));
        assertTrue(adjacentPlayersPositions.containsKey(19));
        assertTrue(adjacentPlayersPositions.containsKey(21));
        assertTrue(adjacentPlayersPositions.containsKey(33));
        assertEquals(0, adjacentPlayersPositions.get(5).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(5).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(19).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(19).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(21).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(21).horizontalDistance());
        assertEquals(4, adjacentPlayersPositions.get(33).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(33).horizontalDistance());
    }

    @Test
    public void testAdjacentPlayersPositions_M_R() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(14);

        assertEquals(2, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(17));
        assertTrue(adjacentPlayersPositions.containsKey(31));
        assertEquals(0, adjacentPlayersPositions.get(17).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(17).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(31).verticalDistance());
        assertEquals(2, adjacentPlayersPositions.get(31).horizontalDistance());
    }

    @Test
    public void testAdjacentPlayersPositions_M_CR() {
        Map<Integer, Tactic.Distance> adjacentPlayersPositions = tactic.getAdjacentPlayersPositions(16);

        assertEquals(3, adjacentPlayersPositions.size());
        assertTrue(adjacentPlayersPositions.containsKey(15));
        assertTrue(adjacentPlayersPositions.containsKey(19));
        assertTrue(adjacentPlayersPositions.containsKey(31));
        assertEquals(0, adjacentPlayersPositions.get(15).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(15).horizontalDistance());
        assertEquals(0, adjacentPlayersPositions.get(19).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(19).horizontalDistance());
        assertEquals(2, adjacentPlayersPositions.get(31).verticalDistance());
        assertEquals(0, adjacentPlayersPositions.get(31).horizontalDistance());
    }
}
