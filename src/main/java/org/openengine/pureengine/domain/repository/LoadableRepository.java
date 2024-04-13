package org.openengine.pureengine.domain.repository;

public interface LoadableRepository<T> extends Repository {
    void loadData();
}
