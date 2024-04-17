package org.openengine.pureengine.domain.repository;

public interface WriteableRepository<T> extends Repository {
    void save(T entity);
}
