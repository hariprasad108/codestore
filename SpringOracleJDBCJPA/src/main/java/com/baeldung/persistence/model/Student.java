package com.baeldung.persistence.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name = "STUDENT")
@NamedNativeQueries({
    @NamedNativeQuery(name = "studentById"
        , query = "select a.id as id, a.name as name, a.age as age from student a where id = ?0"
          , resultClass = StudentBase.class)
    , @NamedNativeQuery(name = "listStudents"
    , query = "select a.id as id, a.name as name, a.age as age from student a order by a.id"
      , resultClass = StudentBase.class)
})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Marks> marks = new ArrayList<>(0);
    
    protected Integer id;
    protected String name;
    protected Integer age;

    public Student() {
        super();
    }

    public Student(Integer id, String name, Integer age, List<Marks> marksList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marksList;
    }

    public Student(String name, Integer age, List<Marks> marksList) {
        this.name = name;
        this.age = age;
        this.marks = marksList;
    }
    
    // constructor for deep copy
    public Student(Student student) {
        this(student.getId(), student.getName(), student.getAge(), student.getMarks());
    }

    @Id
    @Column(name = "ID", nullable = false)
    /*
     * Way how to setup automatic oracle sequence generator to work.
     * allocationSize=1 is always mandatory.
     */
    @SequenceGenerator(name = "studentSeqLoc", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeqLoc")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "AGE", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Marks> getMarks() {
       return marks;
    }
     
    public void setMarks(List<Marks> marks) {
       this.marks = marks;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(), marksList = new StringBuilder();
        marks.forEach((mark) -> marksList.append(mark.toString()));
        builder.append("Student [id=").append(id)
          .append(" name=").append(name)
          .append(" age=").append(age)
          .append(" marks: ").append(marksList)
          .append("]");
        return builder.toString();
    }


}