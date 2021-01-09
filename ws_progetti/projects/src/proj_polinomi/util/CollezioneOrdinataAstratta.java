package proj_polinomi.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
   public int hashCode(){
       int h = 0;
       final int M = 83;
       Iterator<T> it = this.iterator();
       while(it.hasNext()){
           h = h*M + it.next().hashCode();
       }
       return h;
   }//hashCode

    public boolean equals(Object o){
        if( ! (o instanceof CollezioneOrdinata)) return false;
        if( o==this ) return true;
        CollezioneOrdinata<T> co = (CollezioneOrdinata<T>) o;
        if( this.size() != co.size()) return false;
        //essendo di tipo CollezioneOrdinata (generica) non possiamo usare i puntatori
        Iterator<T> it1= iterator(), it2 = co.iterator();
        while( it1.hasNext() ){
            if( ! it1.next().equals(it2.next())) return false;
        }//while
        return true;
    }//equals

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
       return sb.toString();
    }


}


