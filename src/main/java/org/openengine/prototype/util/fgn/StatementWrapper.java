package org.openengine.prototype.util.fgn;

import java.util.ArrayList;
import java.util.List;

public class StatementWrapper {

    private List<Token> tokens = new ArrayList<>();

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return this.tokens;
    }
}
