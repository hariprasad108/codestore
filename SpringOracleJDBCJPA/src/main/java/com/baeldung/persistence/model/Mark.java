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

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "MARK")
@NamedNativeQueries({
    @NamedNativeQuery(name = "markNativeById"
        , query = "select a.*, b.* from mark a"
                  + " left join student b on a.student_id = b.id where b.id = :markId"
          , resultClass = Mark.class)
    , @NamedNativeQuery(name = "listNativeMarks"
    , query = "select a.*, b.* from mark a"
                  + " left join student b on a.student_id = b.id order by a.id desc"
      , resultClass = Mark.class)
    , @NamedNativeQuery(name = "listNativeMarksByStudentId"
    , query = "select a.*, b.* from mark a"
                  + " left join student b on a.student_id = b.id where a.student_id = :studentId"
      , resultClass = Mark.class)
})public class Mark extends MarkBase {
    private static final long serialVersionUID = 1L;
    
    @JsonBackReference private Student student;

    /** mandatory constructor */
    public Mark() {
        super();
    }
    
    public Mark(Integer mark, Integer year, Integer studentId, Student student) {
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
