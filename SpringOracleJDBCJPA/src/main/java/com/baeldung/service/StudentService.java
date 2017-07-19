package com.baeldung.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baeldung.persistence.dao.StudentDAO;
import com.baeldung.persistence.model.Marks;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentBase;
import com.baeldung.persistence.model.StudentSequence;

@Service
@Repository
@Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Integer getStudentIdNexval() {
        Session session = studentDAO.getSession();
        // Query<StudentSequence> query = session.getNamedQuery("getStudentSequenceId");
        Query<StudentSequence> query = session.createNamedQuery("getStudentSequenceId");
        StudentSequence studentSequence = query.getSingleResult();
        return studentSequence.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Integer id) {
        List<Student> students = null;
        Set<Student> studentsUniq = null;
        Student student = null;

        Session session = studentDAO.getSession();
        Query<Student> query = session.getNamedQuery("studentNativeById");
        //Query<Student> query = session.createNamedQuery("studentById");
        query.setParameter("studentId", id);
        students = query.getResultList();
        if (students != null) {
            studentsUniq = new HashSet<>();
            studentsUniq.addAll(students);
            if (studentsUniq.size() == 1) {
                student = studentsUniq.stream().findFirst().get();
                logger.info("Student found: " + student.toString());
            } else {
                logger.info("Student Id: " + id + " not found");

            }
        }
        return student;
    }
    
    /** to use @OneToMany @ManyToOne annotations leads to duplicate
     *  rows if detail has more rows than only one
     *  to use Set can reduce lines, which are not determined for splitting */
    private List<Student> removeListDuplicates(List<Student> students) {
        // LinkedHashSet keep order
        Set<Student> studentsSet = new LinkedHashSet<>();
        studentsSet.addAll(students);
        List <Student> studentsNew = new ArrayList<>();
        studentsNew.addAll(studentsSet);
        //Collections.sort(studentsNew, new CustomComparator());
        return  studentsNew;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        List<Student> students;
        students = studentDAO.findAll();
        students = removeListDuplicates(students);
        return students;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listStudents() {
        List<Student> students;

        Session session = studentDAO.getSession();
        // List<Student> students = session.createQuery("select id, name, age from Student").list();

        Query<Student> query = session.getNamedQuery("listSNativeStudents");
        //Query<Student> query = session.createNamedQuery("listStudents");
        students = query.getResultList();
        //students = removeListDuplicates(students);
        return students;
    }

    @Override
    @Transactional(readOnly = false)
    public Student createStudent(Student student) {
        // String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        Integer id;
        //Session session = studentDAO.getSession();
        //id = (Integer) session.save(student);
        id = (Integer) studentDAO.save(student);
        Student studentRet = new Student(student);
        // .update(SQL, new Object[] { student.getId(), student.getName(), student.getAge() });
        studentRet.setId(id);
        logger.info("+++++++ Student created: " + student.toString());
        return studentRet;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Student updateStudent(Student student) {
        // String SQL = "update student set age = ? where id = ?";
        // jdbcTemplateObject.update(SQL, new Object[] { age, id });
        // StudentBase studentOld = getStudent(student.getId());
        Student studentOld = getStudentById(student.getId());
        if (studentOld != null) {
            // to avoid org.springframework.dao.DuplicateKeyException
            //studentDAO.getSession().merge(student);
            studentDAO.update(student);
            logger.info("Student updated: " + studentOld);
        } else {
            logger.info("Student not found");

        }
        return studentOld;
    }

    @Override
    @Transactional(readOnly = false)
    public Student deleteStudent(Integer id) {
        // String SQL = "delete from student where id = ?";
        // jdbcTemplateObject.update(SQL, new Object[] { id });
        Student student = getStudentById(id);
        if (student != null) {
            studentDAO.getSession()
                .delete(student);
        }
        System.out.println("Deleted Record with Id = " + id);
        return student;
    }
}

class CustomComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId().compareTo(o2.getId());
    }
}