package poo.polinomi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class PolinomioLL extends PolinomioAstratto{
    private LinkedList<Monomio> lista = new LinkedList<>();

    public PolinomioLL(){};

    public PolinomioLL(Polinomio p){
        for(Monomio m: p) this.add(m);
    }

    public PolinomioLL(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    @Override
    public PolinomioLL crea() { //covarianza del tipo di ritorno
        return new PolinomioLL();
    }//crea

    @Override
    public void add(Monomio m) {
        //si mantiene la lista continuamente ordinata per gradi decrescenti
        if(m.getCoeff()==0) return;
        ListIterator<Monomio> lit = lista.listIterator();
        boolean flag = false; // true quando é sistemato
        while(lit.hasNext() && !flag){
            Monomio m1 = lit.next();
            if(m.equals(m1)){ //monomi simili
                Monomio m2 = m.add(m1);
                if(m2.getCoeff()!=0)
                    lit.set(m2);
                else
                    lit.remove();
                flag = true;
            }//if monomi simili
            else if(m1.compareTo(m)>0){
                lit.previous(); lit.add(m); flag = true;
            }
        }
        if(!flag) lit.add(m);
    }

    @Override
    public Iterator<Monomio> iterator() {
        return lista.iterator();
    }

    public int size(){
        return lista.size();
    }
}
