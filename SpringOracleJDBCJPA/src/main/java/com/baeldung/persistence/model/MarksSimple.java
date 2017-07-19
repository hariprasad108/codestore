package com.baeldung.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MARKS")
public class MarksSimple extends MarksBase {
    private static final long serialVersionUID = 1L;

    /** mandatory constructor */
    public MarksSimple() {
        super();
    }
    
    public MarksSimple(Integer mark, Integer year, Integer studentId) {
        super(mark, year, studentId);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Mark [id=").append(id)
          .append(" mark=").append(mark)
          .append(" year=").append(year)
          .append(" student_id: ").append(studentId)
          .append("]");
        return builder.toString();
    }    
}