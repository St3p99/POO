package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BufferLimitato<T> implements Coda<T>{
    /* Ogni volta che in e out sono uguali o é piena o é vuota
    *  Possiamo distinguere i due casi con size, mentre in alcuni
    *  casi per risparmiare variabili si usa una booleana.
    *  Se l'ultima operazione é stata put(.) => é piena
    *  Se l'ultima operazione é stata get()  => é vuota.  */
    private T[] buffer;
    private int in, out, size;

    public BufferLimitato(int n){
        if(n<=0) throw new IllegalArgumentException();
        buffer = (T[]) new Object[n];
        // La lunghezza effettiva dell'array la otteniamo dall'array stesso
        in = out = size = 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for(int i=out, j=0; j<size; j++, i = (i+1)%buffer.length) //i per indicizzare - j per contare
            buffer[i] = null;
        in=out=size=0;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return size==buffer.length;
    }

    public void put(T x) {
        if(isFull()) throw new RuntimeException(" Buffer pieno! ");
        buffer[in] = x;
        in  = (in+1)%buffer.length; size++;
    }//put

    public T get() {
        if(isEmpty()) throw new NoSuchElementException(" Buffer vuoto! ");
        T x = buffer[out]; buffer[out] = null;
        out = (out+1)%buffer.length; size--;
        return x;
    }//get

    public T peek() {
        if(isEmpty()) throw new NoSuchElementException(" Buffer vuoto! ");
        return buffer[out];
    }

    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T>{
        private int cursor = -1;
        private boolean rimuovibile = false;

        public boolean hasNext() {
            if(cursor==-1) return size!=0;
            return (cursor+1)%buffer.length != in;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (cursor == -1) cursor = out;
            else cursor = (cursor + 1) % buffer.length;
            rimuovibile = true;
            return buffer[cursor];
        }

        public void remove() {
            if(! rimuovibile) throw new IllegalStateException();
            rimuovibile = false;
            int j = (cursor+1)%buffer.length;
            while( j!= in ){
                buffer[(j-1+buffer.length)%buffer.length]  = buffer[j]; //decremento modulare
                j = (j+1)%buffer.length;
            }
            size--;
            in = (in-1+buffer.length)%buffer.length;
            buffer[in] = null;
            cursor = (cursor-1+buffer.length)%buffer.length;
        }//remove
    }//BufferIterator

    public static void main(String[] args) {
        BufferLimitato<Integer> b = new BufferLimitato<>(5);
        b.put(3);
        b.put(5);
        System.out.println(b.get());
        System.out.println(b.get());
    }
}//BufferLimitato
