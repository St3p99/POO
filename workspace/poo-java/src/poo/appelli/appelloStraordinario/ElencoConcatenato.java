package poo.appelli.appelloStraordinario;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElencoConcatenato<T extends Comparable<? super T>> extends ElencoAstratto<T> {
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private Nodo<T> testa = null;
    private int size = 0;


    public int size() {
        return size;
    }

    public void clear() {
        size = 0; testa = null;
    }

    public void add(T x) {
        Nodo<T> n = new Nodo<>(); n.info = x; n.next = null;
        if(testa == null ){
            testa = n;
        }
        else{
            Nodo<T> pre = null, cor = testa;
            while(cor!=null){
                pre = cor; cor = cor.next;
            }
            pre.next = n;
        }
        size++;
    }

    public void ordina() {
        boolean scambi = true;
        while(scambi){
            scambi = false;
            Nodo<T> cor = testa;
            while(cor.next != null){
                if(cor.info.compareTo(cor.next.info) > 0){//swap
                    T tmp = cor.info;
                    cor.info = cor.next.info;
                    cor.next.info = tmp;
                    scambi = true;
                }
                cor = cor.next;
            }
        }
    }

    public Iterator<T> iterator() {
        return new ElencoConcatenatoIterator();
    }

    private class ElencoConcatenatoIterator implements Iterator<T>{
        Nodo<T> pre = null, cor = null;

        public boolean hasNext(){
            if(cor==null) return testa != null;
            return cor.next != null;
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = testa;
            else{ pre = cor; cor = cor.next;}
            return cor.info;
        }

        public void remove(){
            if(pre==cor) throw new IllegalStateException();
            if(cor==testa) testa = testa.next;
            else{ pre.next = cor.next;}
            cor = pre;
        }
    }
}
