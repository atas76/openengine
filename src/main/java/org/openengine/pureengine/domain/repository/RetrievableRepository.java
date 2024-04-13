package org.openengine.pureengine.domain.repository;

import java.util.Collection;

public interface RetrievableRepository<T> extends Repository<T> {
    Collection<T> findAll();
}
