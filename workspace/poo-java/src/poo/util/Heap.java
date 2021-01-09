package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>> implements Iterable<T>{
    private T[] heap;
    private
    int n, size;
    //size punta all'ultimo occupato: 1<=size<=n
    @SuppressWarnings ("unchecked")
    public Heap( int n ){
        if( n<=0 ) throw new IllegalArgumentException();
        this.n = n; size= 0;
        heap=(T[]) new Comparable[n+1];
    }//Heap
    public int size(){ return size; }
    public
    boolean contains( T elem){
        for( int i =1; i <=size; i++)
            if( heap[i].equals(elem)) return true;
                return false;
    }//contains

    public void add( T elem){
        if( size==n ){//espandi
            heap = java.util.Arrays.copyOf( heap, 2*n+1 );
            n=2*n;
        }
        size++;
        heap[size]= elem; //aggiunge elem in ultima posizionein ultima posizione
        // aggiusta heap "upward"
        int i = size;
        while( i>1){
            if( heap[i].compareTo(heap[i/2])<0 ){
            //scambia heap[i] e heap[i/2]
                T park=heap[i]; heap[i]=heap[i/2];
                heap[i/2]=park;
                i=i/2;
            }
            else break;
        }//while
        }//add

        public T remove(){
            //rimuove il minimo e lo restituisce
            if( size==0 ) throw new RuntimeException("Heap empty!");
            T min=heap[1];
            heap[1]=heap[size]; //promozione ultimo elemento
            heap[size] = null;
            size--;
            //riaggiusto heap
            int i = 1;
            while( i<=size/2 ){
                int j=2*i, k=j+1; //indici dei due figli di i
                //trova min tra heap[j] e heap[k], sia z l'indice del min
                int z=j; //ipotesi
                if( k<=size && heap[k].compareTo(heap[z])<0 ) z=k;
                if( heap[i].compareTo(heap[z])>0 ){
                    //scambia heap[i] con heap[z]
                    T park=heap[i]; heap[i]=heap[z]; heap[z]=park;
                    i=z;
                }
                else break;
            }
            return min;
        }//remove

        public T remove(T x){
            T e = null;
            int i = 1; //si parte da 1
            while(i<size){
                if(heap[i].equals(x)){
                    e = heap[i]; break;
                }
                i++;
            }
            if(e==null) return null;
            //else trovato
            int oldSize = size;
            size = i-1;
            int j = i+1;
            while(j <= oldSize){ // oldSize inclusa
                this.add(heap[j]); j++;
            }
            return e;
        }

        public void clear(){
            for( int i=1; i<=size; i++ )
                heap[i] = null;
            size=0;
        }//clear
    public String toString(){
        StringBuilder sb = new StringBuilder(200);
        sb.append('[');
        for( int i=1; i<=size; i++ ){
            sb.append( heap[i] );
            if( i<size ) sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }//toString

    public Iterator<T> iterator(){
        return new heapIterator();
    }

    private class heapIterator implements Iterator<T>{
        private int cor = 0;
        private boolean rimovibile = false;

        public boolean hasNext() {
            return cor<size;
        }

        public T next(){
            if(! hasNext()) throw new NoSuchElementException();
            rimovibile = true; cor++;
            return heap[cor];
        }

        public void remove(){
            if(! rimovibile) throw new IllegalStateException();
            rimovibile = false;
            int oldSize = size;
            size = cor - 1;
            int j = cor + 1;
            cor = size;
            while(j<=oldSize){
                add(heap[j]); j++;
            }
        }
    }

    public static void main(String[] args) {
        Heap<Integer> hp = new Heap<>(9);
                    hp.add(-5);
                hp.add(-1);                  hp.add(4);
        hp.add(3);        hp.add(12);   hp.add(30); hp.add(17);
        hp.add(40); hp.add(22);
        System.out.println(hp);
        hp.remove(-1);
        Iterator<Integer> it = hp.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        System.out.println(hp);

    }
}//Heap