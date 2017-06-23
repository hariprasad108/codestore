package com.baeldung.persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.baeldung.persistence.dao.common.AbstractHibernateDAO;
import com.baeldung.persistence.model.Student;

@Repository
public class StudentDAO extends AbstractHibernateDAO<Student> {

    public Session getSession() {
            return this.sessionFactory.getCurrentSession();
    }
    
}
