package org.mpn.exceptions;

public class UnknownDirectiveException extends Exception {

    private String directive;
    public UnknownDirectiveException(String directive) {
        this.directive = directive;
    }

    @Override
    public String getMessage() {
        return this.directive;
    }
}
