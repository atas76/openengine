package org.ttn.parser.exceptions;

public class ValueException extends Exception {

    private String msg;

    public ValueException(String msg) {
        super(msg);
    }
}
