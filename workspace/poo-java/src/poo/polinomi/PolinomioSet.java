package poo.polinomi;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PolinomioSet extends PolinomioAstratto {
    private Set<Monomio> contenuto = new TreeSet<>();

    public PolinomioSet(){}
    public PolinomioSet(Polinomio p){
        for(Monomio m: p) this.add(m);
    }

    public PolinomioSet(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    @Override
    public Polinomio crea() {
        return new PolinomioSet();
    }

    @Override
    public void add(Monomio m) {
        if (m.getCoeff() == 0) return;
        if (!(contenuto.contains(m))) contenuto.add(m);
        else {
            Iterator<Monomio> it = contenuto.iterator();
            Monomio m1 = null;
            while (it.hasNext()) {
                m1 = it.next();
                if (m1.equals(m)) {
                    it.remove();
                    break;
                } // trovato
            }
            m1 = m1.add(m);
            if (m1.getCoeff() != 0) contenuto.add(m1);
        }
    }

    @Override
    public Iterator<Monomio> iterator() {
        return contenuto.iterator();
    }
}
