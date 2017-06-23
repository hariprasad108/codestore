package com.baeldung;


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

        // Get saved employee
        Student student = studentService.getStudent(std.getId());
        logger.info("Retrieving saved student " + student);
    }

}
