package proj_polinomi.util;

import java.util.Iterator;

public abstract class AbstractVector<T> implements Vector<T> {
    public String toString(){
        StringBuilder sb = new StringBuilder(300);
        sb.append('[');
        for(T e: this)
            sb.append(e+", ");
        if(sb.length()>1)            
            sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if(!(o instanceof Vector)) return false;
        if(o==this) return true;
        Vector<T> v = (Vector<T>) o;
        if(this.size()!= v.size()) return false;
        Iterator<T> it1 = iterator(), it2 = v.iterator();
        while(it1.hasNext()){
            T e1 = it1.next(), e2= it2.next();
        if(!e1.equals(e2)) return false;
        }
        return true;
    }//equals

    public int hashCode(){
        final int MUL = 43;
        int ret=0;
        for (int i=0; i<size(); i++)
            ret= ret*MUL + get(i).hashCode();
        return ret;
    }//hashCode
}//AbstractVector