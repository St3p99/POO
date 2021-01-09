package poo.figure;

import poo.util.Mat;
import java.lang.Math;

public class Cerchio extends Figura{//Cerchio é instanceof di Figura, FiguraPiana
  public Cerchio(double r){
      super(r);
  }

  public double getRaggio(){
      return getDimensione();
  }

  @Override
  public double perimetro(){//circonferenza
      return 2*Math.PI*super.getDimensione();
  }

  @Override
  public double area(){
      return Math.PI*Math.pow(getDimensione(),2);
  }

  public String toString(){
      return "Cerchio di raggio="+String.format("%1.2f",getDimensione());
  }

  public boolean equals(Object o){
      if(!(o instanceof Cerchio)) return false;
      if(o==this) return true;
      Cerchio c = (Cerchio) o;
      return Mat.quasiUguali(this.getDimensione(),c.getDimensione());
  }

  public int hashCode(){
      Double d = Double.valueOf(getDimensione());
      return d.hashCode();
  }


}
