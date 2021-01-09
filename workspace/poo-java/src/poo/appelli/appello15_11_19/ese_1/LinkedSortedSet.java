package poo.appelli.appello15_11_19.ese_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSortedSet<T extends Comparable<? super T>> extends AbstractSet<T> implements SortedSet<T>{
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private int size = 0;
    private Nodo<T> testa = null;

    public Set<T> crea(){
        return new LinkedSortedSet<>();
    }

    public void add(T x){
        if(belongs(x)) return;
        Nodo<T> nuovo = new Nodo<>(); nuovo.info = x;
        if(testa==null || testa.info.compareTo(x)>=0){
            nuovo.next = testa; testa = nuovo;
        }
        else{
            Nodo<T> pre = null, cor = testa;
            while(cor!=null && cor.info.compareTo(x)<0){
                pre = cor; cor = cor.next;
            }
            pre.next = nuovo; nuovo.next = cor;
        }
        size++;
    }//add

    public void remove(T x){
        Nodo<T> pre = null, cor = testa;
        while(cor!=null && cor.info.compareTo(x)<0){
            pre = cor; cor = cor.next;
        }
        if(cor!=null && cor.info.equals(x)){//trovato
            if(cor==testa) testa = testa.next;
            else{
                pre.next = cor.next;
            }
            size--;
        }
    }//remove

    public Iterator<T> iterator(){
        return new SortedSetIterator();
    }

    private class SortedSetIterator implements Iterator<T>{
        // se pre == cor non é possibile fare remove
        Nodo<T> pre = null, cor = null;

        public boolean hasNext(){
            if(cor == null) return size!=0;
            return cor.next != null;
        }

        public T next(){
            if(! hasNext()) throw new NoSuchElementException();
            if( cor == null) cor = testa;
            else{ pre = cor; cor = cor.next;}
            return cor.info;
        }

        public void remove(){
            if(pre==cor) throw new IllegalStateException();
            if(cor==testa) testa = testa.next;
            else pre.next = cor.next;
            size--;
            cor = pre;
        }//remove
    }//SetIterator
}//LinkedSortedSet
