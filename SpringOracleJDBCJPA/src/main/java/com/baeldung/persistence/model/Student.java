package com.baeldung.persistence.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "STUDENT")
@NamedNativeQueries({
    @NamedNativeQuery(name = "studentNativeById"
        , query = "select a.id, a.name, a.age, a.update_date"
                  + ", b.id, b.student_id, b.mark, b.year from student a"
                  + " left join mark b on a.id = b.student_id where a.id = :studentId"
          , resultClass = Student.class)
    , @NamedNativeQuery(name = "listNativeStudents"
    , query = "select a.id, a.name, a.age, a.update_date"
              + ", b.id, b.student_id, b.mark, b.year from student a"
              + " left join mark b on a.id = b.student_id order by a.id desc"
      , resultClass = Student.class)
})
/** strange join is based on object technology Student is class, a.marks join is refined from method getMarks */
@NamedQueries({
    @NamedQuery(name = "studentById"
        , query = "select a from Student a left join a.marks b where a.id = :studentId")
    , @NamedQuery(name = "listStudents"
    , query = "select a from Student a left join a.marks b order by a.id desc")
})
public class Student extends StudentBase {
    
    @JsonManagedReference private List<Mark> marks = new ArrayList<>(0);
    
    public Student() {
        super();
    }

    public Student(Integer id, String name, Integer age, ZonedDateTime updateDate, List<Mark> marksList) {
        super(id, name, age, updateDate);
        this.marks = marksList;
    }

    public Student(String name, Integer age, ZonedDateTime updateDate, List<Mark> marksList) {
        super(name, age, updateDate);
        this.marks = marksList;
    }
    
    // constructor for deep copy
    public Student(Student student) {
        this(student.getId(), student.getName(), student.getAge(), student.getUpdateDate(), student.getMarks());
    }
    
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Mark> getMarks() {
       return marks;
    }
     
    public void setMarks(List<Mark> marks) {
       this.marks = marks;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(), marksList = new StringBuilder();
        if (marks != null) {
          marks.forEach((mark) -> marksList.append((mark == null) ? null : mark.toString()));
        }
        builder.append("Student [id=").append(id)
          .append(" name:").append(name)
          .append(" age: ").append(age)
          .append(" update date: ").append(updateDate)
          .append(" mark: ").append(marksList)
          .append("]");
        return builder.toString();
    }
}