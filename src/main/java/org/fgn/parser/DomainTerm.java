package org.fgn.parser;

public class DomainTerm {

    protected String description;

    public DomainTerm(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
