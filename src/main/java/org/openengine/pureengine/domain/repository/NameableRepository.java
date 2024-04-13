package org.openengine.pureengine.domain.repository;

public interface NameableRepository<T> extends RetrievableRepository<T> {
    T findByName(String name);
}
