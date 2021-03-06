package com.baeldung.application;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;
import com.baeldung.service.StudentServiceInt;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
@Component
public class PersistenceApplication {
    private final Logger logger = LoggerFactory.getLogger(PersistenceApplication.class);

    @Autowired
    private StudentServiceInt studentService;
    
    public Integer getStudentIdNexval() {
        Integer id = studentService.getStudentIdNexval();
        logger.info("Id got: " + id);
        return id;        
    }

    public Student addStudent(Student student) {
        Student studentRet = studentService.createStudent(student);
        logger.info("Student created: " + studentRet);
        return studentRet;
    }
    
    public Student getStudentById(Integer id) {
        Student student = studentService.getStudentById(id);
        logger.info("Retrieving student: " + student);
        return student;
    }
    
    public List<Student> findAllStudents() {
        List<Student> students = studentService.findAllStudents();
        logger.info("---- Retrieving all using find students method");
        return students;
    }
    
    public List<Student> listStudents() {
        List<Student> students = studentService.listStudents();
        logger.info("++++ Retrieving all using named query students: " + students);
        return students;
    }
    
    public Student updateStudent(Student student) {
        Student studentOld = studentService.updateStudent(student);
        logger.info("Student updated: " + studentOld);
        return studentOld;
    }
    
   public Student deleteStudent(Integer id) {
       Student student = studentService.deleteStudent(id);
       logger.info("Student deleted: " + student);
       return student;
   }

}
