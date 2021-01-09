package poo.agenda;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendinaSet extends AgendinaAstratta{
    private Set<Nominativo> tabella = new TreeSet<>();


    @Override
    public void aggiungi(Nominativo n) {
        tabella.remove(n);
        tabella.add(n);
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }

    public int size(){
        return tabella.size();
    }

    public void rimuovi(Nominativo n){
        tabella.remove(n);
    }
}
