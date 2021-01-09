package poo.eratostene;

import java.util.Iterator;

public interface Crivello extends Iterable<Integer>{
    default int size(){
        int c=0;
        Iterator<Integer> it = iterator();
        while(it.hasNext()){
            it.next(); c++;
        }
        return c;
    }//size

    default boolean ePrimo(int x){
        return true;
    } ///TODO

    void filtra();
}