package poo.eratostene;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrivelloLinkedHashSet extends CrivelloAstratto {
    public final int N;
    private Set<Integer> crivello = new LinkedHashSet<>();
    //In LinkedHashSet é garantito l'ordine di inserimento e l'Hash


    public CrivelloLinkedHashSet(final int N){
        if(N<2) throw new IllegalArgumentException("Amico mio, ma che mi passi?");
        this.N = N;
        for(int x=2; x<=N; x++)
            crivello.add(x);
    }

    public Iterator<Integer> iterator(){
        return crivello.iterator();
    }

    public void filtra(){
        for(int x=2; x<=Math.round(Math.sqrt(N)); x= ((x==2)? x+1 : x+2) ){
            if(crivello.contains(x)){
                int mx = x+x;
                while(mx<=N){
                    crivello.remove(mx);
                    mx += x;
                }
            }//if
        }//for
    }//filtra

    public static void main(String[] args) {
        Crivello c = new CrivelloLinkedHashSet(1000);
        c.filtra();
        System.out.println(c);
    }
}


    


