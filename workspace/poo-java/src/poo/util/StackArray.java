package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackArray<T> extends StackAstratto<T>{
    private T[] pila;
    private int size;

    @SuppressWarnings("unchecked")
    public StackArray(int n){
        if(n<=0) throw new IllegalStateException();
        pila = (T[]) new Object[n];
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            pila[i] = null;
        size = 0;
    }//clear

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return false;
    }

    public void push(T x) {
        if(size==pila.length) pila = java.util.Arrays.copyOf(pila, size*2);
        pila[size] = x;
        size++;
    }//push

    public T pop() {
        if(size==0) throw new NoSuchElementException();
        size--;
        T x = pila[size]; pila[size]=null;
        return x;
    }

    public T top() {
        if(size==0) throw new NoSuchElementException();
        return pila[size-1];
    }

    public Iterator<T> iterator(){
        return new StackArrayIterator();
    }

    private class StackArrayIterator implements Iterator<T>{
        private int cor = size;
        private boolean rimuovibile = false;

        public boolean hasNext() {
            return cor > 0;
        }

        public T next() {
            if(! hasNext()) throw new NoSuchElementException();
            rimuovibile = true; cor--;
            return pila[cor];

        }

        public void remove() {
            if(! rimuovibile) throw new IllegalStateException();
            rimuovibile = false;
            for (int i = cor; i < size -1; i++)
                pila[i] = pila[i+1];
            size--; // cor NON SI TOCCA
        }
    }//StackArrayIterator
}
