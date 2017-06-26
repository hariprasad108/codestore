package com.baeldung.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name = "STUDENT")
@NamedNativeQueries({
    @NamedNativeQuery(name = "studentById"
        , query = "select a.id as id, a.name as name, a.age as age from student a where id = ?0"
          , resultClass = Student.class)
    , @NamedNativeQuery(name = "listStudents"
    , query = "select a.id as id, a.name as name, a.age as age from student a"
      , resultClass = Student.class)
})
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;

    public Student() {
        super();
    }

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        final Student other = (Student) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        
        if (age == null) {
            if (other.age != null) {
                return false;
            }
        }
        else if (!age.equals(other.age)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student [id=").append(id)
          .append(" name=").append(name)
          .append(" age=").append(age)
          .append("]");
        return builder.toString();
    }


}