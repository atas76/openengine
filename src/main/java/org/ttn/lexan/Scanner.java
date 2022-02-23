package org.ttn.lexan;

import org.ttn.lexan.exceptions.ScannerException;

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

        if (statement.isEmpty()) {
            return result;
        }

        final char [] statementChars = statement.toCharArray();

        while (index < statementChars.length) {

            char ch = statementChars[index];

            if (Character.isDigit(ch)) {
                result.add(getNextToken(Character::isDigit));
                continue;
            } else if (Character.isAlphabetic(ch) || ch == '_') {
                result.add(getNextToken(this::isPermissibleCharacter));
                continue;
            } else if (ch == ':' || ch == '@' || ch == '*' || ch == '!') {
                result.add(String.valueOf(ch));
            } else if (ch == '-') {
                result.add(checkNextToken("->"));
                continue;
            } else if (ch == '=') {
                result.add(checkNextToken("=>"));
                continue;
            } else if (ch == '>') {
                int localIndex = this.index;
                StringBuilder currentToken = new StringBuilder();
                while (localIndex < statementChars.length && statementChars[localIndex] == '>') {
                    currentToken.append(ch);
                    ++localIndex;
                }
                result.add(checkNextToken(currentToken.toString()));
                continue;
            } else if (Character.isWhitespace(ch)) {
                index++;
                continue;
            } else {
                throw new ScannerException(String.valueOf(ch));
            }

            index++;
        }

        return result;
    }

    private String checkNextToken(String token) throws ScannerException {
        if (isNextToken(token)) {
            return token;
        } else {
            throw new ScannerException(token + " expected");
        }
    }

    private boolean isPermissibleCharacter(char ch) {
        return Character.isAlphabetic(ch) || ch == '_';
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

    private boolean isNextToken(String token) {
        int tokenIndex = 0;
        int endTokenIndex = index + token.length();

        while (index < endTokenIndex) {

            char currentChar = statement.charAt(index);
            char currentTokenChar = token.charAt(tokenIndex);

            if (currentChar != currentTokenChar) {
                return false;
            }

            tokenIndex++;
            index++;
        }

        return true;
    }
}
