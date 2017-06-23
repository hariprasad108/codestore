package com.baeldung;

import com.baeldung.persistence.model.Student;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
public class TestApplication {
    ApplicationManager aplicationManager = null;

    private TestApplication() {
      super();
      aplicationManager = new ApplicationManager();
      
    }

    public static void main(String[] args) {
        TestApplication ta = new TestApplication();
        Student std = new Student(1, "Swamiji Maheshwaranada", 1000000008);
        ta.aplicationManager.getApplication().addStudent(std);

    }
}