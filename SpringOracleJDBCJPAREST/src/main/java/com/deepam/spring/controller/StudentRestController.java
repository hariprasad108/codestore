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

@RestController("/")
public class StudentRestController { 
    private final Logger logger = LoggerFactory.getLogger(StudentRestController.class);
    
    //@Autowired
    //ApplicationManager applicationManager = null;

    public StudentRestController() {
        super();
        logger.info("***StudentRestController***");  
    }

    @GetMapping("/students")
    public List<?> getStudents() {
        Customer cust1 = new Customer(1);
        Customer cust2 = new Customer(2);
        Customer cust3 = new Customer(3);
        List<Customer> list = new ArrayList();
        list.add(cust1);
        list.add(cust2);
        list.add(cust3);
        return list;
    }

    @GetMapping("/students/{id}")
    public ResponseEntity getStudent(@PathVariable("id") Integer id) {
        Customer customer = new Customer(id); 

        /*if (applicationManager == null) {
            logger.info("---- ApplicationManager not found");
        }
        else {
            logger.info("++++ ApplicationManager is alive ");            
        }*/
        if (customer == null) {
            return new ResponseEntity("No Customer found for Id: " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customer, HttpStatus.OK);
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