package poo.appelli.appelloStraordinario;

import java.util.Iterator;

public abstract class ElencoAstratto<T extends Comparable<? super T>> implements Elenco<T> {
    public int hashCode(){
        int hc = 0;
        final int M = 43;
        for(T x: this)
            hc = hc*M + x.hashCode();
        return hc;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        Iterator<T> it = iterator();
        int c = 1;
        while(it.hasNext()){
            sb.append(c+") "+it.next());
            if(it.hasNext()) sb.append("\n");
            c++;
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if( o==this ) return true;
        if( !(o instanceof Elenco)) return false;
        Elenco<T> e = (Elenco<T>) o;
        if(e.size() != this.size()) return false;
        Iterator<T> it1 = iterator(), it2 = e.iterator();
        while(it1.hasNext()){
            if(! it1.next().equals(it2.next())) return false;
        }
        return true;
    }

}
