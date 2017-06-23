package com.baeldung.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Preconditions;

public abstract class AbstractDAO<T extends Serializable> {

    protected Class<T> clazz;
    
    abstract T findOne(final Integer id);

    abstract List<T> findAll();

    abstract void create(final T entity);

    abstract T update(final T entity);

    abstract void delete(final T entity);

    abstract void deleteById(final Integer entityId);

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }
}
