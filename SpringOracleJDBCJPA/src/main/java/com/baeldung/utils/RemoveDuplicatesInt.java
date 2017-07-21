package com.baeldung.utils;

import java.util.List;

public interface RemoveDuplicatesInt<U extends DuplicatesInt> {

    /** to use @OneToMany @ManyToOne annotations leads to duplicate
     *  rows if detail has more rows than only one
     *  to use this method can reduce lines, which are not determined for splitting
     *  be careful, if it is needed to have duplicates in result this method is not recommended */
    public List<U> removeListDuplicates(List<U> objs);
}
