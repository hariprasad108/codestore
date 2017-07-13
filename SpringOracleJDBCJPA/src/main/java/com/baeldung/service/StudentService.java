package com.baeldung.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baeldung.persistence.dao.StudentDAO;
import com.baeldung.persistence.model.Marks;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;
import com.baeldung.persistence.model.StudentSequence;

@Service
@Repository
@Transactional
public class StudentService implements StudentServiceInt {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentDAO studentDAO;

    @Override
    public Student createStudent(Integer id, String name, Integer age, List<Marks> marksList) {
        Student student = new Student(id, name, age, marksList);
        createStudent(student);
        return student;
    }
    
    @Override
    @Transactional
    public Integer getStudentIdNexval() {
      Session session = studentDAO.getSession();
      Query<StudentSequence> query = session.getNamedQuery("getStudentSequenceId");
      StudentSequence studentSequence = query.getSingleResult();
      return studentSequence.getId();
    }

    @Override
    @Transactional
    public StudentBase getStudent(Integer id) {
        List<StudentBase> students = null;
        StudentBase student = null;

        Session session = studentDAO.getSession();
        Query<StudentBase> query = session.getNamedQuery("studentById");
        query.setParameter(0, id);
        students = query.getResultList();
        if (students != null && students.size() == 1) {
            student = students.get(0);
        }
        return student;
    }
    
    @Override
    @Transactional
    public List<Student> findAllStudents() {
        List<Student> students;
        students = studentDAO.findAll();
        return students;
    }

    @Override
    @Transactional
    public List<StudentBase> listStudents() {
        List<StudentBase> students;

        Session session = studentDAO.getSession();
        // List<Student> students = session.createQuery("select id, name, age from Student").list();

        Query<StudentBase> query = session.getNamedQuery("listStudents");
        students = query.getResultList();
        return students;
    }
    
    @Override
    @Transactional
    public Student createStudent(Student student) {
        //String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        Integer id;
        Session session = studentDAO.getSession();
        id = (Integer) session.save(student);
        Student studentRet = new Student(student);
        //.update(SQL, new Object[] { student.getId(), student.getName(), student.getAge() });
        studentRet.setId(id);
        logger.info("+++++++ Student created: " + student.toString());
        return studentRet;
    }

    @Override
    @Transactional
    public StudentBase updateStudent(Student student) {
        //String SQL = "update student set age = ? where id = ?";
        //jdbcTemplateObject.update(SQL, new Object[] { age, id });
        StudentBase studentOld = getStudent(student.getId());
        if (studentOld != null) {
          // to avoid org.springframework.dao.DuplicateKeyException
          studentDAO.getSession().merge(student);
        }        
        System.out.println("Student updated: " + studentOld);
        return studentOld;
    }

    @Override
    @Transactional
    public StudentBase deleteStudent(Integer id) {
        //String SQL = "delete from student where id = ?";
        //jdbcTemplateObject.update(SQL, new Object[] { id });
        StudentBase student = getStudent(id);
        if (student != null) {
          studentDAO.getSession().delete(student);
        }
        System.out.println("Deleted Record with Id = " + id);
        return student;
    }

}