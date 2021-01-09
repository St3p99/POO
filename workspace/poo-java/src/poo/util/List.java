package poo.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public interface List<T> extends Iterable<T> {
    default int size(){
        int i = 0;
        for(T x: this) i++;
        return i;
    }

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){ it.next(); it.remove();}
    }

    default boolean contains(T x){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            if(x.equals(it.next())) return true;
        }
        return false;
    }

    default boolean isEmpty(){
        return ! iterator().hasNext();
    }

    default boolean isFull(){
        return false;
    }

    void addFirst(T x);
    void addLast(T x);

    default T getFirst(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        return it.next();
    }

    default T getLast(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = null;
        while(it.hasNext()) x = it.next();
        return x;
    }

    default T removeFirst(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = it.next(); it.remove();
        return x;
    }
    default T removeLast(){
        Iterator<T> it = iterator();
        if(! it.hasNext()) throw new NoSuchElementException();
        T x = null;
        while(it.hasNext()) x = it.next();
        it.remove();
        return x;
    }

    static <T extends Comparable<? super T>> void sort(List<T> l) { // non posso usare T di this se static
        if (l.isEmpty() || l.size()==1) return;
        boolean scambi = true;
        int ius = l.size() - 1;
        while(scambi){
            ListIterator<T> lit = l.listIterator();
            int i=1,  limite = ius; scambi = false;
            T pre =  lit.next(), cor = null;
            while(i<=limite){
                cor = lit.next();
                if(pre.compareTo(cor) > 0){
                    lit.set(pre);
                    lit.previous(); lit.previous(); lit.set(cor);
                    lit.next();
                    pre = lit.next(); // basterebbe lit.next();
                    scambi = true; ius= i-1;
                }
                else pre = cor;
                i++;
            }
        }//while_esterno
    }//sort

    static <T> void sort(List<T> l, Comparator<T> c){
        if (l.isEmpty() || l.size()==1) return;
        boolean scambi = true;
        int ius = l.size() - 1;
        while(scambi){
            ListIterator<T> lit = l.listIterator();
            int i=1,  limite = ius; scambi = false;
            T pre = lit.next(), cor = null;
            while(i<=limite){
                cor = lit.next();
                if(c.compare(pre, cor) > 0){
                    lit.set(pre);
                    lit.previous(); lit.previous(); lit.set(cor);
                    lit.next();
                    pre = lit.next(); // basterebbe lit.next();
                    scambi = true; ius= i-1;
                }
                else pre = cor;
                i++;
            }
        }//while_esterno
    }//sort

    ListIterator<T> listIterator();
    ListIterator<T> listIterator(int pos);
}//List
