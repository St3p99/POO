package poo.eratostene;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CrivelloHashSet extends CrivelloAstratto {
    public final int N;
    private Set<Integer> crivello = new HashSet<>();
    private int modCounter;


    public CrivelloHashSet(final int N){
        if(N<2) throw new IllegalArgumentException("Amico mio, ma che mi passi?");
        this.N = N;
        for(int x=2; x<=N; x++)
            crivello.add(x);
    }

    public Iterator<Integer> iterator(){
        return new CrivelloTreeSetIterator();
    }

    public void filtra(){
        for(int x=2; x<=Math.round(Math.sqrt(N)); x++){
            if(crivello.contains(x)){
                int mx = x+x;
                while(mx<=N){
                    crivello.remove(mx); modCounter++;
                    mx += x;
                }
            }//if
        }//for
    }//filtra

    
    private class CrivelloTreeSetIterator implements Iterator<Integer>{//innerClass
        //Fa uso dell'Iterator del TreeSet(copia dell'HashSet)
        TreeSet<Integer> crivelloIt; //TreeSet copia dell'HashSet crivello
        Iterator<Integer> tIt; //TreeSet Iterator
        int modCounterIt;
        boolean rimovibile; 
        Integer current; // oggetto corrente nell'iterazione

        public CrivelloTreeSetIterator(){
            crivelloIt = new TreeSet<>(crivello);
            tIt = crivelloIt.iterator(); 
            modCounterIt = modCounter;
            rimovibile = false;
        }//Costruttore di default

        @Override
        public boolean hasNext(){
            return tIt.hasNext();
        }//hasNext

        @Override
        public Integer next() {
            if(modCounter != modCounterIt) throw new ConcurrentModificationException("Ti itero sul crivello e me lo distruggi?");
            if(! hasNext()) throw new NoSuchElementException();
            rimovibile = true;
            current = tIt.next();
            return current;
        }//next

        @Override
        public void remove(){
            if(modCounter != modCounterIt) throw new ConcurrentModificationException("Ti itero sul crivello e me lo distruggi?");
            if(!rimovibile) throw new IllegalStateException();
            tIt.remove();
            crivello.remove(current);
            rimovibile = false;    
        }//remove
    }//CrivelloTreeSetIterator
    

    public static void main(String[] args) {
        CrivelloHashSet c = new CrivelloHashSet(100);
        //c.filtra();
        Iterator<Integer> it = c.iterator();
        while(it.hasNext()){
            it.next(); it.remove();
        }
        System.out.println(c);
    }


}


    


