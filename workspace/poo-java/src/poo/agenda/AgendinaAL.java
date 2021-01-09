package poo.agenda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AgendinaAL extends AgendinaAstratta {
    private ArrayList<Nominativo> tabella;

    public AgendinaAL(){
        this(100);
    }

    public AgendinaAL(int n){
        tabella = new ArrayList<>(100);
    }

    public int size(){ return tabella.size();}

    public void svuota(){ tabella.clear();}

    @Override
    public void aggiungi(Nominativo n) {
        int i = Collections.binarySearch(tabella, n);
        if(i>=0) tabella.set(i, n);
        i=0;
        while(i< tabella.size()){
            Nominativo m = tabella.get(i);
            if(m.compareTo(n)>0) break;
            i++;
        }
        tabella.add(i,n);
    }

    @Override
    public Nominativo cerca(Nominativo n){
        int i = Collections.binarySearch(tabella, n);
        if(i<0) return null;
        return tabella.get(i);
    }

    @Override
    public void rimuovi(Nominativo n){
        int i = Collections.binarySearch(tabella, n);
        if(i<0) return;
        tabella.remove(i);
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }
}//AgendinaAL