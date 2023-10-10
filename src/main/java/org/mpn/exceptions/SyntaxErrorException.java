package org.mpn.exceptions;

public class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String token, int index) {
        super("Expected '" + token + "' at position " + index);
    }
}
