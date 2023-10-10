package org.mpn;

import org.mpn.exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexan lexan = new Lexan();
    private List<String> tokens = new ArrayList<>();

    public Statement parse(String line) throws Exception {
        tokens = lexan.scan(line);
        expect(":", 1);
        expect(":", 3);
        return new Statement(getTeamKey(), getMinutes(), getSeconds(), null, null);
    }

    private String getTeamKey() {
        return tokens.get(0);
    }

    private void expect(String token, int index) throws SyntaxErrorException {
        if (!token.equals(tokens.get(index))) {
            throw new SyntaxErrorException(token, index);
        }
    }

    private int getMinutes() {
        return Integer.parseInt(tokens.get(2));
    }

    private int getSeconds() {
        return Integer.parseInt(tokens.get(4));
    }
}
