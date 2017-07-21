package com.baeldung.persistence.model;

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

    public StudentSimple(Integer id, String name, Integer age) {
        super(id, name, age);
    }

    public StudentSimple(String name, Integer age) {
        super(name, age);
    }
    
    // constructor for deep copy
    public StudentSimple(StudentSimple student) {
        super(student.getId(), student.getName(), student.getAge());
    }
}
