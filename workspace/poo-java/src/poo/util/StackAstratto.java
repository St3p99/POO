package poo.util;

import java.util.Iterator;

public abstract class StackAstratto<T> implements Stack<T>{
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        Iterator<T> it = this.iterator();
        sb.append("[");
        while(it.hasNext()){
            sb.append(it.next());
            if(it.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return null;
    }

    @Override
    public int hashCode(){
        final int M = 43;
        int h = 0;
        for(T x: this) h = h*M + x.hashCode();
        return h;
    }

    @Override
    public boolean equals(Object o){
        if(o==this) return true;
        if(! (o instanceof Stack)) return false;
        Stack<T> s = (Stack<T>) o;
        if( s.size()!= this.size()) return false;
        Iterator<T> it1 = iterator(), it2 = s.iterator();
        while(it1.hasNext()){
            if(! it1.next().equals(it2.next()))
                return false;
        }//while
        return true;
    }
}
