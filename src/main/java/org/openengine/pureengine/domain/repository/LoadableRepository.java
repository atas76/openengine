package org.openengine.pureengine.domain.repository;

import org.openengine.pureengine.domain.repository.Repository;

public interface LoadableRepository<T> extends Repository<T> {
    void loadData();
}
