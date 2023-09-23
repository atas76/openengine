package org.mpn;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private List<String> tokens = new ArrayList<>();

    public Statement parse(String line) {
        tokens = getTokens(line);
        return new Statement(getTeamKey(), -1, -1, null, null);
    }

    private List<String> getTokens(String expression) {

        List<String> tokens = new ArrayList<>();
        int index = 0;
        if (Character.isAlphabetic(expression.charAt(0))) {
            while (Character.isAlphabetic(expression.charAt(index))) {
                ++index;
            }
            tokens.add(expression.substring(0, index));
        }
        if (index > 0 && index < expression.length()) tokens.addAll(getTokens(expression.substring(index)));
        return tokens;
    }

    private String getTeamKey() {
        return tokens.get(0);
    }
}
