package com.baeldung.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveDuplicates<T extends DuplicatesInt> implements RemoveDuplicatesInt<T> {

    public RemoveDuplicates() {
    }

    /** removes duplicates from List, keeps order */
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
