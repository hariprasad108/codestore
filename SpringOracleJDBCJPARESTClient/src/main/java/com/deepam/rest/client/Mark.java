package com.deepam.rest.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected Integer id;
    protected Integer mark;
    protected Integer year;
    protected Integer studentId;
    
    @JsonBackReference private Student student;

    /** mandatory constructor */
    public Mark() {
        super();
    }
    
    public Mark(Integer mark, Integer year, Integer studentId, Student student) {
        super();
        this.mark = mark;
        this.year = year;
        this.studentId = studentId;
        this.student = student;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

     public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Mark [id: ").append(id)
          .append(" mark: ").append(mark)
          .append(" year: ").append(year)
          .append(" student_id: ").append(studentId)
          .append("]");
        return builder.toString();
    }    
}