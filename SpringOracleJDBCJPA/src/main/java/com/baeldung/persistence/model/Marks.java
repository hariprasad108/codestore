package com.baeldung.persistence.model;

import java.io.Serializable;

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
public class Marks implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer mark;
    private Integer year;
    private Integer studentId;
    
    private Student student;

    public Marks() {
        super();
    }

    @Id
    @Column(name = "ID", nullable = false)
    /*
     * Way how to setup automatic oracle sequence generator to work.
     * allocationSize=1 is always mandatory.
     */
    @SequenceGenerator(name = "marksSeqLoc", sequenceName = "MARKS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marksSeqLoc")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class, cascade = CascadeType.ALL)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Column(name = "MARK", nullable = false)
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Column(name = "YEAR", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "STUDENT_ID", nullable = false)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
