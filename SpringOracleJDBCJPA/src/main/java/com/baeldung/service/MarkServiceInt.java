package com.baeldung.service;

import java.util.List;

import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.Mark;

public interface MarkServiceInt {

    public Integer getMarksIdNexval();

    public Mark getMarkById(Integer id);
    
    public List<Mark> findMarksByStudentId(Integer sydentId);
    
    public List<Mark> listMarks();    

}
