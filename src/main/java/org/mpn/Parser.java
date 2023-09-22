package org.mpn;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private List<String> tokens = new ArrayList<>();

    public Statement parse(String line) {
        tokens = new Lexan().getTokens(line);
        return new Statement(getTeamKey(), -1, -1, null, null);
    }

    private String getTeamKey() {
        return tokens.get(0);
    }
}
