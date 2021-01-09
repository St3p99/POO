package poo.util;

import java.util.Iterator;
/**
 * Vector é un tipo astratto che descrive..
 * @author Libero Nigro 
 */
public interface Vector<T> extends Iterable<T>{// T rappresenta una qualsiasi classe in java
    // restituisce il n. elementi correnti
    default int size(){
        int c=0;
        Iterator<T> it = iterator();
        while(it.hasNext()){
            it.next(); c++;
        }
        return c;
    }//size

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            it.next(); it.remove();
        }
    }//clear

    //size++; "appende" un elemento in pos. size()
    default void add(T x){
        add(size(), x);
    }

    default int indexOf(T x){
        int i = 0;
        for(T e: this){
            if(e.equals(x)) return i;
            i++;
        }
        return -1;
    }//indexOf

    // rimuove l'oggetto x (se presente nel vettore), alla prima occorrenza 
    default void remove(T x){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            T y = it.next();
            if(y.equals(x)) 
                it.remove(); return;
        }
    }
    
    // rimuove l'oggetto x (se presente nel vettore), alla prima occorrenza 
    default T remove(int i){
        if(i<0 || i>=size()) throw new IndexOutOfBoundsException("Amico mio, ma che mi passi!");
        int j = 0;
        Iterator<T> it = iterator();
        while(it.hasNext()){
            T y = it.next();
            if(i==j){
                it.remove(); return y;
            }//if
            j++;
        }//while
        return null;
    }


    /**
     * @param i indice elemento x
     * @return x 
     * Riceve l'indice di un elemento
     * e restituisce l'elemento stesso.
     * */
    default T get(int i){
        if(i<0 || i>=size()) throw new IndexOutOfBoundsException("Amico mio, ma che mi passi!");
        T x = null; int j=0;
        for(T e: this){
            if(j==i){ x=e; break;}
            j++;
        }
        return x;
    }//get
    

    //sostituisce all'elemento in pos. "i"( e lo ritorna), l'oggetto x
    T set(int i, T x);
    
    //size++; aggiunge un elemento in pos. i; e fa uno shift destro da i a size()-1;
    void add(int i, T x);
    
    default boolean isEmpty(){
        return size()==0;
    }

    default boolean contains(T x){
        return indexOf(x)!=-1;
    }
}//Vector
