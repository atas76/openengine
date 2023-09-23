package org.mpn;

import java.util.ArrayList;
import java.util.List;

public class Lexan {

    public List<String> scan(String expression) {

        List<String> tokens = new ArrayList<>();
        int index = 0;
        if (Character.isAlphabetic(expression.charAt(0))) {
            while (Character.isAlphabetic(expression.charAt(index))) {
                ++index;
            }
            tokens.add(expression.substring(0, index));
        }
        if (index > 0 && index < expression.length()) tokens.addAll(scan(expression.substring(index)));
        return tokens;
    }
}
