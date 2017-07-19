package com.baeldung.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MARKS")
public class Marks extends MarksBase {
    private static final long serialVersionUID = 1L;
    
    private Student student;

    /** mandatory constructor */
    public Marks() {
        super();
    }
    
    public Marks(Integer mark, Integer year, Integer studentId, Student student) {
        super(mark, year, studentId);
        this.student = student;
    }    

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "STUDENT_ID")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Mark [id=").append(id)
          .append(" mark=").append(mark)
          .append(" year=").append(year)
          .append(" student_id: ").append(studentId)
          .append("]");
        return builder.toString();
    }    
}
