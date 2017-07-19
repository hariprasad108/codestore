package com.baeldung.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class MarksBase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected Integer id;
    protected Integer mark;
    protected Integer year;
    protected Integer studentId;

    /** mandatory constructor */
    public MarksBase() {
        super();
    }
    
    public MarksBase(Integer mark, Integer year, Integer studentId) {
        super();
        this.mark = mark;
        this.year = year;
        this.studentId = studentId;
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

    @Column(name = "STUDENT_ID", insertable = false, updatable=false)
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