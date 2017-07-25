package com.baeldung.persistence.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.baeldung.utils.DuplicatesInt;


@MappedSuperclass
public class StudentBase implements Serializable, DuplicatesInt {
    protected static final long serialVersionUID = 1L;
    
    protected Integer id;
    protected String name;
    protected Integer age;
    protected ZonedDateTime updateDate;

    public StudentBase() {
        super();
    }

    public StudentBase(Integer id, String name, Integer age, ZonedDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.updateDate = updateDate;
    }

    public StudentBase(String name, Integer age, ZonedDateTime updateDate) {
        this.name = name;
        this.age = age;
        this.updateDate = updateDate;
    }
    
    // constructor for deep copy
    public StudentBase(StudentBase student) {
        this(student.getId(), student.getName(), student.getAge(), student.getUpdateDate());
    }

    @Id
    @Column(name = "ID", nullable = false)
    /*
     * Way how to setup automatic oracle sequence generator to work.
     * allocationSize=1 is always mandatory.
     */
    @SequenceGenerator(name = "studentSeqLoc", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeqLoc")
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "AGE", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "UPDATE_DATE", nullable = false)
    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student [id: ").append(id)
          .append(" name: ").append(name)
          .append(" age: ").append(age)
          .append(" update date: ").append(updateDate)
          .append("]");
        return builder.toString();
    }
}