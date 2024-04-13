package org.openengine.pureengine.domain.repository;

import java.util.Collection;

public interface IdentifiableRepository<T> extends Repository<T> {

    T findById(int id);
}
