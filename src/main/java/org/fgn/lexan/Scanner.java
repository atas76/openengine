package org.fgn.lexan;

import org.fgn.lexan.exceptions.ScannerException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Scanner {

    private int index = 0;
    private String statement;

    public Scanner(String statement) {
        this.statement = statement;
    }

    public List<String> scan() throws ScannerException {

        List<String> result = new ArrayList<>();

        // TODO: improve
        while (index < statement.length()) {

            char ch = statement.charAt(index);

            if (Character.isDigit(ch)) {
                result.add(getNextToken(Character::isDigit));
                continue;
            } else if (Character.isAlphabetic(ch)) {
                result.add(getNextToken(Character::isAlphabetic));
                continue;
            } else if (ch == ':') {
                result.add(String.valueOf(ch));
            } else if (ch == '=') {
                result.add(getNextToken("=>"));
                continue;
            } else if (Character.isWhitespace(ch)) {
                index++;
                continue;
            } else {
                break;
            }

            index++;
        }

        return result;
    }

    private String getNextToken(String token) throws ScannerException {

        int tokenIndex = 0;
        int endTokenIndex = index + token.length();

        while (index < endTokenIndex) {

            char currentChar = statement.charAt(index);
            char currentTokenChar = token.charAt(tokenIndex);

            if (currentChar != currentTokenChar) {
                throw new ScannerException(token);
            }

            tokenIndex++;
            index++;
        }

        return token;
    }

    private String getNextToken(Predicate<Character> tokenInclusionPredicate) {

        StringBuilder result = new StringBuilder();

        while (index < statement.length()) {

            char currentChar = statement.charAt(index);

            if (tokenInclusionPredicate.test(currentChar)) {
                result.append(currentChar);
                index++;
            } else {
                break;
            }
        }

        return result.toString();
    }
}
