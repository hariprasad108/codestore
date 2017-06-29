package com.deepam.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.ApplicationManager;
import com.baeldung.persistence.model.Student;

@RestController("/")
public class StudentRestController { 
    private final Logger logger = LoggerFactory.getLogger(StudentRestController.class);
    
    @Autowired
    ApplicationManager applicationManager;

    public StudentRestController() {
        super();
        logger.info("***StudentRestController***");  
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        Student stud1 = new Student(1, "aaa", 23);
        Student stud2 = new Student(2, "aaa", 24);
        Student stud3 = new Student(3, "aaa", 25);
        List<Student> list = new ArrayList();
        list.add(stud1);
        list.add(stud2);
        list.add(stud3);
        
        List<Student> students = applicationManager.getApplication().listStudents();
        logger.info("*** Students ***");
        students.forEach(a -> logger.info(a.toString()));

        return students;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") Integer id) {

        if (applicationManager == null) {
            logger.info("---- ApplicationManager not found");
        }
        else {
            logger.info("++++ ApplicationManager is alive ");            
        }
        Student retStudent = applicationManager.getApplication()
            .getStudent(new Integer(id));
        if (retStudent == null) {
            return new ResponseEntity<String>("No Student found for Id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(retStudent, HttpStatus.OK);
    }

    /*@PostMapping(value = "/students")
    public ResponseEntity createStudent(@RequestBody Customer customer) {

        customerDAO.create(customer);

        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {

        if (null == customerDAO.delete(id)) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(id, HttpStatus.OK);

    }

    @PutMapping("/students/{id}")
    public ResponseEntity updateStudent(@PathVariable Long id, @RequestBody Customer customer) {

        customer = customerDAO.update(id, customer);

        if (null == customer) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customer, HttpStatus.OK);
    }*/

}