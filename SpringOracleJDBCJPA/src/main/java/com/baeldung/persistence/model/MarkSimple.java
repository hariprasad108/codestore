package com.baeldung.persistence.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** Clas for simple fetch without @OneToMany, @ManyToOne */
@Entity
@Table(name = "MARKS")
public class MarkSimple extends MarkBase {
    private static final long serialVersionUID = 1L;

    /** mandatory constructor */
    public MarkSimple() {
        super();
    }
    
    public MarkSimple(Integer mark, Integer year, Integer studentId) {
        super(mark, year, studentId);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Mark [id: ").append(id)
          .append(" mark: ").append(mark)
          .append(" year: ").append(year)
          .append(" student_id: ").append(studentId)
          .append("]");
        return builder.toString();
    }    
}