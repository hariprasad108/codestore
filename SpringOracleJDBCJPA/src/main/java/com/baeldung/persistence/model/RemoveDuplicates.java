package com.baeldung.persistence.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveDuplicates <T extends DuplicatesInt> {

    public RemoveDuplicates() {
    }

    /** to use @OneToMany @ManyToOne annotations leads to duplicate
     *  rows if detail has more rows than only one
     *  to use this method can reduce lines, which are not determined for splitting
     *  be careful, if it is needed to have duplicates in result this method is not recommended */
    public List<T> removeListDuplicates(List<T> objs) {
        // LinkedHashSet keep order
        Map<Integer, T> objsSet = new HashMap<>();
        List<T> objsNew = new ArrayList<>();
        for (T obj : objs) {
            if (!objsSet.containsKey(obj.getId())) {
                objsSet.put(obj.getId(), obj);
                objsNew.add(obj);
            }
        }
        return objsNew;
    }
}
