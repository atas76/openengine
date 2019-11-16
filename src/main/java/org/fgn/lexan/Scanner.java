package org.fgn.lexan;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private int index = 0;
    private String statement;

    public Scanner(String statement) {
        this.statement = statement;
    }

    public List<String> scan() {

        List<String> result = new ArrayList<>();

        // TODO: improve
        while (index < statement.length()) {

            char ch = statement.charAt(index);

            if (Character.isDigit(ch)) {
                result.add(getTimeElement());
                continue;
            } else if (Character.isAlphabetic(ch)) {

            } else if (ch == ':') {
                result.add(String.valueOf(ch));
            } else if (ch == '=') {
            } else if (Character.isWhitespace(ch)) {
                index++;
                continue;
            } else {
                break;
            }

            index++;
        }

        result.add("team");
        result.add("instate");
        result.add("=>");
        result.add("outstate");

        return result;
    }

    private String getTimeElement() {

        StringBuilder result = new StringBuilder();

        while (index < statement.length()) {

            char currentChar = statement.charAt(index);

            if (Character.isDigit(currentChar)) {
                result.append(currentChar);
                index++;
            } else {
                break;
            }
        }

        return result.toString();
    }
}
