package com.baeldung.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.baeldung.persistence.dao.StudentDAO;
import com.baeldung.persistence.model.Student;

@Service
@Repository
@Transactional(value = TxType.SUPPORTS)
public class StudentService implements StudentServiceInt {

    @Autowired
    StudentDAO studentDao;

    @Override
    public Student createStudent(Integer id, String name, Integer age) {
        Student student = new Student(id, name, age);
        createStudent(student);
        return student;
    }
    
    @Override
    public Student createStudent(Student student) {
        String SQL = "insert into student (id, name, age) values (?, ?, ?)";
        studentDao.getSession().update(SQL, new Object[] {student.getId(), student.getName(), student.getAge()});
        System.out.println("Created Record Student: " + student.toString());
        return student;
    }

    @Override
    public Student getStudent(Integer id) {
        String SQL = "select a.id as student_id, a.name as student_name, a.age as student_age from student a where id = ?";
        List<Student> students = null;
        Student student = null;
        
        Session session = studentDao.getSession();
        TypedQuery<Student> query = session.createQuery(SQL, Student.class);
        query.setParameter(1, id);
        students = query.getResultList();
        if (students !=null && students.size() == 1) {
            student = students.get(0);
        }
        return student;
    }

    @Override
    public List<Student> listStudents() {
        String SQL = "select st.id as student_id, st.name as student_name, st.age as student_age from student st order by st.id";
        List<Student> students;

        Session session = studentDao.getSession();
        TypedQuery<Student> query = session.createQuery(SQL, Student.class);
        
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