package com.deepam.rest.client;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Written by Hariprasad composed from many sources
 *  Client for testing SpringOracleJDBCJPAREST compound projects
 * and SpringOracleJDBCJPARESTMerged project
 * to change URI comment and uncomment lines 22, 23 */
public class SpringRestStudentClient {
    private final Logger logger = LoggerFactory.getLogger(SpringRestStudentClient.class);
    //public static final String REST_SERVICE_URI = "http://localhost:8989/SpringOracleJDBCJPAREST";
    public static final String REST_SERVICE_URI = "http://localhost:8989/SpringOracleJDBCJPARESTMerged";
    public static final String STUDENTS = "/students";
    public static final String STUDENTS_SLASH = "/students/";
    public static final String STUDENT = "/student";
    public static final String STUDENT_SLASH = "/student/";
    public static final String RESOURCE_URLS = REST_SERVICE_URI + STUDENTS;
    public static final String RESOURCE_URLS_SLASH = REST_SERVICE_URI + STUDENTS_SLASH;
    public static final String RESOURCE_URL = REST_SERVICE_URI + STUDENT;
    public static final String RESOURCE_URL_SLASH = REST_SERVICE_URI + STUDENT_SLASH;

    public SpringRestStudentClient() {
        super();
    }

    // GET
    @RequestMapping(produces = {"application/json; charset=UTF-8"})
    private List<Student> listAllStudents() {
        logger.info("!!!!+++++ Active URI: " + REST_SERVICE_URI);
        
        logger.info("Testing listAllStudents API-----------");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Student>> studentResponse = restTemplate.exchange(RESOURCE_URLS, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });
        logger.info("Return code: " + studentResponse.getStatusCodeValue());
        studentResponse.getBody()
            .forEach((student) -> logger.info("Student: " + student.toString()));
        return studentResponse.getBody();
    }

    // GET
    private Student getStudent(Integer id) {
        System.out.println("Testing getStudent API----------");
        RestTemplate restTemplate = new RestTemplate();
        // Student studentRet = (Student) restTemplate.getForObject(RESOURCE_URLS_SLASH + id.toString(), Student.class);
        ResponseEntity<Student> studentResponse = restTemplate.getForEntity(RESOURCE_URLS_SLASH + id.toString(), Student.class);
        Student studentRet = (Student) studentResponse.getBody();

        logger.info("Return code: " + studentResponse.getStatusCodeValue());
        logger.info("Student get: " + studentRet);
        return studentRet;
    }

    // POST
    private Student createStudent(Student student) {
        System.out.println("Testing create Student API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> request = new HttpEntity<>(student);
        // Student studentRet = restTemplate.postForObject(RESOURCE_URL, request, Student.class);
        ResponseEntity<Student> studentResponse = restTemplate.postForEntity(RESOURCE_URL, student, Student.class);
        logger.info("Return code: " + studentResponse.getStatusCodeValue());
        logger.info("Student create: " + studentResponse.getBody());
        return studentResponse.getBody();
    }

    // POST
    private Student updateStudent(Student student) {
        System.out.println("Testing update Student API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> request = new HttpEntity<>(student);
        // Student studentRet = restTemplate.postForObject(RESOURCE_URLS_SLASH + student.getId(), request, Student.class);
        ResponseEntity<Student> studentResponse = restTemplate.postForEntity(RESOURCE_URLS_SLASH + student.getId(), student, Student.class);
        logger.info("Return code: " + studentResponse.getStatusCodeValue());
        logger.info("Student update: " + studentResponse.getBody());
        return studentResponse.getBody();
    }

    // DELETE
    private Student deleteStudent(Student student) {
        System.out.println("Testing delete Student API----------");
        RestTemplate restTemplate = new RestTemplate();
        // restTemplate.delete(RESOURCE_URL_SLASH + id);
        HttpEntity<Student> request = new HttpEntity<>(student);
        ResponseEntity<Student> studentResponse = restTemplate.exchange(RESOURCE_URL_SLASH + student.getId(), HttpMethod.DELETE, request, Student.class);
        logger.info("Return code: " + studentResponse.getStatusCodeValue());
        logger.info("Student delete: " + studentResponse.getBody());
        return studentResponse.getBody();
    }

    public static void main(String args[]) {
        SpringRestStudentClient client = new SpringRestStudentClient();
        client.listAllStudents();
        Student studentGet = client.getStudent(new Integer(450));
        
        ZonedDateTime dateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        Student student = new Student("His Holiness Swamiji Maheshwaranda", 51, dateTime);
        
        List<Mark> mark = new ArrayList<Mark>();
        mark.add(new Mark(1008, 2017, null, student));
        mark.add(new Mark(10008, 1918, null, student));
        
        student.setMarks(mark);
        
        Student studentRet = client.createStudent(student);
        client.logger.info("Student created: " + studentRet);

        Student studentUpd = null;
        if (studentRet != null) {
            studentUpd = new Student(studentRet.getId(), "His Holiness Paramhansa Swamiji Maheshwaranda", 33, ZonedDateTime.now());
            studentRet.getMarks().add(new Mark(1234, 2256, studentRet.getId(), studentRet));
            studentRet.getMarks().add(new Mark(5678, 2020, studentRet.getId(), studentRet));
            studentUpd.getMarks().addAll(studentRet.getMarks());
        }
        if (studentUpd != null) {
            client.updateStudent(studentUpd);
        }

        studentGet = client.getStudent(502);
        if (studentGet != null) {
            client.deleteStudent(studentGet);
        }

        if (studentUpd != null) {
          studentGet = client.getStudent(studentUpd.getId());
        }
        client.logger.info("Still alive: " + studentGet);
        // client.listAllStudents();
        client.logger.info("!!!!+++++ Active URI: " + REST_SERVICE_URI);
    }
}