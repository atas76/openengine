package org.openengine.pureengine.domain.repository;

import java.util.Collection;

public interface IdentifiableRepository<T> extends RetrievableRepository<T> {

    T findById(int id);
}
