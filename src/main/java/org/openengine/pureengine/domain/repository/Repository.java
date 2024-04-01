package org.openengine.pureengine.domain.repository;

public interface Repository<T> {

    T findById(int id);
    void loadData();
}
