package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackConcatenato<T> extends StackAstratto<T> {
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private Nodo<T> testa = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        testa = null; size = 0;
    }

    @Override
    public void push(T x) {
        Nodo<T> n = new Nodo<>();
        n.info = x;
        n.next = testa; testa = n;
        size++;
    }

    @Override
    public T pop() {  // puó fallire
        if( testa==null ) throw new NoSuchElementException();
        T x = testa.info;
        testa = testa.next; // dimentichiamo il primo elemento
        size--;
        return x;
    }

    @Override
    public T top(){  // puó fallire
        if( testa==null ) throw new NoSuchElementException();
        return testa.info;
    }

    @Override
    public boolean isEmpty() {
        return testa==null;  // oppure size==0
    }

    @Override
    public boolean isFull() {
        return false;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(200);
        sb.append('[');
        Nodo<T> cor = testa;
        while( cor!= null ){
            sb.append(cor.info);
            cor = cor.next;
            if( cor != null) sb.append(", "); // se non é l'ultimo
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{
        /* Se pre e cur sono uguali non é possibile fare remove(), poiché non é stata fatta next().
         *  Se cor != null allora punta ad un elemento giá stato restituito. */
        Nodo<T> pre = null, cor = null;

        public boolean hasNext() {
            if( cor==null ) return testa != null;
            return cor.next != null;
        }

        public T next() {
            if(! hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = testa;
            else{ pre = cor; cor = cor.next;}
            return cor.info;
        }

        public void remove() {
            if( cor==pre ) throw new IllegalStateException();
            if( cor==testa ) testa = testa.next;
            else pre.next = cor.next;
            cor = pre;
        }
    }//StackIterator
}//StackConcatenato
