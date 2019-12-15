package org.fgn.parser;

import java.util.Objects;

public class DomainTerm {

    protected String description;

    DomainTerm(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainTerm)) return false;
        DomainTerm that = (DomainTerm) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
