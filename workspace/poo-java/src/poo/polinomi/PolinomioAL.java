package poo.polinomi;

import java.util.ArrayList;
import java.util.Iterator;

public class PolinomioAL extends PolinomioAstratto {
    private ArrayList<Monomio> lista = new ArrayList<>();


    public PolinomioAL(){};

    public PolinomioAL(Polinomio p){
        for(Monomio m: p) this.add(m);
    }

    public PolinomioAL(String linea){
        this(ValutaPolinomio.interpretaPolinomio(linea));
    }

    @Override
    public int size(){ return lista.size(); }

    @Override
    public PolinomioAL crea() {
        return new PolinomioAL();
    }

    @Override
    public void add(Monomio m) {
        if(m.getCoeff()==0) return;
        for(int i=0; i<lista.size(); i++){
            Monomio m1 = lista.get(i);
            if(m.compareTo(m1) < 0) {lista.add(i,m); return;}
            else if(m.equals(m1)){//monomi simili
                int somma = m1.getCoeff()+m.getCoeff();
                if(somma == 0){
                    lista.remove(i); return;
                }
                else{
                    lista.set(i, new Monomio(somma, m.getGrado())); return ;
                }
            }//if_equals
        }//for
        lista.add(m);
    }

    @Override
    public Iterator<Monomio> iterator() {
        return lista.iterator();
    }
}
