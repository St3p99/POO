package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Stack<T> extends Iterable<T>{
    default int size(){
        int i =0;
        for(T x: this) i++;
        return i;
    }

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){ it.next(); it.remove();}
    }
    void push(T e); //add

    default T pop(){  // remove (puó fallire se lo stack é vuoto)
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = it.next(); it.remove();
        return x;
    }//pop

    default T top(){  // restituisce l'elemento in cima ma non lo toglie?
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        return it.next();
    }//top

    default boolean isEmpty(){return ! iterator().hasNext();}
    default boolean isFull(){return  false;}
}

