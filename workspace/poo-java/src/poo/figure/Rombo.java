package poo.figure;

import poo.util.Mat;

import java.lang.Math;
public class Rombo extends Figura{//Rombo é instanceof di Figura, FiguraPiana
  private double dMinore;

  public Rombo(double dMaggiore, double dMinore){
      super(dMaggiore);
      if(dMinore<=0)
          throw new IllegalArgumentException("Dimensione nulla o negativa");
      this.dMinore=dMinore;
  }

  public double getDiagMinore(){
      return dMinore;
  }

  public double getDiagMaggiore(){
      return super.getDimensione();
  }

  public double lato(){
      return Math.sqrt((getDimensione()*getDimensione())/4+(dMinore*dMinore)/4);
  }

  public double perimetro(){
      return 4*this.lato();
  }


  public double area(){
      return (dMinore*getDimensione())/2;
  }

  public String toString(){
      return "Rombo con diagonale maggiore="+String.format("%1.2f",getDimensione())+" e diagonale minore="+
              String.format("%1.2f",dMinore);
  }


  public boolean equals(Object o) {
      if(!(o instanceof Rombo)) return false;
      if(o==this) return true;
      Rombo r = (Rombo) o;
      return Mat.quasiUguali(this.getDimensione(),r.getDimensione())&&Mat.quasiUguali(this.dMinore,r.dMinore);
  }

  public int hashCode(){
      final int MUL=43;
      Double p = MUL*dMinore + getDimensione();
      return p.hashCode();
  }

  public static void main(String[] args){
      Rombo r = new Rombo(4,2);
      System.out.println(r);
      System.out.println(r.lato());
      System.out.println(r.perimetro());

  }
}
