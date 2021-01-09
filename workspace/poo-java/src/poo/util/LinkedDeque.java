package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<T> extends AbstractDeque<T> {
    private class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private Nodo<T> first = null, last = null;
    private int size;

    public int size() {
        return size;
    }

    public void clear() {
        first=last=null;
        size=0;
    }

    public boolean contains(T x) {
        Nodo<T> cor = first;
        while(cor!=last){
            if(cor.info.equals(x)) return true;
            cor = cor.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size==0;
    }

    //Giá implementato nell'interefaccia public boolean isFull() { return false; }

    public void addFirst(T x) {
        Nodo<T> n = new Nodo<>(); n.info = x;
        n.next = first; first = n;
        if(last==null) last = n; // se la lista era vuota
        size++;
    }

    public void addLast(T x) {
        Nodo<T> n = new Nodo<>();
        n.info = x; n.next = null;
        if(first==null) first = n;
        else last.next = n;
        last = n;
        size++;
    }

    public T getFirst() { // restituisce senza rimuovere
        if(first==null) throw new NoSuchElementException("Empty deque");
        return first.info;
    }

    public T getLast() {
        if(last==null) throw new NoSuchElementException("Empty deque");
        return last.info;
    }

    public T removeFirst(){
        if(first==null) throw new NoSuchElementException("Empty deque");
        T x = first.info;
        first = first.next;
        if(first == null) last = null;
        size--;
        return x;
    }

    public T removeLast() {
        if(last==null) throw new NoSuchElementException("Empty deque");
        T x = last.info;
        Nodo<T> pre= null, cor=first;
        while(cor!=last){
            pre = cor; cor = cor.next; }
        if(cor==first){ // un solo elemento
            first = null; last = null;
        }
        else{
            pre.next = null; last = pre;
        }
        size--;
        return x;
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T>{
        /* Se pre e cur sono uguali non é possibile fare remove(), poiché non é stata fatta next().
         *  Se cor != null allora punta ad un elemento giá stato restituito. */
        private Nodo<T> pre = null, cor = null;

        @Override
        public boolean hasNext() {
            if(cor==null) return first != null; // mai fatto next()
            return cor.next!=null;
        }

        @Override
        public T next() {
            if(! hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = first;
            else{
                pre = cor; cor = cor.next;
            }
            return cor.info;
        }

        @Override
        public void remove() {
            if(cor==pre) throw new IllegalStateException();
            if(cor==first) first = first.next;
            else pre = pre.next;
            cor = pre;
        }
    }

    public static void main(String[] args) {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        //ld.addFirst(2);
        ld.addLast(10);
        //ld.addFirst(3);
        //ld.addFirst(7);
        ld.addLast(1);
        ld.addLast(2);
        //ld = [7,3,2,10,1,2]
        System.out.println("First: "+ld.getFirst());
        System.out.println("Last: "+ld.getLast());
        System.out.println("Contains: "+ld.contains(1));
        LinkedDeque<Integer> ld_2 = new LinkedDeque<>();
        ld_2.addFirst(2);
        ld_2.addLast(10);
        ld_2.addFirst(3);
        ld_2.addFirst(7);
        ld_2.addLast(1);
        ld_2.addLast(2);
        System.out.println(ld.equals(ld_2));

        for(int n = ld.size(); n>0; n--)
            System.out.print(ld.removeFirst()+" ");
        System.out.println();
        System.out.println(ld_2);
    }
}
