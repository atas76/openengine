package org.openengine.abstractmodel.util;

import org.junit.Test;

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
}
