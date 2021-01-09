package poo.figure;

import poo.util.Mat;

public class Quadrato extends Figura {//Quadrato é instanceof di Figura, FiguraPiana
  /*Una classe concreta che estende una classe abstract
    deve concretizzare tutti i metodi abstract della superclasse*/
    public Quadrato(double lato){
      super(lato);
    }

    public double getLato(){
      return getDimensione();
    }

    @Override
    public double perimetro(){
      return 4*getDimensione();
    }
    @Override
    public double area(){
      double l=getDimensione();
      return l*l;
    }
    
    public String toString(){
      return "Quadrato di lato="+String.format("%1.2f",getDimensione());
    }

    public boolean equals(Object o){
      if(!(o instanceof Quadrato)) return false;
      if(o==this) return true;
      Quadrato q = (Quadrato) o;
      return Mat.quasiUguali(this.getDimensione(),q.getDimensione());
    }

    public int hashCode(){
      Double d = Double.valueOf(getDimensione());
      return d.hashCode();
    }




}
