package org.openengine.abstractmodel.util;

public class TacticalPitchCalculations {

    public static int getDistance(Point a, Point b) {
        return (int) Math.round(Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2)));
    }

    public static double [][] add(double[][] a, double[][] b, int width, int height) {

        double [][] result = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }
}
