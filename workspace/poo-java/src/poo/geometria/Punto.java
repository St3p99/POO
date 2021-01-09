package poo.geometria;

import java.lang.Math;

public class Punto{  // classe mutabile
	// vogliamo catturare il concetto di punto in un piano cartesiano.
	//NOTA: un nome tutto maiuscolo indica una COSTANTE
	private double x,y; // variabili di istanza
	
	public Punto(){  // costruttore di default
		this(0,0);
	}
	public Punto(double x, double y){  // costruttore
		this.x = x;
		this.y = y;
	}
	public Punto(Punto p){  // costruttore per copia
		x = p.x;
		y = p.y;
	}

	public double getX(){ return x;}  // metodo accessore

	public double getY(){ return y;}  // metodo accessore

	public void muovi(double x, double y){//metodo mutatore
		this.x = x;
		this.y = y;
	}//muovi

	public double distanza(Punto p){  // metodo accessore
		return Math.sqrt((p.x - x)*(p.x - x) + (p.y-y)*(p.y-y));
	}//distanza

	public String toString(){
//		return "Punto<"+x+","+y+">";
		return "Punto<"+String.format("%7.3f",x)+","+String.format("%7.3f",y)+">";
	}//toString

	public boolean equals(Object o){
		if(!(o instanceof Punto)) return false;
		if(o==this) return true;
		Punto p = (Punto) o;
		return this.x==p.x && this.y==p.y;
	}

	public int hashCode(){
		Double d1 = Double.valueOf(x);
		Double d2 = Double.valueOf(y);
		int primo = 43;
		return d1.hashCode()*primo+d2.hashCode();
	}

	public static void main(String[] args){
		Punto p0 = new Punto(7,2);
		Punto p1 = new Punto(-2,4);
		double d = p0.distanza(p1);
		System.out.println("distanza"+p0+" e "+p1+"="+d);
	}//main
}//Punto

