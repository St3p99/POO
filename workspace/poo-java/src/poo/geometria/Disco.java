//****EREDITARIETA****
//Sará l'estensione della classe Punto
package poo.geometria;
import java.lang.Math;

public class Disco extends Punto implements FiguraPiana{//Disco é instanceof di Punto, FiguraPiana
	private double raggio;;

	public Disco(double r){
		//centrato nell'origine
		super();//potevamo evitarlo, viene invocato autom. il costr. di default di Punto=SUPERCLASSE
		if(r<=0)
			throw new IllegalArgumentException("raggio nullo o negativo");
		raggio=r;
	}//costruttore di default

	public Disco(double x, double y, double r){
		super(x,y);
		if(r<=0)
			throw new IllegalArgumentException("raggio nullo o negativo");
		raggio=r;
	}//costruttore

	public Disco(Punto p, double r){
		super(p);
		if(r<=0)
			throw new IllegalArgumentException("raggio nullo o negativo");
		raggio=r;
	}

	public Disco(Disco d){//Disco erede di Punto ==> Disco é un Punto
		super(d.getX(), d.getY());
		if(d.raggio<=0)
			throw new IllegalArgumentException("raggio nullo o negativo");
		raggio=d.raggio;
	}//costruttore per copia

	public double getRaggio(){ return raggio;}

	public double perimetro(){
		return 2*Math.PI;
	}//perimetro

	public double area(){
		return raggio*raggio*Math.PI;
	}

	public String toString(){//perfezionamento del metodo ereditato toString() dalla superclass Punto
		return "Disco di centro= "+super.toString()+" e raggio= "+raggio;
	}

	public int hashCode(){
		Double d= Double.valueOf(super.getX());
		Double d1= Double.valueOf(super.getY());
		Double d2= Double.valueOf(raggio);
		int primo = 43;
		int hC=d.hashCode();
		hC = hC*primo+d1.hashCode();
		hC = hC*primo+d2.hashCode();
		return hC;
	}

	public static void main(String[] args){
		Punto p = new Punto(3,4);
		Disco d1 = new Disco(5);
		Disco d2 = new Disco(p,5);
		double dist1 = p.distanza(d1);// distanza si aspetta un punto, ma un Disco IS A Punto
		double dist2 = p.distanza(d2);// distanza si aspetta un punto, ma un Disco IS A Punto
		System.out.println("area d1= "+d1.area());
		System.out.println("area d2= "+d2.area());
		System.out.println("Distanza tra punto e disco1: "+dist1);
		System.out.println("Distanza tra punto e disco2: "+dist2);
		p=d1;
		System.out.println(p);
		System.out.println(d1 instanceof FiguraPiana);
	}
}
