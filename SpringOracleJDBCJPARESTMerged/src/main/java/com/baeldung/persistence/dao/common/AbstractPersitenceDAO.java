package com.baeldung.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.common.base.Preconditions;

public abstract class AbstractPersitenceDAO<T extends Serializable> extends AbstractDAO<T> {
    
    @PersistenceContext
    private EntityManager entityManager;

    public AbstractPersitenceDAO() {
        super();

    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T findOne(final Integer id) {
        return (T) entityManager.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public Serializable create(final T entity) {
        // does not return id
        Preconditions.checkNotNull(entity);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }    

    @Override
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) entityManager.merge(entity);
    }

    @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.remove(entity);
    }
    
    @Override
    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

}