package com.baeldung.service;

import java.util.List;

import com.baeldung.persistence.model.Marks;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;

public interface StudentServiceInt {
    
    public Integer getStudentIdNexval();

    public Student createStudent(Integer id, String name, Integer age, List<Marks> marksList);

    public Student createStudent(Student student);

    public Student getStudentById(Integer id);
    
    public List<Student> findAllStudents();
    
    public List<Student> listStudents();    

    public Student deleteStudent(Integer id);
    
    public Student updateStudent(Student student);
}
