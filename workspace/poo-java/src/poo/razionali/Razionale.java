package poo.razionali;

import java.lang.Math;
import poo.util.Mat;
import poo.util.Array;
import poo.util.IO;


public class Razionale implements Comparable<Razionale>{// oggetto immutabile
	private final int NUM, DEN;
	private static int contatore=0;

	public Razionale(int num, int den){
		// vogliamo ridurre ai minimi termini la frazione es. 4/8--> 1/2
		if(den==0) 
			throw new RuntimeException("Denominatore nullo");
      	if( num!=0 ){
    		int n=Math.abs( num ), d=Math.abs( den );
    		int cd=Mat.mcd( n, d );
    		num=num/cd; den=den/cd;
      	}
      	if( den<0 ){
        	num *= -1;
        	den *= -1;
      	}
      	NUM=num;
      	DEN=den;
      	contatore++;
	}//costruttore

	public Razionale(Razionale r){
		NUM = r.NUM;
		DEN = r.DEN;
		contatore++;
	}//costruttore per copia

	public int getNum(){ return NUM;}

	public int getDen(){ return DEN;}

	public static int quantiRazionali(){
		return contatore;
	}

	public Razionale mul(int s){
		return new Razionale(NUM*s, DEN);
	}

	public Razionale mul(Razionale r){
		return new Razionale(this.NUM*r.NUM,
		            		this.DEN*r.DEN);
	}

	public Razionale div(Razionale r){
		return new Razionale(this.DEN*r.DEN, this.DEN*r.NUM);
	}

	public Razionale add(Razionale r){
		int mcm = (this.DEN*r.DEN)/Mat.mcd(this.DEN, r.DEN);
		int d = mcm;
		int n = (mcm/this.DEN)*this.NUM + (mcm/r.DEN)*r.NUM;
		return new Razionale(n,d);
	}

	public Razionale sub(Razionale r){
		int mcm = (this.DEN*r.DEN)/Mat.mcd(this.DEN, r.DEN);
		int d = mcm;
		int n = (mcm/this.DEN)*this.NUM - (mcm/r.DEN)*r.NUM;
		return new Razionale(n,d);
	}

	public String toString(){
		if(DEN==1)
			return ""+NUM;
		return ""+NUM+"/"+DEN;
	}

	/*protected void finalize(){
		contatore--;
	}//distruttore*/

	public int compareTo(Razionale r){
		int mcm = (this.DEN*r.DEN)/Mat.mcd(this.DEN, r.DEN);
		int n1 = (mcm/this.DEN)*this.NUM;
		int n2 = (mcm/r.DEN)*r.NUM;
		return n1-n2;
	}
	public static void main(String[] args){
		Razionale r1 = new Razionale(1,3);
		Razionale r2 = new Razionale(1,4);
		Razionale r3 = new Razionale(1,2);
		Razionale r4 = new Razionale(28,3);
		System.out.println(r1.mul(r2));
		int confronto = r1.compareTo(r2);
		if(confronto>0) System.out.println("r1 é maggiore di r2");
		if(confronto==0) System.out.println("r1 é uguale a r2");
		if(confronto<0) System.out.println("r1 é minore di r2");
		Razionale[] v = {r1,r2,r3,r4};
		Array.selectionSort(v);
		IO.printlnArray(v);
		System.out.println(Array.ricercaBinaria(v, r3));
		System.out.println(Array.ricercaBinaria(v, new Razionale(3,2)));
	}
}//Razionale
