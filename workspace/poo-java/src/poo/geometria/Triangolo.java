package poo.geometria;
import poo.util.*;

public class Triangolo implements FiguraPiana{
	// classe mutabile
	private Punto p0,p1,p2;  // vertici triangolo
	private double a, b, c;  // lunghezza lati triangolo
	public enum Tipo{ISOSCELE, SCALENO, EQUILATERO, RETTANGOLO, RETTANGOLO_ISOSCELE};

	public Triangolo(Punto p0, Punto p1, Punto p2){  // costruttore
		this.p0 = new Punto(p0);
		this.p1 = new Punto(p1);
		this.p2 = new Punto(p2);
		a = p0.distanza(p1);
		b = p0.distanza(p2);
		c = p1.distanza(p2);
		if(!esistenzaTrng())
			throw new IllegalArgumentException("***ATTENZIONE il triangolo creato non esiste"+
			"in quanto\n   i punti dati sono allineati.***");
	}

	public Triangolo(Triangolo t){
		p0 = new Punto(t.p0);
		p1 = new Punto(t.p1);
		p2 = new Punto(t.p2);
		a = t.a;
		b = t.b;
		c = t.c;
	}

	public boolean esistenzaTrng(){
		// Verifica se il triangolo esiste. Se i vertici del triangolo sono allineati ==> il triangolo non esiste
		// con l'eq. y=mx+q sono esprimibili tutte le rette eccetto le parallele all'asse y ---> x=x0
		if(p0.getX()==p1.getX() && p0.getX()==p2.getX())
			return false;
		double m01 = (p1.getY()-p0.getY())
			       /(p1.getX()-p0.getX());  // pendenza retta passante per p0 e p1
		double q01 = p0.getY() - m01*p0.getX();
		double m02 = (p2.getY()-p0.getY())
	    	/(p2.getX()-p0.getX());  // pendenza retta passante per p0 e p2
		double q02 = p0.getY() - m02*p0.getX();
		return !(m01==m02 && q01==q02);
	}//esistenzaTrng


	public Punto[] getVertici(){
		Punto[] v = new Punto[3];
		v[0]= new Punto(p0);
		v[1] = new Punto(p1);
		v[2]= new Punto(p2);
		return new Punto[]{new Punto(p0), new Punto(p1), new Punto(p2)};
	}

	public double getLatoA(){return a;}
	public double getLatoB(){return b;}
	public double getLatoC(){return c;}

	public double perimetro(){return a+b+c;}

	public double area(){
		double sp = this.perimetro()/2;  //semi-perimetro
		return Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));  //formula di Erone
	}

	public Double[] getAngles(){
		double x = Math.acos((c*c + b*b - a*a)/(2*c*b));
		double y = Math.acos((c*c + a*a - b*b)/(2*a*c));
		double z = Math.acos((a*a + b*b - c*c)/(2*a*b));
		return new Double[]{Math.toDegrees(x),Math.toDegrees(y),Math.toDegrees(z)};
	}

	public Tipo tipo(){
		if(a==b && b==c)
			return Tipo.EQUILATERO;
		if(a==b || b==c || a==c)
			if(eRettangolo())
				return Tipo.RETTANGOLO_ISOSCELE;
			else
				return Tipo.ISOSCELE;
		if(eRettangolo())
			return Tipo.RETTANGOLO;
		return Tipo.SCALENO;
	}

	private boolean eRettangolo(){
		Double[] angoli = getAngles();
		for(Double angolo :angoli)
			if(Mat.quasiUguali(angolo,90))
				return true;
		return false;
	}

	public String toString(){
		return "Triangolo di vertici: \n 1)"+p0+", 2)"+p1+", 3)"+p2+
		"; \ncon perimetro: "+this.perimetro()+", e area: "+this.area();
	}

	public int hashCode(){
        int primo = 43;
        int hC=p0.hashCode();
		hC = hC*primo + p1.hashCode();
		hC = hC*primo + p2.hashCode();
        return hC;
    }

    public boolean equals(Object o){
        if(!(o instanceof Triangolo)) return false;
        if(o==this) return true;
        Triangolo t = (Triangolo) o;
		Punto[] verticiThis = {p0,p1,p2};
		Punto[] verticiT = {t.p0,t.p1,t.p2};
		for (Punto puntoThis : verticiThis)
			if(Array.ricercaLineare(verticiT, puntoThis)==-1)
				return false;
		return true;
    }

	public static void main(String[] args){
		Punto p0 = new Punto(3,0);
		Punto p1 = new Punto(3,3);
		Punto p2 = new Punto();	
		//Ciao
		Triangolo t0 = new Triangolo(p0,p1,p2);
		Triangolo t1 = new Triangolo(p1,p2,p0);
		System.out.println(t0.tipo());
		System.out.println(t1.tipo());
		System.out.println(t0.perimetro());
		System.out.println(t1.perimetro());
		System.out.println(t0.area());
		System.out.println(t1.area());
		System.out.println(t1.equals(t0));

	}//main
}//Triangolo
