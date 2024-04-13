package org.openengine.pureengine.domain.repository;

import java.util.Collection;

public interface Repository<T> {

    T findById(int id);

    Collection<T> findByReferenceId(int id);

    T findByName(String name);

    Collection<T> findAll();


}
