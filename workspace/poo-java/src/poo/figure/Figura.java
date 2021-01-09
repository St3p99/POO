package poo.figure;

import poo.geometria.FiguraPiana;

public abstract class Figura implements FiguraPiana {
  //Figura é instanceof FiguraPiana
  //abstract-->non instanziabile e almeno un metodo dovrá essere abstract
  private double dimensione;
  public Figura(double d){//serve perché lo useranno le subclass
    if(d<=0)
      throw new IllegalArgumentException("Dimensione nulla o negativa");
    dimensione=d;
  }

  protected double getDimensione(){//protectede--> visibile solo alle subclass
    return dimensione;
  }//getDimensione

  //i metodi abstract si limitano all'intestazione, a prevederne l'esistenza --- NON METTERE MAI {}
  /*
  Non occorre inserire i metodi abstract perimetro e area in quanto Figura implements FiguraPiana che 
  giá prevede quei metodi. public abstract double perimetro(); public abstract double area();*/

  public String toString(){
    return "Figura di dimensione="+String.format("%5.2f",dimensione);
  }

}
