package com.baeldung;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baeldung.configuration.PersistenceConfig;
import com.baeldung.persistence.model.Student;
import com.baeldung.service.StudentService;

public class TestDBMainApp {
    
    AnnotationConfigApplicationContext ctx;
    
    StudentService studentService;

    /*private Integer getSequence() {
        Integer seqNumber = studentService.getSequence()
            .getNextVal();
        System.out.println("Sequence number: " + seqNumber);
        return seqNumber;
    }*/

    public TestDBMainApp() {
        super();
        init();
    }

    private void init() {
        ctx = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        studentService = ctx.getBean("studentService", StudentService.class);
    }
    
    public void close() {
        ctx.close(); 
    }

    public AnnotationConfigApplicationContext getCtx() {
        return ctx;
    }    

    public static void main(String[] args) {
        TestDBMainApp testDBMainApp = new TestDBMainApp();

        System.out.println("------Records Creation--------");
        //testDBMainApp.studentJDBCService.createStudent(testDBMainApp.getSequence(), "Zara", 11);
        //testDBMainApp.studentJDBCService.createStudent(testDBMainApp.getSequence(), "Nuha", 2);
        //testDBMainApp.studentJDBCService.createStudent(testDBMainApp.getSequence(), "Ayan", 15);

        System.out.println("------Listing Multiple Records--------");
        List<Student> students = testDBMainApp.studentService.listStudents();
        for (Student record : students) {
            System.out.print("ID : " + record.getId());
            System.out.print(", Name : " + record.getName());
            System.out.println(", Age : " + record.getAge());
        }

        System.out.println("----Updating Record with ID = 2 -----");
        //testDBMainApp.studentJDBCService.updateStudentAge(2, 20);

        System.out.println("----Listing Record with ID = 2 -----");
        Student student = testDBMainApp.studentService.getStudent(2);
        System.out.print("ID : " + student.getId());
        System.out.print(", Name : " + student.getName());
        System.out.println(", Age : " + student.getAge());

    }
}
