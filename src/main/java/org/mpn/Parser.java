package org.mpn;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexan lexan = new Lexan();
    private List<String> tokens = new ArrayList<>();

    public Statement parse(String line) throws Exception {
        tokens = lexan.scan(line);
        return new Statement(getTeamKey(), -1, -1, null, null);
    }



    private String getTeamKey() {
        return tokens.get(0);
    }
}
