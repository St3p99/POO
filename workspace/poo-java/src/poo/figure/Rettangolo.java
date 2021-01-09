package poo.figure;

import poo.util.Mat;

public class Rettangolo extends Figura{//Rettangolo é instanceof di Figura, FiguraPiana
    private double altezza;

    public Rettangolo(double b, double h){
        super(b);
        if(h<=0)
            throw new IllegalArgumentException("Dimensione nulla o negativa");
        altezza=h;
    }

    public double getBase(){
        return getDimensione();
    }

    public double getAltezza(){
        return altezza;
    }

    @Override
    public double perimetro() {
        return getDimensione()+altezza;
    }

    @Override
    public double area() {
        return getDimensione()*altezza;
    }

    public String toString(){
        return "Rettangolo di base="+String.format("%1.2f",getDimensione())+" e altezza="+
                String.format("%1.2f",altezza);
    }
    public boolean equals(Object o){
        if(!(o instanceof Rettangolo)) return false;
        if(o==this) return true;
        Rettangolo q = (Rettangolo) o;
        return Mat.quasiUguali(this.getDimensione(),q.getDimensione()) && Mat.quasiUguali(this.altezza,q.altezza);
    }

    public int hashCode(){
        final int MUL=43;
        Double p = MUL*altezza + getDimensione();
        return p.hashCode();
    }

    public static void main(String[] args){
        Rettangolo r1 = new Rettangolo(3,4);
        Rettangolo r2 = new Rettangolo(3,4);
        System.out.println(r1.equals(r2));
        System.out.println(r1.hashCode());
        System.out.println(r2.hashCode());

    }
}

