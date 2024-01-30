package org.openengine.abstractmodel.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TacticalPitchCalculationsTest {

    @Test
    public void testDistance() {
        Point a1 = new Point(2, 3);
        Point b1 = new Point(4, 6);
        Point a2 = new Point(1, 1);
        Point b2 = new Point(5, 7);
        Point a3 = new Point(0, 5);
        Point b3 = new Point(3, 2);

        assertEquals(4, TacticalPitchCalculations.getDistance(a1, b1));
        assertEquals(7, TacticalPitchCalculations.getDistance(a2, b2));
        assertEquals(4, TacticalPitchCalculations.getDistance(a3, b3));
    }

    @Test
    public void testMatrixAddition() {
        double[][] matrix1 = {
                {0.12, 0.15, 0.18, 0.20, 0.18, 0.15, 0.12},
                {0.15, 0.25, 0.30, 0.35, 0.30, 0.25, 0.15},
                {0.18, 0.30, 0.45, 0.50, 0.45, 0.30, 0.18},
                {0.15, 0.25, 0.30, 0.35, 0.30, 0.25, 0.15},
                {0.12, 0.15, 0.18, 0.20, 0.18, 0.15, 0.12}
        };

        double[][] matrix2 = {
                {0.10, 0.12, 0.14, 0.15, 0.14, 0.12, 0.10},
                {0.12, 0.18, 0.25, 0.28, 0.25, 0.18, 0.12},
                {0.14, 0.25, 0.30, 0.40, 0.30, 0.25, 0.14},
                {0.12, 0.18, 0.25, 0.28, 0.25, 0.18, 0.12},
                {0.10, 0.12, 0.14, 0.15, 0.14, 0.12, 0.10}
        };

        double[][] expectedSum = {
                {0.22, 0.27, 0.32, 0.35, 0.32, 0.27, 0.22},
                {0.27, 0.43, 0.55, 0.63, 0.55, 0.43, 0.27},
                {0.32, 0.55, 0.75, 0.90, 0.75, 0.55, 0.32},
                {0.27, 0.43, 0.55, 0.63, 0.55, 0.43, 0.27},
                {0.22, 0.27, 0.32, 0.35, 0.32, 0.27, 0.22}
        };

        double[][] result = TacticalPitchCalculations.add(matrix1, matrix2, 5, 7);

        assertArrayEquals(expectedSum, result);
    }
}
