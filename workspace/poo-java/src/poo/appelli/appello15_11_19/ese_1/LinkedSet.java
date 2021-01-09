package poo.appelli.appello15_11_19.ese_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSet<T> extends AbstractSet<T>{
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private int size = 0;
    private Nodo<T> testa = null;

    public int size(){ return size;}

    public Set<T> crea(){
        return new LinkedSet<>();
    }

    public void add(T x){
        if(belongs(x)) return;
        Nodo<T> nuovo = new Nodo<>(); nuovo.info = x;
        if(testa==null){ nuovo.next = testa; testa = nuovo;}
        else{
            Nodo<T> pre = null, cor = testa;
            while(cor!=null){ pre = cor; cor = cor.next; }
            if(cor == testa){
                nuovo.next = null; testa.next = nuovo;
            }
            else{
                pre.next = nuovo; nuovo.next = cor;
            }
        }//else
        size++;
    }//add

    /*public void remove(T x){
        Nodo<T> pre = null, cor = testa;
        while(cor!=null && ! cor.info.equals(x)){
            pre=cor; cor = cor.next;
        }
        if(cor==null) return; // x non appartiene al Set
        // x appartiene al Set
        if(cor==testa) testa = testa.next;
        else pre.next = cor.next;
        size--;
    }//remove*/

    public Iterator<T> iterator(){
        return new SetIterator();
    }

    private class SetIterator implements Iterator<T>{
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

    public static void main(String[] args) {
        LinkedSet<Integer> l = new LinkedSet<>();
        l.add(5); l.add(4); l.add(3);
        System.out.println(l.size());
        l.remove(4);
        System.out.println(l.size());
    }
}//LinkedSet
