package poo.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayVector<T> extends AbstractVector<T> {// la classe implementa l'interfaccia Vector
    private T[] array;
    private int size;// n. elementi correnti
    private int modCounter;

    @SuppressWarnings("unchecked")
    public ArrayVector(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Ma che mi passi!");
        array = (T[]) new Object[capacity];
        size = 0;
        modCounter=0;
    }
    
    @SuppressWarnings("unchecked")
    public ArrayVector(T[] x) {
        size = x.length;
        array = (T[]) new Object[size];
        for (int i = 0; i < size; i++)
            array[i] = x[i];
    }

    public ArrayVector() {
        this(17);
    }

    public T get( int i ){
		if( i<0 || i>=size ) throw new IndexOutOfBoundsException();
		return array[i];
	}//get

    // restituisce il n. elementi correnti
    @Override
    public int size() {
        return size;
    }

    public void add(int i, T x) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException();
        if (size == array.length)
            array = java.util.Arrays.copyOf(array, size * 2);
        //for scorrimento destro
        for (int j = size - 1; j >= i; --j)
            array[j + 1] = array[j];
        array[i] = x;
        size++; modCounter++;
    }//add



    public T set(int i, T x) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException();
        T ret = array[i];
        array[i] = x; modCounter++;
        return ret;
    }

    @Override
    public void remove(T x){ 
        super.remove(x); modCounter++;}

    @Override
    public T remove(int i){ 
        modCounter++; return super.remove(i);}

    public Iterator<T> iterator() {
        return new ArrayVectorIterator();
    }

    private class ArrayVectorIterator implements Iterator<T> {//Innerclass, sono visibili tutte le var. di istanza di ArrayVector
        int cur = -1;
        boolean rimovibile = false;
        int modCounterIt = modCounter;


        @Override
        public boolean hasNext() {
            return (cur < size - 1);
        }

        @Override
        public T next() {
            if(modCounter != modCounterIt) throw new ConcurrentModificationException("Ti itero sul Vector e me lo distruggi!?");
            if (!hasNext()) throw new NoSuchElementException();
            cur++; rimovibile = true;
            return array[cur];
        }

        @Override
        public void remove() {
            if(modCounter != modCounterIt) throw new ConcurrentModificationException("Ti itero sul Vector e me lo distruggi!?");
            if (!rimovibile) throw new IllegalStateException("Bisogna fare prima next()");
            rimovibile = false;
            for (int i = cur + 1; i < size; i++)
                array[i - 1] = array[i];//piú robusto
            cur--; size--; modCounter++; modCounterIt++;
        }
    }

    public static void main(String[] args) {
        ArrayVector<Integer> v = new ArrayVector<>();
        v.add(0, 1);
        v.add(1, 10);
        v.add(2, 82);
        v.add(3, 11);
        v.add(4, 131);
        for (Integer s : v) {
            v.remove(s);
        }


        String[] as = {"Zaino", "lupo", "tazza", "fuoco", "abaco", "deal"};
        Vector<String> vs = new ArrayVector<>();
        for (String s : as) {
            int i = 0;
            boolean flag = false;
            while (i < vs.size() && !flag) {
                if (vs.get(i).compareTo(s) > 0) {
                    vs.add(i, s);
                    flag = true;
                } else i++;
            }//while
            if (!flag) vs.add(s);
        }//foreach
        System.out.println(vs);
    }

}//ArrayVector

/*****METODI GIA PRE IMPLEMENTATI NELL'INTERFACCIA VECTOR*****/
    /*public T remove(int i) {
        if(i<0 || i>=size) throw new IndexOutOfBoundsException("Amico mio, ma che mi passi!");
        T x = array[i];
        //scorri a sinistra
        for (int j=i+1; j<size; j++)
            array[j-1] = array[j];
        size--;
        //CONTRAZIONE if(size<array.length/2) array = java.util.Arrays.copyOf(array, array.length/2);
        return x;
    }*/

    /*public void clear(){
        for(int i=0; i<size; i++)
            array[i]=null;
        size=0;
    }*/

    /*public boolean isEmpty() {
        return size==0;
    }

    public boolean contains(T x) {
        return indexOf(x)>=0;
    }*/
        /*public T get(int i){
        if(i<0 || i>=size) throw new IndexOutOfBoundsException("Amico mio, ma che mi passi!");
        return array[i];
    }

    */
    /*//size++; "appende" un elemento in pos. size()
    public void add(T x) {
        if(array.length == size){//espansione
            array = java.util.Arrays.copyOf(array, size*2);
        }
        array[size] = x; size++;
    }//add*/

    /*public void remove(T x) {
        int i=0;//inizializziamo prima affinché sia visibile fuori dal blocco for
        for(; i<size; i++)
            if(array[i].equals(x)) break;
        if(i==size) return;
        remove(i);//i é l'cur dove é presente x
    }*/

    /*public int indexOf(T x) {
        for (int i=0; i<size; i++)
            if(array[i].equals(x))
                return i;
        return -1;
    }*/
