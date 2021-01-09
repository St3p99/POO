package proj_polinomi.polinomi;

import proj_polinomi.util.ArrayVector;
import proj_polinomi.util.Vector;
import java.util.Iterator;

public class PolinomioVector extends PolinomioAstratto {
    Vector<Monomio> polinomio  = new ArrayVector<>();

    public PolinomioVector(){}

    public PolinomioVector(Polinomio p){
        for(Monomio x: p) this.add(x);
    }

    public PolinomioVector(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    public int size() {
        return polinomio.size();
    }

    public Polinomio crea() {
        return new PolinomioVector();
    }

    public void add(Monomio m) {
        if(m.getCoeff()==0) return;
        for(int i = 0; i<polinomio.size(); i++){
            Monomio x = polinomio.get(i);
            if(x.compareTo(m)>0){ polinomio.add(i, m); return;}
            if(x.equals(m)){
                int somma = x.getCoeff() + m.getCoeff();
                if(somma==0){
                    polinomio.remove(i); return;
                }
                polinomio.set(i, new Monomio(somma, x.getGrado()));
                return;
            }
        }
        polinomio.add(m);
    }

    public Iterator<Monomio> iterator() {
        return polinomio.iterator();
    }
}