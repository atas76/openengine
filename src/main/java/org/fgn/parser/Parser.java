package org.fgn.parser;

import java.util.List;

public class Parser {

    private List<String> tokens;

    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }

    public Statement parse() {
        return new Statement();
    }
}
