package poo.appelli.appello23_07_19.sol_1;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T extends Comparable< ?super T>> implements Iterable<T>{
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private Nodo<T> testa = null;
    private int size = 0;

    public int size(){ return size;}

    public void add(T x){
        Nodo<T> n = new Nodo<>(); n.info = x;
        if( testa == null ){
            n.next = null; testa = n;
        }
        else{
            Nodo<T> pre = null, cor = testa;
            while(cor != null ){ pre = cor; cor = cor.next;}
            pre.next = n; n.next = cor;
        }
        size++;
    }

    public void remove(T x){
        Nodo<T> pre = null, cor = testa;
        while(cor!=null && ! cor.info.equals(x)){
            pre = cor; cor = cor.next;
        }
        if(cor == null ) return;
        if(cor == testa ) testa = testa.next;
        else{
            pre.next = cor.next;
        }
        size--;
    }

    public void clear(){testa=null; size=0;}

    public int hashCode(){
        int hc = 0;
        final int M = 43;
        for(T x: this) hc = hc*M + x.hashCode();
        return hc;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        sb.append('[');
        Iterator<T> it = iterator();
        while(it.hasNext()){
            sb.append(it.next());
            if(it.hasNext()) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ListaConcatenata)) return false;
        ListaConcatenata<T> lc = (ListaConcatenata<T>) o;
        if (lc.size() != size()) return false;
        Iterator<T> it1 = iterator(), it2 = lc.iterator();
        while (it1.hasNext())
            if (!it1.next().equals(it2.next())) return false;
        return true;
    }

    public Iterator<T> iterator(){
        return new LCIterator();
    }

    private class LCIterator implements Iterator<T>{
        Nodo<T> pre = null, cor = null;

        public boolean hasNext(){
            if(cor==null) return testa != null;
            return cor.next != null;
        }

        public T next(){
            if(! hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = testa;
            else{
                pre = cor; cor = cor.next;
            }
            return cor.info;
        }

        public void remove(){
            if(pre==cor) throw new IllegalStateException();
            if(cor==testa) testa = testa.next;
            else{
                pre.next = cor.next;
            }
            cor = pre; size--;
        }
    }//LCIterator

    public void sort(){
        boolean scambi = true;
        while(scambi){
            scambi = false;
            Nodo<T> cor = testa;
            while(cor.next != null){
                if(cor.info.compareTo(cor.next.info) > 0){
                    T tmp = cor.info;
                    cor.info = cor.next.info;
                    cor.next.info = tmp;
                    scambi = true;
                }
                cor = cor.next;
            }
        }
    }
}//ListaConcatenata
