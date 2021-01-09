package poo.appelli.appelloStraordinario;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class ElencoLL<T extends Comparable<? super T>> extends ElencoAstratto<T> {
    private LinkedList<T> elenco = new LinkedList<>();

    @Override
    public int size() {
        return elenco.size();
    }

    @Override
    public void clear() {
        elenco.clear();
    }

    public void add(T x) {
        elenco.add(x);
    }

    @Override
    public void ordina() {
        Collections.sort(elenco);
    }

    @Override
    public Iterator iterator() {
        return elenco.iterator();
    }
}
