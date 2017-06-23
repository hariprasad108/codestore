package com.baeldung;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.persistence.model.Student;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public class TestApplication {
    private final Logger logger = LoggerFactory.getLogger(TestApplication.class);
    ApplicationManager aplicationManager = null;

    private TestApplication() {
      super();
      aplicationManager = new ApplicationManager();
      
    }

    public static void main(String[] args) {
        TestApplication ta = new TestApplication();
        //Student std = new Student("Swamiji Maheshwaranada", 1000000008);
        //ta.aplicationManager.getApplication().addStudent(std);
        
        /*Student retStd = ta.aplicationManager.getApplication()
            .getStudent(*std.getId()*new Integer(204));
        ta.logger.info("Student: " + retStd);*/
        
        List<Student> students = ta.aplicationManager.getApplication().listStudents();
        ta.logger.info("Students: " + students);
    }
}