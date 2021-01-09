package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListaRec <T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
    private static class Lista<E>{
        E info;
        Lista<E> next;
    }

    private Lista<T> lista =null;
    // il campo size non viene messo a scopo didattico

    public int size() {
        return size(lista);
    }

    private int size(Lista<T> lista){
        if(lista==null) return 0;
        return 1+size(lista.next);
    }

    public boolean contains(T e) {
        return contains(lista, e);
    }

    private boolean contains(Lista<T> lista, T e){
        if(lista==null) return false;
        if(lista.info.equals(e)) return true;
        if(lista.info.compareTo(e)>0) return false;
        return contains(lista.next, e);
    }

    public T get(T e) {
        return get(lista, e);
    }

    private T get(Lista<T> lista, T e){
        if(lista==null) return null;
        if(lista.info.equals(e)) return lista.info;
        if(lista.info.compareTo(e)>0) return null;
        return get(lista.next, e);
    }

    public boolean isFull() {
        return false;
    }

    public void remove(T e) {
        lista = remove(lista, e);
    }

    private Lista<T> remove(Lista<T> lista, T e){
        if(lista==null) return lista;
        if(lista.info.equals(e)) return lista.next;
        if(lista.info.compareTo(e)>0) return lista;
        lista.next = remove(lista.next, e);
        return lista;
    }

    public void add(T e) {
        // la lista cambia, va riassegnata
        lista = add(lista, e);
    }

    private Lista<T> add(Lista<T> lista, T e){
        if(lista==null){
            lista = new Lista<>();
            lista.info = e; lista.next = null;
            return lista;
        }
        if(lista.info.compareTo(e)>=0){
            Lista<T> n = new Lista<>();
            n.info = e; n.next = lista;
            return n;
        }
        // la lista cambia, va riassegnata
        lista.next = add(lista.next, e);
        return lista; // ritorno la lista con il residuo cambiato
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(300);
        sb.append('[');
        toString(lista, sb);
        sb.append(']');
        return sb.toString();
    }

    private void toString(Lista<T> lista, StringBuilder sb){
        if(lista == null) return;
        sb.append(lista.info);
        if(lista.next != null) sb.append(", ");
        toString(lista.next, sb);
    }

    public boolean equals(Object x){
        if(! (x instanceof ListaRec)) return false;
        if( x==this) return true;
        ListaRec<T> l = (ListaRec<T> ) x;
        return equals(lista, l.lista);
    }

    private boolean equals(Lista<T> l1, Lista<T> l2){
        if(l1 == null && l2 == null) return true; // caso di uscita
        if(l1 == null || l2 == null) return false;
        if(! l1.info.equals(l2.info)) return false;
        return equals(l1.next, l2.next);
    }

    public int hashCode(){
        return hashCode(lista);
    }

    private int hashCode(Lista<T> lista){
        if(lista == null) return 0;
        return 43*hashCode(lista.next) + lista.info.hashCode();
    }

    public Iterator<T> iterator(){
        return new LCIterator();
    }

    private class LCIterator implements Iterator<T>{
        /* Se pre e cur sono uguali non é possibile fare remove(), poiché non é stata fatta next().
         *  Se cor != null allora punta ad un elemento giá stato restituito. */
        Lista<T> pre = null, cor = null;

        public boolean hasNext() {
            if( cor == null )//siamo all'inizio
                return lista != null; //hasNext se ha almeno un elemento
            //else cor != null
            return cor.next != null;  //hasNext se il next dell'elemento giá restituito ha un next
        }//hasNext

        public T next() {
            if(!hasNext()) throw new NoSuchElementException("Unnu Sacc!!!");
            if( cor==null ) cor = lista;
            else{ pre = cor; cor = cor.next; }
            return cor.info;
        }//next

        public void remove() {
            if( pre== cor ) throw new IllegalStateException();
            if( cor==lista) lista = lista.next;
            else pre.next = cor.next;
            cor = pre;
        }//remove
    }//LCIterator

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaRec<Integer> l = new ListaRec<>();
        for(;;){
            System.out.println("int ( 0 per terminare) > ");
            int x = sc.nextInt();
            if(x==0) break;
            l.add(x);
        }
        System.out.println(l);
        System.out.println("x = ");
        int x = sc.nextInt();
        l.remove(x);
        System.out.println(l);
    }
}//ListaRec
