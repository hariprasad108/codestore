package com.baeldung.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.baeldung.persistence.dao.StudentDAO;
import com.baeldung.persistence.model.StudentSequence;
import com.baeldung.persistence.model.Student;

@Service
@Repository
@Transactional(value = TxType.SUPPORTS)
public class StudentService implements StudentServiceInt {

    @Autowired
    StudentDAO studentDAO;

    @Override
    public Student createStudent(Integer id, String name, Integer age) {
        Student student = new Student(id, name, age);
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
    public Integer createStudent(Student student) {
        //String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        Integer id = (Integer) studentDAO.getSession().save(student);
            //.update(SQL, new Object[] { student.getId(), student.getName(), student.getAge() });
        System.out.println("Created Record Student: " + student.toString());
        return id;
    }

    @Override
    @Transactional
    public Student getStudent(Integer id) {
        List<Student> students = null;
        Student student = null;

        Session session = studentDAO.getSession();
        Query<Student> query = session.getNamedQuery("studentById");
        query.setParameter(0, id);
        students = query.getResultList();
        if (students != null && students.size() == 1) {
            student = students.get(0);
        }
        return student;
    }

    @Override
    @Transactional
    public List<Student> listStudents() {
        List<Student> students;

        Session session = studentDAO.getSession();
        // List<Student> students = session.createQuery("select id, name, age from Student").list();

        Query<Student> query = session.getNamedQuery("listStudents");
        students = query.getResultList();
        return students;
    }
    
    @Override
    @Transactional
    public Student updateStudent(Student student) {
        //String SQL = "update student set age = ? where id = ?";
        //jdbcTemplateObject.update(SQL, new Object[] { age, id });
        Student studentOld = getStudent(student.getId());
        if (studentOld != null) {
          // to avoid org.springframework.dao.DuplicateKeyException
          studentDAO.getSession().merge(student);
        }        
        System.out.println("Student updated: " + studentOld);
        return studentOld;
    }

    @Override
    @Transactional
    public Student deleteStudent(Integer id) {
        //String SQL = "delete from student where id = ?";
        //jdbcTemplateObject.update(SQL, new Object[] { id });
        Student student = getStudent(id);
        if (student != null) {
          studentDAO.getSession().delete(student);
        }
        System.out.println("Deleted Record with Id = " + id);
        return student;
    }
}