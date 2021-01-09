package poo.appelli.appelloStraordinario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;

public interface Elenco<T extends Comparable<? super T>> extends Iterable<T>{
    default int size(){
        int c = 0;
        for(T x: this) c++;
        return c;
    }

    default void clear(){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            it.next(); it.remove();
        }
    }

    void add(T x);

    default void compatta(){
        LinkedHashSet<T> set = new LinkedHashSet<>();
        for(T x: this) set.add(x);
        this.clear();
        for(T x: set) this.add(x);
    }

    default void remove(T x){
        Iterator<T> it = iterator();
        while(it.hasNext()){
            if(it.next().equals(x)) it.remove();
        }
    }

    default void scarica(String nomeFile) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(nomeFile));
        for(T x: this) pw.println(x);
        pw.close();
    }

    void ordina();
}
