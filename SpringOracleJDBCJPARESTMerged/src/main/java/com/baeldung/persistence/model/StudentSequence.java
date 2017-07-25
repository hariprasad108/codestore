/**
 * 
 */
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
@SequenceGenerator(name = "studentSeq", sequenceName = "STUDENT_SEQ", allocationSize = 1)
@NamedNativeQuery(name = "getStudentSequenceId"
, query = "select student_seq.nextval as id from dual a"
  , resultClass = StudentSequence.class)
public class StudentSequence implements Serializable {
    private static final long serialVersionUID = -7386276676650544255L;
    Integer id;
 
    public StudentSequence() {
        super();
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "studentSeq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
