package com.deepam.rest.client;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;
    private Integer age;
    protected ZonedDateTime updateDate;

    @JsonManagedReference private List<Mark> marks = new ArrayList<>(0);

    public Student() {
        super();
    }

    public Student(Integer id, String name, Integer age, ZonedDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.updateDate = updateDate;
    }

    public Student(String name, Integer age, ZonedDateTime updateDate) {
        this.name = name;
        this.age = age;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

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
          .append(" name: ").append(name)
          .append(" age:").append(age)
          .append(" update date:").append(updateDate)
          .append(" mark: ").append(marksList)
          .append("]");
        return builder.toString();
    }
}