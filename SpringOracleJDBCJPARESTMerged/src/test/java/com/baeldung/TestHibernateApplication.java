package com.baeldung;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.application.ApplicationManager;
import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.Student;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public class TestHibernateApplication {
    private final Logger logger = LoggerFactory.getLogger(TestHibernateApplication.class);
    
    ApplicationManager applicationManager = null;

    private TestHibernateApplication() {
      super();
      applicationManager = new ApplicationManager();
      
    }

    public static void main(String[] args) {
        TestHibernateApplication ta = new TestHibernateApplication();
        List<Student> students = ta.applicationManager.getApplication().findAllStudents();
        /*ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));*/

        students = ta.applicationManager.getApplication().listStudents();        
        ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));

        // next value from sequence
        Integer newId = ta.applicationManager.getApplication().getStudentIdNexval();
        ta.logger.info("New Id: " + newId);
 
        ZonedDateTime dateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        Student std = new Student("Avatár Purí ěščřžýáíé", 1000000008, dateTime, null);
        
        List<Mark> mark = new ArrayList<Mark>();
        mark.add(new Mark(1008, 2017, std.getId(), std));
        mark.add(new Mark(10008, 2018, std.getId(), std));
        
        std.setMarks(mark);
        ta.logger.info("Student before insert: " + std.toString());
        
        std = ta.applicationManager.getApplication().addStudent(std);
        ta.logger.info("Student added: " + std);
        
        Student studentAfterAdd = ta.applicationManager.getApplication()
            .getStudentById(std.getId());
        ta.logger.info("Student fetch afer add: " + studentAfterAdd);
                
        Student studentOld = null;
        //List<Mark> marks = new ArrayList<Mark>();
        std.getMarks().add(new Mark(1234, 2256, std.getId(), std));
        std.getMarks().add(new Mark(5678, 2020, std.getId(), std));

        //std.setMarks(marks);
        
        studentOld = ta.applicationManager.getApplication().updateStudent(std);
        ta.logger.info("Student updated with marks: " + studentOld);       
        
        Student stddel = ta.applicationManager.getApplication().deleteStudent(588);

        Student retStd = ta.applicationManager.getApplication()
            .getStudentById(594);
        ta.logger.info("*** Student for update: " + retStd);
        if (retStd != null) {
          retStd.setName("Shri Siva");
          studentOld = ta.applicationManager.getApplication().updateStudent(retStd);
          ta.logger.info("Student updated: " + studentOld);        
        }
        else {
            ta.logger.info("Student for update not exists: " + retStd);                   
        }

        students = ta.applicationManager.getApplication().listStudents();
        ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));
        
        ta.logger.info("//// Finished ////");
        
    }
}