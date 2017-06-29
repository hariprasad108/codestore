package com.baeldung;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.persistence.model.Student;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public class TestApplication {
    private final Logger logger = LoggerFactory.getLogger(TestApplication.class);
    
    ApplicationManager applicationManager = null;

    private TestApplication() {
      super();
      applicationManager = new ApplicationManager();
      
    }

    public static void main(String[] args) {
        TestApplication ta = new TestApplication();
        Student std = new Student("Swamiji Maheshwaranada", 1000000008);
        ta.logger.info(std.toString());
        Integer id = ta.applicationManager.getApplication().addStudent(std);
        std.setId(id);
        ta.logger.info("Student added: " + std);        
        
        std = ta.applicationManager.getApplication().deleteStudent(6);

        Student retStd = ta.applicationManager.getApplication()
            .getStudent(new Integer(id));
        ta.logger.info("*** Student for update: " + retStd);
        if (retStd != null) {
          retStd.setName("Shri " + retStd.getName());
          Student studentOld = ta.applicationManager.getApplication().updateStudent(retStd);
          ta.logger.info("Student updated: " + studentOld);        
        }
        else {
            ta.logger.info("Student for update not exists: " + retStd);                   
        }

        List<Student> students = ta.applicationManager.getApplication().listStudents();
        ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));
        
        Integer newId = ta.applicationManager.getApplication().getStudentIdNexval();
        ta.logger.info("New Id: " + newId);     
        
    }
}