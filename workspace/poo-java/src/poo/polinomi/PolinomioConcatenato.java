package poo.polinomi;

import java.util.Iterator;
import poo.util.ListaConcatenataOrdinata;


public class PolinomioConcatenato extends PolinomioAstratto {
    private ListaConcatenataOrdinata<Monomio> polinomio = new ListaConcatenataOrdinata<>();

    public PolinomioConcatenato(){}

    public PolinomioConcatenato(Polinomio p){
        for (Monomio m : p )
            this.add(m);
    }

    public PolinomioConcatenato(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    public int size() {
        return polinomio.size();
    }

    public Polinomio crea() {
        return new PolinomioConcatenato();
    }

    public void add(Monomio m) {
        if(m.getCoeff() ==0 ) return;
        Monomio q = polinomio.get(m);
        if(q==null) polinomio.add(m);
        else {
            polinomio.remove(m);
            int somma = q.getCoeff() + m.getCoeff();
            if(somma!=0) polinomio.add(new Monomio(somma, m.getGrado()));
        }
    }//add

    public Iterator<Monomio> iterator() {
        return polinomio.iterator();
    }

    public static void main(String[] args) {

    }

}
