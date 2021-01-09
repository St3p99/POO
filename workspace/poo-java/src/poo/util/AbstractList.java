package poo.util;

import java.util.Iterator;

public abstract class AbstractList<T> implements List<T> {
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append("[");
        for(T e: this) sb.append(e+", ");
        sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode(){
        final int M = 43;
        int h = 0;
        for(T e: this)h = h*M + e.hashCode();
        return h;
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(! (o instanceof List)) return false;
        List<T> l = (List<T>) o;
        if(l.size() != this.size()) return false;
        Iterator<T> it1 = iterator(), it2 = l.iterator();
        while(it1.hasNext()){
            if(! it1.next().equals(it2.next()))
                return false;
        }//while
        return true;
    }
}
