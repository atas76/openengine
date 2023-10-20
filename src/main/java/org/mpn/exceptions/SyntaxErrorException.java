package org.mpn.exceptions;

public class SyntaxErrorException extends Exception {

    public SyntaxErrorException(int index) {
        super("Unexpected token at position " + index);
    }
    public SyntaxErrorException(String token, int index) {
        super("Expected '" + token + "' at position " + index);
    }
}
