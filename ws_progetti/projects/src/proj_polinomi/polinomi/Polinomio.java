package proj_polinomi.polinomi;

public interface Polinomio extends Iterable<Monomio>{
    default int size(){
        int size = 0;
        for(Monomio x: this) size++;
        return size;
    }

    Polinomio crea(); //factory

    void add(Monomio m);

    default Polinomio add( Polinomio p ){
        Polinomio somma = crea();
        for(Monomio m: this) somma.add(m);
        for(Monomio m: p) somma.add(m);
        return somma;
    }//add

    default Polinomio mul( Polinomio p ){
        Polinomio prodotto = crea();
        for(Monomio m: this){
            prodotto = prodotto.add( p.mul(m) );
        }
        return prodotto;
    }//mul

    default Polinomio mul( Monomio m ){
        Polinomio prodotto = crea();
        for(Monomio m1: this){
            prodotto.add(m1.mul(m));
        }
        return prodotto;
    }//mul

    default boolean contains(Monomio m){
        for(Monomio x: this)
            if(x.equals(m) && x.getCoeff()==m.getCoeff()) return true;
        return false;
    }

    default Polinomio derivata(){
        Polinomio der = crea();
        for(Monomio m: this){
            if( m.getGrado()>0 ) der.add(new Monomio(m.getCoeff()*m.getGrado(),m.getGrado()-1));
        }
        return der;
    }

    default double valore(double x){
        double v = 0D;
        for(Monomio m: this){
            v += m.getCoeff()*Math.pow(x,m.getGrado());
        }
        return v;
    }//valore
}//Polinomio