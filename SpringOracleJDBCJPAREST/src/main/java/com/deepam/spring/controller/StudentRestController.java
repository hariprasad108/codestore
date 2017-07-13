package com.deepam.spring.controller;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.ApplicationManager;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;
import com.deepam.exceptions.CustResourceNotFoundException;
import com.deepam.exceptions.utils.CustomizedExceptionsList;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentRestController {
    private final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    ApplicationManager applicationManager;

    public StudentRestController() {
        super();
        logger.info("***StudentRestController***");
    }

    @GetMapping("/students")
    // @RequestMapping(consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    public List<StudentBase> getStudents() {

        List<StudentBase> students = applicationManager.getApplication()
            .listStudents();
        logger.info("*** Students ***");
        students.forEach(a -> logger.info(a.toString()));

        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentBase> getStudent(@PathVariable("id") Integer id) throws CustResourceNotFoundException {

        /*if (applicationManager == null) logger.info("---- ApplicationManager not found");
        else { logger.info("++++ ApplicationManager is alive ");*/
        
        StudentBase studentRet = applicationManager.getApplication()
            .getStudent(new Integer(id));

        try {
            Validate.notNull(studentRet, CustomizedExceptionsList.EX000);
        } catch (Exception e) {
            logger.info("Student get not found: " + e.getMessage());
            throw new CustResourceNotFoundException(e.getMessage());
        }
        logger.info("Student get found");
        return new ResponseEntity<StudentBase>(studentRet, HttpStatus.OK);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student studentRet = applicationManager.getApplication()
            .addStudent(student);
        logger.info("++++ ApplicationManager, createStudent: " + studentRet);
        return new ResponseEntity(studentRet, HttpStatus.OK);
    }

    @PostMapping("/students/{id}")
    public ResponseEntity<StudentBase> updateStudent(@PathVariable Integer id, @RequestBody Student student) throws CustResourceNotFoundException {

        assert (id.equals(student.getId()));

        StudentBase studentRet = applicationManager.getApplication()
            .updateStudent(student);
        
        try {
            Validate.notNull(studentRet, CustomizedExceptionsList.EX000);
        } catch (Exception e) {
            logger.info("Student for update not found: " + e.getMessage());
            throw new CustResourceNotFoundException(e.getMessage());
        }
        logger.info("Student for delete found");
        return new ResponseEntity(studentRet, HttpStatus.OK);

    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<StudentBase> deleteStudent(@PathVariable Integer id) throws CustResourceNotFoundException {

        StudentBase studentRet = applicationManager.getApplication()
            .deleteStudent(id);
        
        try {
            Validate.notNull(studentRet, CustomizedExceptionsList.EX000);
        } catch (Exception e) {
            logger.info("Student for delete not found: " + e.getMessage());
            throw new CustResourceNotFoundException(e.getMessage());
        }
        logger.info("Student for delete found");
        return new ResponseEntity(studentRet, HttpStatus.OK);
    }

}