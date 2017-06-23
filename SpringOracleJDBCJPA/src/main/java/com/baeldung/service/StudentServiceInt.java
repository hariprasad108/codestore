package com.baeldung.service;

import java.util.List;

import com.baeldung.persistence.model.Student;

public interface StudentServiceInt {

    public Student createStudent(Integer id, String name, Integer age);

    public Student createStudent(Student student);

    public Student getStudent(Integer id);
    
    public List<Student> listStudents();    

	//void addStudent(Student student);

}
