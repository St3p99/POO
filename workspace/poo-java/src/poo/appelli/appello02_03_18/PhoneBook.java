package poo.appelli.appello02_03_18;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public interface PhoneBook extends Iterable<Persona>{
    default int size(){
        int c = 0;
        for(Persona p: this) c++;
        return c;
    }

    default void clear(){
        Iterator<Persona> it = iterator();
        while(it.hasNext()){
            it.next(); it.remove();
        }
    }

    void add(Persona p);
    void resort(Comparator<Persona> c);

    default List<Persona> search(String s){
        List<Persona> l = new LinkedList<>();
        for(Persona p: this)
            if((p.getNome()+p.getTelefono()).matches(s))
                l.add(p);
        return l;
    }

    default void remove(String s){
        Iterator<Persona> it = iterator();
        while(it.hasNext()){
            Persona p = it.next();
            if((p.getNome()+p.getTelefono()).matches(s)) it.remove();
        }
    }
}//PhoneBook
