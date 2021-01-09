package poo.agenda;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AgendinaMap extends AgendinaAstratta{
    Map<Nominativo, Nominativo> tabella = new TreeMap<>();
    int x = 3;

    @Override
    public int size(){
        return tabella.size();
    }

    @Override
    public void svuota(){
        tabella.clear();
    }

    @Override
    public void aggiungi(Nominativo n) {
        tabella.put(n,n);
    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.keySet().iterator();
    }

    @Override
    public void rimuovi(Nominativo n){
        tabella.remove(n);
    }

    @Override
    public Nominativo cerca(Nominativo n){
        return tabella.get(n);
    }
}//AgendinaMap
