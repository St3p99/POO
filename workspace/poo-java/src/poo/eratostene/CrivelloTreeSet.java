package poo.eratostene;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;

public class CrivelloTreeSet extends CrivelloAstratto {
    public final int N;
    private Set<Integer> crivello = new TreeSet<>();


    public CrivelloTreeSet(final int N){
        if(N<2) throw new IllegalArgumentException("Amico mio, ma che mi passi?");
        this.N = N;
        for(int x=2; x<=N; x++)
            crivello.add(x);
    }

    public Iterator<Integer> iterator(){
        return crivello.iterator();
    }

    public void filtra(){
        for(int x=2; x<=(Math.sqrt(N)); x++){
            if(crivello.contains(x)){
                int mx = x+x;
                while(mx<=N){
                    crivello.remove(mx);
                    mx += x;
                }
            }//if
        }//for
    }//filtra
}


    


