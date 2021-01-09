package poo.util;

import java.util.Iterator;

public abstract class AbstractDeque<T> implements Deque<T> {
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append("[");
        for(T x: this){
            sb.append(x+", ");
        }
        sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode(){
        final int M= 43;
        int h = 0;
        for(T x: this)
            h = h*M + x.hashCode();
        return h;
    }

    public boolean equals(Object o){
        if(! (o instanceof Deque))return false;
        if( o==this ) return true;
        Deque<T> d = (Deque<T>) o;
        if(d.size() != this.size()) return false;
        Iterator<T> it1 = d.iterator(), it2 = iterator();
        while(it1.hasNext()){
            if(! it1.next().equals(it2.next())) return false;
        }
        return true;
    }
}
