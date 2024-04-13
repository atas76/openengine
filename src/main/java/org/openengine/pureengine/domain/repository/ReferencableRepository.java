package org.openengine.pureengine.domain.repository;

import java.util.Collection;

public interface ReferencableRepository<T> extends Repository {
    Collection<T> findByReferenceId(int id);
}
