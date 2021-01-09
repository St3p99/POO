package proj_polinomi.util;

import java.util.Iterator;

public interface CollezioneOrdinata <T extends Comparable<? super T>> extends Iterable<T>{
    default int size(){
        int size=0;
        for(T e: this) size++;
        return size;
    }

    default boolean contains(T e){
        for(T x: this){
            if(x.equals(e)) return true;
            if(x.compareTo(e)>0) break;
        }
        return false;
    }
    default boolean isEmpty(){
        for(T e: this)
            return false;
        return true;
    }
    /*Torna l'elemento nella lista uguale (in alcune caratteristiche) a quello nella lista.
    * Esempio: nella rubrica cercavamo un Nominativo solo con nome e cognome, e veniva
    *          restituito il Nominativo completo(con tutte le caratteristiche). */
    default T get(T e){
        for(T x: this)
            if(x.equals(e))
                return x;
        return null;
    }
    default void clear(){
        Iterator<T> it = this.iterator();
        while(it.hasNext()){
            it.next(); it.remove();
        }
    }

    default boolean isFull(){
        return false;
    }

    void remove(T e);
    void add(T e);
}