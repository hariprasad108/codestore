package com.baeldung.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baeldung.persistence.dao.MarkDAO;
import com.baeldung.persistence.model.Mark;
import com.baeldung.persistence.model.MarkSequence;
import com.baeldung.persistence.model.RemoveDuplicates;
import com.baeldung.persistence.model.Student;
import com.baeldung.persistence.model.StudentSequence;

@Service
@Repository
@Transactional(readOnly = true)
public class MarkService implements MarkServiceInt {
        private final Logger logger = LoggerFactory.getLogger(MarkService.class);
    
        @Autowired
        MarkDAO markDAO;

        @Override
        @Transactional(readOnly = true)
        public Integer getMarksIdNexval() {
            Session session = markDAO.getSession();
            // Query<StudentSequence> query = session.getNamedQuery("getStudentSequenceId");
            Query<MarkSequence> query = session.createNamedQuery("getMarkSequenceId");
            MarkSequence markSequence = query.getSingleResult();
            return markSequence.getId();
        }

        @Override
        @Transactional(readOnly = true)
        public Mark getMarkById(Integer id) {
            List<Mark> marks = null;
            Set<Mark> marksUniq = null;
            Mark mark = null;

            Session session = markDAO.getSession();
            Query<Mark> query = session.getNamedQuery("markNativeById");
            // Query<Mark> query = session.createNamedQuery("markById");
            query.setParameter("markId", id);
            marks = query.getResultList();
            if (marks != null) {
                marksUniq = new HashSet<>();
                marksUniq.addAll(marks);
                if (marksUniq.size() == 1) {
                    mark = marksUniq.stream()
                        .findFirst()
                        .get();
                    logger.info("Mark found: " + mark.toString());
                } else {
                    logger.info("Mark Id: " + id + " not found");

                }
            }
            return mark;
        }

        @Override
        @Transactional(readOnly = true)
        public List<Mark> findMarksByStudentId(Integer studentId) {
            List<Mark> marks = null;
            Set<Mark> marksUniq = null;
            Mark mark = null;

            Session session = markDAO.getSession();
            Query<Mark> query = session.getNamedQuery("listNativeMarksByStudentId");
            query.setParameter("studentId", studentId);
            marks = query.getResultList();
            return marks;
        }

        @Override
        @Transactional(readOnly = true)
        public List<Mark> listMarks() {
            List<Mark> marks;
            Session session = markDAO.getSession();
            Query<Mark> query = session.getNamedQuery("listNativeMarks");
            marks = query.getResultList();
            RemoveDuplicates<Mark> dup = new RemoveDuplicates<>();
            marks = dup.removeListDuplicates(marks);
            return marks;
        }
}
