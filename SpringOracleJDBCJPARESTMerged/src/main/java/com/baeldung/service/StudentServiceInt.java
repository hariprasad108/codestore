package com.baeldung.service;

import java.time.ZonedDateTime;
import java.util.List;

import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.Student;

public interface StudentServiceInt {
    
    public Integer getStudentIdNexval();

    public Student createStudent(Integer id, String name, Integer age, ZonedDateTime updateDate, List<Mark> marksList);

    public Student createStudent(Student student);

    public Student getStudentById(Integer id);
    
    public List<Student> findAllStudents();
    
    public List<Student> listStudents();    

    public Student deleteStudent(Integer id);
    
    public Student updateStudent(Student student);
}
