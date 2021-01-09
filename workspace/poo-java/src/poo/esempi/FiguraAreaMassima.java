package poo.esempi;

import poo.geometria.Disco;
import poo.geometria.FiguraPiana;
import poo.figure.*;



public class FiguraAreaMassima{
    public static FiguraPiana areaMassima(FiguraPiana f[]){
        FiguraPiana fam=null;
        double am= 0.0D;
        for(int i=0; i<f.length;i++){
            double a = f[i].area();
            if(a>am){
                am=a;
                fam=f[i];
            }
        }
        return fam;
  }

  public static void main(String[] args){
    //Bisogna utilizzare il tipo piú generale
    FiguraPiana[] v = new FiguraPiana[5];
    v[0]= new Cerchio(3.4);
    v[1]= new Rombo(3,7);
    v[2]= new Rettangolo(2,5);
    v[3]= new Quadrato(2);
    v[4]= new Disco(3);
    FiguraPiana fam = areaMassima(v);
    System.out.println(fam + " ha l'area massima="+ String.format("%1.3f", fam.area()));
  }
}
