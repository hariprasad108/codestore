package com.baeldung.persistence.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.baeldung.persistence.dao.common.AbstractPersitenceDAO;
import com.baeldung.persistence.model.Student;

@Repository
public class StudentPersistenceDAO extends AbstractPersitenceDAO<Student> {

    /** constructor */
    StudentPersistenceDAO() {
        super();
        this.setClazz(Student.class);
    }
}
