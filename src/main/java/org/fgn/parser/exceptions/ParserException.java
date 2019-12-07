package org.fgn.parser.exceptions;

public class ParserException extends Exception {

    public final static String INVALID_TIME_FORMAT = "Invalid time format";

    public ParserException(String msg) {
        super(msg);
    }
}
