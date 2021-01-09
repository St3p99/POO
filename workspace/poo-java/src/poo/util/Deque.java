package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Deque<T> extends Iterable<T>{
    default int size(){
        int i = 0;
        for(T x: this) i++;
        return i;
    }

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){ it.next(); it.remove();}
    }

    default boolean contains(T x){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            if(x.equals(it.next())) return true;
        }
        return false;
    }

    default boolean isEmpty(){
        return ! iterator().hasNext();
    }

    default boolean isFull(){
        return false;
    }

    void addFirst(T x);
    void addLast(T x);

    default T getFirst(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        return it.next();
    }

    default T getLast(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = null;
        while(it.hasNext()) x = it.next();
        return x;
    }

    default T removeFirst(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = it.next(); it.remove();
        return x;
    }
    default T removeLast(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = null;
        while(it.hasNext()) x = it.next();
        it.remove();
        return x;
    }
}
