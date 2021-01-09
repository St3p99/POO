package poo.appelli.appello15_11_19.ese_1;

import java.util.Iterator;

public interface Set<T> extends Iterable<T>{
    default int size(){
        int c = 0;
        for(T x: this) c++;
        return c;
    }
    default boolean belongs(T x){
        for(T e: this)
            if(e.equals(x)) return true;
        return false;
    }
    void add(T x);
    Set<T> crea();

    default void remove(T x){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            if(it.next().equals(x)){ it.remove(); break;}
        }
    }

    default Set<T> union(Set<T> s){
        Set<T> ret = crea();
        for(T x: this) ret.add(x);
        for(T x: s) ret.add(x);
        return ret;
    }

    default Set<T> intersection(Set<T> s){
        Set<T> ret = crea();
        for(T x: this)
            if(s.belongs(x)) ret.add(x);
        return ret;
    }

    default Set<T> difference(Set<T> s){
        Set<T> ret = crea();
        for(T x: this)
            if(! s.belongs(x)) ret.add(x);
        return ret;
    }
}
