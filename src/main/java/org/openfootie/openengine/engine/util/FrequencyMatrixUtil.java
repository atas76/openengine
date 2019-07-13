package org.openfootie.openengine.engine.util;

import java.util.List;
import java.util.Random;

public class FrequencyMatrixUtil {

    private static Random rnd = new Random();

    public static int getWeightedElement(List<Integer> matrix) {

        int total = matrix.stream().reduce(0, Integer::sum);
        int selected = rnd.nextInt(total);

        int currentValue = 0;
        int index = 0;

        for (Integer item: matrix) {
            currentValue += item;
            if (selected <= currentValue) {
                return index;
            }
            ++index;
        }
        return index;
     }
}
