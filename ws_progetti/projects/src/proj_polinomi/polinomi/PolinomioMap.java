package proj_polinomi.polinomi;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;

public class PolinomioMap extends PolinomioAstratto {
    private Map<Monomio, Monomio> mappa = new TreeMap<>();

    public PolinomioMap(){}
    public PolinomioMap(Polinomio p){
        for(Monomio m: p) this.add(m);
    }
    public PolinomioMap(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    public int size(){
        return mappa.size();
    }

    @Override
    public PolinomioMap crea() {
        return new PolinomioMap();
    }

    @Override
    public void add(Monomio m) {
        if(m.getCoeff()==0) return;
        if(! mappa.containsKey(m))
            mappa.put(m, m);
        else{
            int somma = mappa.get(m).getCoeff() + m.getCoeff();
            if(somma==0) mappa.remove(m);
            else {
                Monomio m1 = new Monomio(somma, m.getGrado());
                mappa.put(m1,m1);
            }
        }
    }//add

    public boolean contains(Monomio m){
        return mappa.containsKey(m) && mappa.get(m).getCoeff() == m.getCoeff();
    }

    @Override
    public Iterator<Monomio> iterator() {
        return mappa.keySet().iterator();
    }

    public static void main(String[] args) {
        PolinomioMap p = new PolinomioMap();
        p.add(new Monomio(2,3));
        p.add(new Monomio(1,3));
        p.add(new Monomio(-3,3));
        p.add(new Monomio(1,3));
        System.out.println(p);
    }
}//PolinomioMap
