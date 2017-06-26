/**
 * 
 */
package com.baeldung.persistence.model;

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
, query = "select student_seq.nextval as student_id from dual a"
  , resultClass = StudentSequence.class)
public class StudentSequence {
    @Id
    @GeneratedValue(generator = "studentSeq")
    Integer student_id;
 
    public StudentSequence() {
        super();
    }

    public void setNextVal(Integer studentId) {
        this.student_id = studentId;
    }

    public Integer getNextVal() {
        return student_id;
    }
}
