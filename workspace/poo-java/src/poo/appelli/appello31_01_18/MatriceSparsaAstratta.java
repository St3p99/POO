package poo.appelli.appello31_01_18;

import java.util.Iterator;

public abstract class MatriceSparsaAstratta implements MatriceSparsa{
    public int hashCode(){
        int hc = 0;
        final int M = 43;
        int n = getN();
        for(int i = 0; i<n; i++){
            if(rigaVuota(i)) continue;
            for(Elemento e: riga(i))
                hc = hc*M + e.hashCode();
        }
        return hc;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        int n = getN();
        for(int i = 0; i<n; i++){
            if(rigaVuota(i)) continue;
            sb.append(i+": ");
            Iterator<Elemento> it = riga(i).iterator();
            while(it.hasNext()){
                sb.append(it.next());
                if(it.hasNext()) sb.append(", ");
            }
            if(i < n-1) sb.append("\n");
        }
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(! (o instanceof MatriceSparsa)) return false;
        MatriceSparsa m = (MatriceSparsa) o;
        if(m.getN() != getN()) return false;
        for(int i=0; i<getN(); i++){
            if(rigaVuota(i) && m.rigaVuota(i)) continue;
            if(rigaVuota(i) || m.rigaVuota(i)) return false;
            if(sizeRiga(i) != m.sizeRiga(i)) return false;
            Iterator<Elemento> it1 = riga(i).iterator(), it2 = m.riga(i).iterator();
            while(it1.hasNext()){
                if(! it1.next().equals(it2.next())) return false;
            }
        }
        return true;
    }
}