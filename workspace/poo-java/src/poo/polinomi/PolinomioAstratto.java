package poo.polinomi;

import java.util.Iterator;

public abstract class PolinomioAstratto implements Polinomio{
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        Iterator<Monomio> it = iterator();
        boolean flag = true;
        if(!it.hasNext()) return "0";
        while(it.hasNext()){
            Monomio m = it.next();
            if(m.getCoeff()>0 && !flag) sb.append("+");
            if(flag) flag = !flag;
            sb.append(m);
        }
        return sb.toString();
    }//toString

    @Override
    public boolean equals(Object o){
        if( ! (o instanceof Polinomio)) return false;
        if( o == this) return true;
        Polinomio p = (Polinomio) o;
        if(this.size() != p.size()) return false;
        Iterator<Monomio> it = this.iterator();
        for( Monomio m : p){
            Monomio q = it.next();
            if( m.getCoeff() != q.getCoeff() ||
                m.getGrado()!= q.getGrado()) return false;
        }
        return true;
    }//equals

    @Override
    public int hashCode(){
        final int MUL = 43;
        int h =0;
        for(Monomio m: this){
            h = h*MUL + m.hashCode();
        }
        return h;
    }
}
