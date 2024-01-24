package org.openengine.abstractmodel.util;

public class TacticalPitchCalculations {

    public static int getDistance(Point a, Point b) {
        return (int) Math.round(Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2)));
    }
}
