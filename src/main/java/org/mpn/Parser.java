package org.mpn;

import org.mpn.exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private Lexan lexan = new Lexan();
    private List<String> tokens = new ArrayList<>();
    private int index;

    public Statement parse(String line) throws Exception {

        tokens = lexan.scan(line);

        index = 0;
        String teamKey = getTeamKey();
        expect(":");
        Time startTime = getTime();
        Time endTime = null;
        if (tokens.contains("=>")) {
            expect("=>");
            endTime = getTime();
        }
        State initialState = getInitialState();
        expect("->");
        State endState = getEndState();

        return new Statement(teamKey, startTime, endTime, initialState, endState);
    }

    private String getTeamKey() {
        return tokens.get(index++);
    }

    private void expect(String token) throws SyntaxErrorException {
        if (!token.equals(tokens.get(index++))) {
            throw new SyntaxErrorException(token, index - 1);
        }
    }

    private Time getTime() throws Exception {
        int minutes = Integer.parseInt(tokens.get(index++));
        expect(":");
        int seconds = Integer.parseInt(tokens.get(index++));
        return new Time(minutes, seconds);
    }

    private State getInitialState() {
        return State.createFromName(tokens.get(index++));
    }

    private State getEndState() {
        return State.createFromName(tokens.get(index++));
    }
}
