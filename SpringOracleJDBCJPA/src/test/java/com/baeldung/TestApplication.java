package com.baeldung;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.persistence.model.Marks;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;

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
        //List<Student> students = ta.applicationManager.getApplication().findAllStudents();
        List<Student> students = ta.applicationManager.getApplication().listStudents();        
        ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));

        // next value from sequence
        Integer newId = ta.applicationManager.getApplication().getStudentIdNexval();
        ta.logger.info("New Id: " + newId);
 
        Student std = new Student("Vishwaguruji Maheshwaranada", 1000000008, null);
        
        List<Marks> marks = new ArrayList<Marks>();
        marks.add(new Marks(1008, 2017, std.getId(), std));
        marks.add(new Marks(10008, 2018, std.getId(), std));
        
        std.setMarks(marks);
        ta.logger.info("Student before insert: " + std.toString());
        
        std = ta.applicationManager.getApplication().addStudent(std);
        ta.logger.info("Student added: " + std);
                
        Student studentOld = null;
        /*List<Marks> marks = new ArrayList<Marks>();
        marks.add(new Marks(1008, 2017, std));
        marks.add(new Marks(10008, 218, std));

        std.setMarks(marks);
        
        studentOld = ta.applicationManager.getApplication().updateStudent(std);
        ta.logger.info("Student updated with marks: " + studentOld); */       
        
        Student stddel = ta.applicationManager.getApplication().deleteStudent(207);

        Student retStd = ta.applicationManager.getApplication()
            .getStudentById(79);
        ta.logger.info("*** Student for update: " + retStd);
        if (retStd != null) {
          retStd.setName("Shri " + retStd.getName());
          studentOld = ta.applicationManager.getApplication().updateStudent(retStd);
          ta.logger.info("Student updated: " + studentOld);        
        }
        else {
            ta.logger.info("Student for update not exists: " + retStd);                   
        }

        /*students = ta.applicationManager.getApplication().listStudents();
        ta.logger.info("*** Students ***");
        students.forEach(a -> ta.logger.info(a.toString()));*/
        
        ta.logger.info("//// Finished ////");
        
    }
}