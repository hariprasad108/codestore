package com.baeldung.service;

import java.util.List;

import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;

public interface StudentServiceInt {
    
    public Integer getStudentIdNexval();

    public Integer createStudent(Integer id, String name, Integer age, List<Mark> marksList);

    public Integer createStudent(Student student);

    public Student getStudentById(Integer id);
    
    public List<Student> findAllStudents();
    
    public List<Student> listStudents();    

    public Student deleteStudent(Integer id);
    
    public Student updateStudent(Student student);
}
