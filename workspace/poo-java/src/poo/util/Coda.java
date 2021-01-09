package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Coda<T> extends Iterable<T> {
    default int size(){
        int i = 0;
        for(T x: this) i++;
        return i;
    }

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){it.next(); it.remove();}
    }

    void put(T x);

    default T get(){  // restituisce e rimuove
        Iterator<T> it = iterator();
        if( ! it.hasNext()) throw new NoSuchElementException();
        T x = it.next(); it.remove();
        return x;
    }

    default T peek() { // restituisce senza rimuovere
        Iterator<T> it = iterator();
        if( ! it.hasNext()) throw new NoSuchElementException();
        return it.next();
    }

    default boolean isEmpty(){ return ! iterator().hasNext();}

    default boolean isFull(){
        return false;
    }
}
