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
public class MainApplication {
    private final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    @Autowired
    private StudentServiceInt studentService;

    public void addStudent(Student std) {
        // Save new employee
        studentService.createStudent(std);
    }
    
    public Student getStudent(Integer id) {
        // Get saved employee
        Student student = studentService.getStudent(id);
        logger.info("Retrieving saved student " + student);
        return student;
    }
    
    public List<Student> listStudents() {
        // Get saved employee
        List<Student> students = studentService.listStudents();
        logger.info("Retrieving saved student " + students);
        return students;
    }

}
