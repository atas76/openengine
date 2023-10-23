package org.mpn;

import org.mpn.exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private Lexan lexan = new Lexan();
    private List<String> tokens = new ArrayList<>();
    private int index;

    private Map<String, String> argumentList = new HashMap<>();

    public Statement parse(String line) throws Exception {

        tokens = lexan.scan(line);

        // Optional elements
        PitchPosition initialPitchPosition = null;
        boolean keepPossession = true;

        index = 0;
        String teamKey = getTeamKey();
        expect(":");
        Time startTime = getTime();
        Time endTime = null;
        if (tokens.contains("=>")) {
            expect("=>");
            endTime = getTime();
        }
        State initialState = parseState();
        switch(lookahead()) {
            case ":":
                initialPitchPosition = parsePitchPosition();
                expect("->");
                break;
            case "->":
               break;
            default:
                throw new SyntaxErrorException(index - 1);
        }

        if ("!".equals(peek())) {
            keepPossession = false;
            lookahead();
        }

        State endState = parseState();
        if (index < tokens.size()) {
            expect(";");
        }
        while (index < tokens.size()) {
            addArgumentAssignment();
        }

        Statement statement = new Statement(teamKey, startTime, endTime, initialState, endState, argumentList);
        statement.addOptionalElements(initialPitchPosition, keepPossession);
        return statement;
    }

    private String getTeamKey() {
        return tokens.get(index++);
    }

    private void expect(String token) throws SyntaxErrorException {
        if (!token.equals(tokens.get(index++))) {
            throw new SyntaxErrorException(token, index - 1);
        }
    }

    private String lookahead() {
        return tokens.get(index++);
    }

    private String peek() {
        return tokens.get(index);
    }

    private void addArgumentAssignment() throws Exception {
        String key = tokens.get(index++);
        expect("=");
        String value = tokens.get(index++);
        argumentList.put(key, value);
        if (index < tokens.size()) {
            expect(",");
        }
    }

    private Time getTime() throws Exception {
        int minutes = Integer.parseInt(tokens.get(index++));
        expect(":");
        int seconds = Integer.parseInt(tokens.get(index++));
        return new Time(minutes, seconds);
    }

    private PitchPosition parsePitchPosition() {
        return PitchPosition.valueOf(tokens.get(index++));
    }

    private State parseState() {
        return State.createFromName(tokens.get(index++));
    }
}
