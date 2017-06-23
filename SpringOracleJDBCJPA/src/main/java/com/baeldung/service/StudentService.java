package com.baeldung.service;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.baeldung.persistence.dao.StudentDAO;
import com.baeldung.persistence.model.Student;

@Service
@Repository
@Transactional(value = TxType.SUPPORTS)
@NamedNativeQuery(name = "@LISTSTUDENTS", query = "select a.id as id, a.name as name, a.age as age from student a where id = ?")
public class StudentService implements StudentServiceInt {

    @Autowired
    StudentDAO studentDAO;
    
/*
                Session session = factory.openSession();
                Transaction tx = null;
                List<EmployeeBean> employees = null;
                try {
                        tx = session.beginTransaction();
                        // List<EmployeeBean> employees = session.createQuery("FROM
                        // Employee").getResultList();

                        String sqlString = "select a.ID as ID, a.FIRST_NAME as FIRST_NAME, "
                                        + "a.LAST_NAME as LAST_NAME, a.SALARY as SALARY FROM Employee a order by a.ID";

                        Query<EmployeeBean> query = session.createNativeQuery(sqlString, EmployeeBean.class);

                        employees = query.getResultList();

                        System.out.println("***List of Employees***");
                        for (Iterator<EmployeeBean> iterator = employees.iterator(); iterator.hasNext();) {
                                EmployeeBean employee = (EmployeeBean) iterator.next();
                                System.out.print("Id: " + employee.getId());
                                System.out.print(" First Name: " + employee.getFirstName());
                                System.out.print("  Last Name: " + employee.getLastName());
                                System.out.println("  Salary: " + employee.getSalary());
                        }
                        tx.commit();

                } catch (HibernateException e) {
                        if (tx != null)
                                tx.rollback();
                        e.printStackTrace();
                } finally {
                        session.close();
                }
                return employees;
 */

    @Override
    public Student createStudent(Integer id, String name, Integer age) {
        Student student = new Student(id, name, age);
        createStudent(student);
        return student;
    }
    
    @Override
    public Student createStudent(Student student) {
        String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        studentDAO.getSession().update(SQL, new Object[] {student.getId(), student.getName(), student.getAge()});
        System.out.println("Created Record Student: " + student.toString());
        return student;
    }

    @Override
    @Transactional
    public Student getStudent(Integer id) {
        String SQL = "select a.id as student_id, a.name as student_name, a.age as student_age from student a where id = ?";
        List<Student> students = null;
        Student student = null;
        
        Session session = studentDAO.getSession();
        TypedQuery<Student> query = session.createQuery(SQL, Student.class);
        query.setParameter(1, id);
        students = query.getResultList();
        if (students !=null && students.size() == 1) {
            student = students.get(0);
        }
        return student;
    }

    @Override
    @Transactional
    public List<Student> listStudents() {
        String SQL = "select st.id as id, st.name as name, st.age as age from student st order by st.id";
        List<Student> students;

        Session session = studentDAO.getSession();
        //List<Student> students = session.createQuery("select id, name, age from Student").list();
        
        TypedQuery<Student> query = session.createNativeQuery(SQL, Student.class);
        students = query.getResultList();
        return students;
    }

    /*@Override
    public void deleteStudent(Integer id) {
        String SQL = "delete from student where id = ?";
        jdbcTemplateObject.update(SQL, new Object[] { id });
        System.out.println("Deleted Record with ID = " + id);
        return;
    }

    @Override
    public void updateStudentAge(Integer id, Integer age) {
        String SQL = "update student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, new Object[] { age, id });
        System.out.println("Updated Record with ID = " + id);
        return;
    }*/
}