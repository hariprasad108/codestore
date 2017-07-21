package com.baeldung.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NamedNativeQuery;

/**
 * @author hariprasad
 *
 */
@Entity
@SequenceGenerator(name = "marksSeq", sequenceName = "MARKS_SEQ", allocationSize = 1)
@NamedNativeQuery(name = "getMarksSequenceId"
, query = "select marks_seq.nextval as id from dual a"
  , resultClass = MarkSequence.class)
public class MarkSequence implements Serializable {
    Integer id;
 
    public MarkSequence() {
        super();
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "marksSeq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

