package com.baeldung.persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.baeldung.persistence.dao.common.AbstractHibernateDAO;
import com.baeldung.persistence.model.Mark;

@Repository
public class MarkDAO extends AbstractHibernateDAO<Mark> {

    /** constructor */
    MarkDAO() {
        super();
        this.setClazz(Mark.class);
    }
    
    public Session getSession() {
            return this.sessionFactory.getCurrentSession();
    }
    
}
