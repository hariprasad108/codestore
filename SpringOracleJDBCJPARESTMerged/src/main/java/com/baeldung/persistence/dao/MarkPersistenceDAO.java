package com.baeldung.persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.baeldung.persistence.dao.common.AbstractPersitenceDAO;
import com.baeldung.persistence.model.Mark;

@Repository
public class MarkPersistenceDAO extends AbstractPersitenceDAO<Mark> {

    /** constructor */
    MarkPersistenceDAO() {
        super();
        this.setClazz(Mark.class);
    }
}
