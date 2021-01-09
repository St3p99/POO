package poo.agenda;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class AgendinaLL extends AgendinaAstratta {
    private LinkedList<Nominativo> tabella =
            new LinkedList<>();//la capicitá corrisponde agli elementi correnti


    @Override
    public void aggiungi(Nominativo n) {
        // aggiunge n in ordine, evitando omonimie
        // se n é giá presente, sovrascrive
        ListIterator<Nominativo> lit = tabella.listIterator();
        boolean flag = false;
        while(lit.hasNext() && !flag){
            Nominativo m = lit.next();
            int comparazione = m.compareTo(n);
            if(comparazione == 0) {
                lit.set(n); flag = true;}
            else if(comparazione > 0) {
                 lit.previous();
                 lit.add(n); flag = true;
            }
        
        }
        if(!flag) tabella.add(n);
    }

    @Override
    public void svuota(){
        tabella.clear();
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }

}