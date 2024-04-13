package org.openengine.pureengine.domain.repository.file;

import org.openengine.pureengine.domain.repository.Repository;

public interface LoadableRepository<T> extends Repository<T> {
    void loadData();
}
