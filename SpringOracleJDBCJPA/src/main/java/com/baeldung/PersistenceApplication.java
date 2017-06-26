package com.baeldung;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baeldung.persistence.model.Student;
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

    public Integer addStudent(Student student) {
        // Save new employee
        Integer id = studentService.createStudent(student);
        logger.info("Student created: " + student);
        return id;
    }
    
    public Student getStudent(Integer id) {
        // Get saved employee
        Student student = studentService.getStudent(id);
        logger.info("Retrieving saved student: " + student);
        return student;
    }
    
    public List<Student> listStudents() {
        // Get saved employee
        List<Student> students = studentService.listStudents();
        logger.info("Retrieving saved student: " + students);
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
