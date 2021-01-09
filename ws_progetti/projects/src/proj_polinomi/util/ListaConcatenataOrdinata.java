package proj_polinomi.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenataOrdinata<T extends  Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
    //Lista concatenata semplice, ad un solo puntatore

    private Nodo<T> testa = null;
    private int size = 0;

    private static class Nodo<E>{  // Una inner-class static non ha i puntatori alla sua outer-class
        E info;
        Nodo<E> next; //definizione ricorsiva di un'entitá
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return testa == null;
    }

    public boolean isFull() {
        return false;
    }

    public void clear() {
        testa = null; size = 0;
    }

    /*public boolean contains(T x) {
        Nodo<T> cor = testa;
        while( cor != null ){
            if(cor.info.equals(x)) return true;
            Nodo<T> pre = cor;
            if(cor.info.compareTo(x) > 0) return false;
            cor = cor.next;
        }//while
        return false;
    }//contains

    public void add(T x){
        Nodo<T> pre = null, cor = testa;
        while ( cor!= null && cor.info.compareTo(x) < 0 ){
            pre = cor; cor = cor.next;
        }
        Nodo<T> nuovo = new Nodo<>();
        nuovo.info = x;
        if( cor == testa ){//l'elemento va inserito in testa
            nuovo.next = testa; testa = nuovo;
        }
        else{// ins. in pos. intermedia o alla fine
            nuovo.next = cor;
            pre.next = nuovo;
        }
        size++;
    }*/

    public void add(T x){
        Nodo<T> nuovo = new Nodo<>();
        nuovo.info = x;
        if(testa == null || testa.info.compareTo(x) >= 0){
            nuovo.next = testa; testa = nuovo;
        }
        else{//ins. in pos. intermedia o alla fine
            Nodo<T> pre = testa, cor = testa.next;
            while( cor!=null && cor.info.compareTo(x) < 0 ){
                pre = cor; cor = cor.next;
            }
            //ins. nuovo tra pre e cor
            pre.next = nuovo; nuovo.next = cor;
        }//else
        size++;
    }//add

    public boolean contains(T x) {
        Nodo<T> cor = testa;
        while( cor!= null && cor.info.compareTo(x) < 0 ){
            cor = cor.next;
        }//while
        if( cor != null && cor.info.equals(x))
            return true;
        return false;
    }//containts

    public T get(T x) {
        Nodo<T> cor = testa;
        while( cor!= null && cor.info.compareTo(x) < 0){
            cor = cor.next;
        }
        if( cor != null && cor.info.equals(x)) return cor.info;
        return null;
    }//get


    public void remove(T x){
        Nodo<T> pre = null, cor = testa;
        while( cor!= null && cor.info.compareTo(x) < 0 ){
            pre = cor; cor = cor.next;
        }//while
        if( cor!= null && cor.info.equals(x)){
            if(cor==testa) testa = testa.next;
            else{ pre.next = cor.next; }
            size--;
        }
    }//remove

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append('[');
        Nodo<T> cor = testa;
        while( cor != null ){
            sb.append(cor.info);
            cor = cor.next;
            if(cor!=null) sb.append(", ");
        }//while
        sb.append(']');
        return sb.toString();
    }//toString



    public int hashCode() {
        final int M = 83;
        int h = 0;
        Nodo<T> cor = testa;
        while (cor != null){
            h = h * M + cor.info.hashCode();
            cor = cor.next;
        }
        return h;
    }//hashCode

    public Iterator<T> iterator(){
        return new LCIterator();
    }

    private class LCIterator implements Iterator<T>{
        /* Se pre e cur sono uguali non é possibile fare remove(), poiché non é stata fatta next().
        *  Se cor != null allora punta ad un elemento giá stato restituito. */
        Nodo<T> pre = null, cor = null;

        public boolean hasNext() {
            if( cor == null )//siamo all'inizio
                return testa != null; //hasNext se ha almeno un elemento
            //else cor != null
            return cor.next != null;  //hasNext se il next dell'elemento giá restituito ha un next
        }//hasNext

        public T next() {
            if(!hasNext()) throw new NoSuchElementException("Unnu Sacc!!!");
            if( cor==null ) cor = testa;
            else{ pre = cor; cor = cor.next; }
            return cor.info;
        }//next

        public void remove() {
            if( pre== cor ) throw new IllegalStateException();
            if( cor==testa) testa = testa.next;
            else pre.next = cor.next;
            size--;
            cor = pre;
        }//remove
    }//LCIterator
}//ListaConcatenata
