package com.baeldung.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.common.base.Preconditions;

public abstract class AbstractDAO<T extends Serializable> {

    protected Class<T> clazz;
    
    abstract T findOne(final Integer id);

    abstract List<T> findAll();

    abstract Serializable create(final T entity);

    abstract T update(final T entity);

    abstract void delete(final T entity);

    abstract void deleteById(final Integer entityId);
    
    abstract EntityManager getEntityManager();

    public void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }
}
