package com.baeldung.persistence.model;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/** Clas for simple fetch without @OneToMany, @ManyToOne */
@Entity
@Table(name = "STUDENT")
public class StudentSimple extends StudentBase {

    public StudentSimple() {
        super();
    }

    public StudentSimple(Integer id, String name, Integer age, ZonedDateTime updateDate) {
        super(id, name, age, updateDate);
    }

    public StudentSimple(String name, Integer age, ZonedDateTime updateDate) {
        super(name, age, updateDate);
    }
    
    // constructor for deep copy
    public StudentSimple(StudentSimple student) {
        super(student.getId(), student.getName(), student.getAge(), student.getUpdateDate());
    }
}
