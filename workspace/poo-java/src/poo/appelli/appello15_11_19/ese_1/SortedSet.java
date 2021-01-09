package poo.appelli.appello15_11_19.ese_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface SortedSet<T extends Comparable<? super T>> extends Set<T>{
    default T first(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        return it.next();
    }
    default T last(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = null;
        while(it.hasNext()) x = it.next();
        return x;
    }
}