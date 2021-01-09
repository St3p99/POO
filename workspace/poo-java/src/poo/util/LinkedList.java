package poo.util;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> {
    // linkedList a doppi puntatori
    private enum Move{UNKNOWN, FORWARD, BACKWARD}  // le enumerazioni vanno dichiarate nella outer class
    private static class Nodo<E>{
        E info;
        Nodo<E> prior,  next;
    }
    private Nodo<T> first = null, last = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        first = null; last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return false;
    }

    public void addFirst(T e) {
        Nodo<T> n = new Nodo<>(); n.info = e;
        n.next = first; n.prior = null;
        if(first != null) first.prior = n; // se non é vuota
        first = n;
        if( last == null) last = n; // la lista era vuota
        size++;
    }

    public void addLast(T e){
        Nodo<T> n = new Nodo<>(); n.info = e;
        n.next = null; n.prior = last;
        if(last != null) last.next = n;
        last = n;
        if(first == null) first = n;
        size++;
    }

    public T getFirst() {
        if(first==null) throw new NoSuchElementException();
        return first.info;
    }

    public T getLast() {
        if(last==null) throw new NoSuchElementException();
        return last.info;
    }

    public T removeFirst() {
        if(first==null) throw new NoSuchElementException();
        T e = first.info;
        first = first.next;
        if(first!=null) first.prior = null; //almeno 2 elementi
        else last = null;
        size--;
        return e;
    }

    public T removeLast(){
        if(last==null) throw new NoSuchElementException();
        T e = last.info;
        last = last.prior;
        if(last!=null) last.next = null;
        else first = null;
        size--;
        return e;
    }

    public ListIterator<T> listIterator() {
        return new ListIteratorImpl();
    }

    public ListIterator<T> listIterator(int pos) {
        return new ListIteratorImpl(pos);
    }

    public Iterator<T> iterator() {
        // gli occhiali sono quelli di Iterator ==> possiamo usare solo hasNext, next, remove
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl implements ListIterator<T>{
        //Nel movimento Backward l'elemento corrente é next
        //Nel movimento Forward l'elemento corrente é previous
        private Nodo<T> previous, next;
        private Move lastMove = Move.UNKNOWN;

        public ListIteratorImpl(){
            previous = null; next = first;
        }

        public ListIteratorImpl(int pos){
            if(pos<0 || pos>size) throw new IllegalArgumentException();
            if(pos == size || pos==0 && isEmpty()) {  // siamo alla fine della lista
                previous=last; next = null;
            }
            else{
                previous = null; next = first;
                for(int i = 0; i<pos; i++){
                    previous = next; next = next.next;
                }
            }
        }

        public boolean hasNext() {
            return next!=null;
        }

        public T next() {
            if(! hasNext()) throw new NoSuchElementException();
            lastMove = Move.FORWARD;
            previous = next; next = next.next;
            return previous.info;
        }

        public boolean hasPrevious() {
            return previous!=null;
        }

        public T previous() {
            if(! hasPrevious()) throw new NoSuchElementException();
            lastMove = Move.BACKWARD;
            next = previous; previous = previous.prior;
            return next.info;
        }


        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            if(lastMove == Move.UNKNOWN) throw new IllegalStateException();
            Nodo<T> r = null; // da rimuovere

            if(lastMove == Move.FORWARD) r = previous;
            else r = next;

            if(r==first){ // rimuove all'inizio o un solo elemento
                first = first.next;
                if(first!=null) first.prior = null;
                else last = null;
            }
            else if(r==last){ // rimuove alla fine
                // c'é piú di un elemento ==> esclude che last sia null
                last = last.prior;
                last.next = null;
            }
            else{  // rimozione intermedia
                r.prior.next = r.next;
                r.next.prior = r.prior;
            }

            // aggiorna l'elemento corrente
            if(lastMove == Move.FORWARD)
                previous = r.prior;
            else
                next = r.next;

            size--;
            lastMove = Move.UNKNOWN;
        }

        public void set(T e) {
            if(lastMove == Move.UNKNOWN) throw new IllegalStateException();
            if(lastMove==Move.FORWARD) previous.info = e;
            else next.info = e;
        }

        public void add(T e) {
            // la add si puó sempre fare
            Nodo<T> n = new Nodo<>();
            n.info = e; n.next = next; n.prior = previous;
            if(next!=null) next.prior = n;
            if(previous!=null) previous.next = n;
            previous = n; // IMPORTANTE
            if(n.next==first) first = n ;  // add in testa
            if(n.prior==last) last = n;  // add in coda
            size++;
            //dopo add non si puó fare remove
            lastMove = Move.UNKNOWN;
        }
    }//ListIteratorImpl

    public static void main(String[] args) {
        LinkedList<Integer> t = new LinkedList<>();
        t.addFirst(1);
        t.addLast(2);
        t.addFirst(3);
        System.out.println(t);
    }
}//LinkedList
