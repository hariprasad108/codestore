package com.baeldung.persistence.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;

public abstract class AbstractHibernateDAO<T extends Serializable> extends AbstractDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    // API

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T findOne(final Integer id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
    }
    
    @Override
    public Serializable save(final T entity) {
            return getCurrentSession().save(entity);
    }
    

    @Override
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }
    
    @Override
    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

}