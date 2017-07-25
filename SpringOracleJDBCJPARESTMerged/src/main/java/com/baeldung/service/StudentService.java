package com.baeldung.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baeldung.persistence.dao.StudentPersistenceDAO;
import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentSequence;

@Service
@Repository
@Transactional(value = "transactionManager", readOnly = true)
public class StudentService implements StudentServiceInt {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentPersistenceDAO studentPersistenceDAO;

    @Override
    public Student createStudent(Integer id, String name, Integer age, ZonedDateTime updateDate, List<Mark> marksList) {
        Student student = new Student(id, name, age, updateDate, marksList);
        createStudent(student);
        return student;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Student createStudent(Student student) {
        studentPersistenceDAO.create(student);
        Student studentRet = student;
        logger.info("+++++++ Student created: " + student.toString());
        return studentRet;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getStudentIdNexval() {
        EntityManager em = studentPersistenceDAO.getEntityManager();
        // Query<StudentSequence> query = session.getNamedQuery("getStudentSequenceId");
        TypedQuery<StudentSequence> query = em.createNamedQuery("getStudentSequenceId", StudentSequence.class);
        StudentSequence studentSequence = query.getSingleResult();
        return studentSequence.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Integer id) {
        List<Student> students = null;
        Set<Student> studentsUniq = null;
        Student student = null;

        EntityManager em = studentPersistenceDAO.getEntityManager();
        Query<Student> query = (Query<Student>) em.createNamedQuery("studentNativeById", Student.class);
        // Query<Student> query = session.createNamedQuery("studentById");
        query.setParameter("studentId", id);
        students = query.getResultList();
        if (students != null) {
            studentsUniq = new HashSet<>();
            studentsUniq.addAll(students);
            if (studentsUniq.size() == 1) {
                student = studentsUniq.stream()
                    .findFirst()
                    .get();
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
        List<Student> studentsNew = new ArrayList<>();
        studentsNew.addAll(studentsSet);
        // Collections.sort(studentsNew, new CustomComparator());
        return studentsNew;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        List<Student> students;
        students = studentPersistenceDAO.findAll();
        students = removeListDuplicates(students);
        return students;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listStudents() {
        List<Student> students;

        EntityManager em = studentPersistenceDAO.getEntityManager();
        // List<Student> students = session.createQuery("select id, name, age from Student").list();

        Query<Student> query = (Query<Student>) em.createNamedQuery("listNativeStudents", Student.class);
        // Query<Student> query = session.createNamedQuery("listStudents");
        students = query.getResultList();
        students = removeListDuplicates(students);
        return students;
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
            // studentPersistenceDAO.getSession().merge(student);
            studentPersistenceDAO.update(student);
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
            studentPersistenceDAO.delete(student);
        }
        System.out.println("Deleted Record with Id = " + id);
        return student;
    }
}

class CustomComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId()
            .compareTo(o2.getId());
    }
}