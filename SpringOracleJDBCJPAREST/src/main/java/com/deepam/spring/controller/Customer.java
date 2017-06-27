package com.deepam.spring.controller;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Customer implements Serializable {
   private static final long serialVersionUID = 1L;
    Integer id;
    
    Customer() {
        super();
    }
    
    Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}