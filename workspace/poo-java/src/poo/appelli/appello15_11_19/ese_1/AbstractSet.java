package poo.appelli.appello15_11_19.ese_1;

public abstract class AbstractSet<T> implements Set<T>{
    public boolean equals(Object o){
        //L'ordine non conta
        if(o==this) return true;
        if(! (o instanceof Set)) return false;
        Set<T> s = (Set<T>) o;
        if(s.size() != this.size()) return false;
        for(T x: this)
            if(! s.belongs(x)) return false;
        return true;
    }//equals

    public int hashCode(){
        int hc = 0;
        final int M = 43;
        for(T x: this)
            hc = hc*M + x.hashCode();
        return hc;
    }//hashCode

    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append('[');
        for(T x: this)
            sb.append(x + ", ");
        if(size()!=0) sb.setLength(sb.length()-2);
        sb.append(']');
        return sb.toString();
    }//toString
}
