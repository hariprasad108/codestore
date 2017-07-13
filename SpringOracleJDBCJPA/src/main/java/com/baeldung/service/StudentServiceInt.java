package com.baeldung.service;

import java.util.List;

import com.baeldung.persistence.model.Marks;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;

public interface StudentServiceInt {
    
    public Integer getStudentIdNexval();

    public Student createStudent(Integer id, String name, Integer age, List<Marks> marksList);

    public Student createStudent(Student student);

    public StudentBase getStudent(Integer id);
    
    public List<Student> findAllStudents();
    
    public List<StudentBase> listStudents();    

    public StudentBase deleteStudent(Integer id);
    
    public StudentBase updateStudent(Student student);
}
