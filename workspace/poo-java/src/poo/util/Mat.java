package poo.util;

import java.util.HashMap;

public final class Mat{//una classe é final se non é estendebile.

	public enum Soluzione{ITERATIVA, RICORSIVA}
	private static HashMap<Integer, Integer> mapSommaDivisori = new HashMap<>();

	public static double montecarlo(long iterazioni){
		long colpiInterni = 0;
		long colpiTotali = 0;
		long k =0;
		double x,y,distanzaDaOrigine;
		while(k<iterazioni){			
			x = Math.random()*2-1;//genera numeri tra [-1,1]
            y = Math.random()*2-1;
            distanzaDaOrigine = Math.sqrt(x*x + y*y);
            colpiTotali++;
			if(distanzaDaOrigine<=1)
				colpiInterni++;
            k++;	
        }
		return 4*((double)colpiInterni/colpiTotali);
	}

	private Mat(){};//costruttore di default che non fa niene e privato affinché non si possano creare oggetti Mat
	
	public static final double EPSILON = 1.0E-10;

	public static boolean quasiUguali(double x, double y){
		//true se per le prime 10 cifre sono uguali
		return Math.abs(x-y)<=EPSILON;
	}

	public static int mcd(int x, int y){
		if(x<=0 || y<= 0)
			throw new IllegalArgumentException("Operandi non positivi");
		return mcd_Euclide(x,y);
	}

	private static int mcd_Euclide(int x, int y){
		if(y==0) return x;
		return mcd_Euclide(y,x%y);
	}

	public static int mcm(int x, int y){
		if(x<=0 || y<= 0)
			throw new IllegalArgumentException("Operandi non positivi");
		return (x*y)/mcd_Euclide(x,y);
	}

	public static int sommaDivisori(int x, Soluzione sol){
		if(x<=0) throw new IllegalArgumentException();
		if(mapSommaDivisori.containsKey(x)) return mapSommaDivisori.get(x);
		switch (sol) {
			case ITERATIVA:
				return sommaDivisoriIte(x);
			default:
				return sommaDivisoriRecursive(x, x/2);
		}
	}
	private static int sommaDivisoriIte(int x){
		int c = 0;
		for (int d = 1; d <= x/2 ; d++)
			if(x%d == 0) c+=d;
		return c;
	}
	private static int sommaDivisoriRec(int x, int d, int[] c){
		if( d == x/2 +1 ) return c[0];
		if( x%d == 0 ) c[0] = c[0]+d;
		return sommaDivisoriRec(x, d+1, c);
	}

	private static int sommaDivisoriRecursive(int x, int  d){
		if(d==1) return 1;
		if(x%d==0) return d+sommaDivisoriRecursive(x, d-1);
		return sommaDivisoriRecursive(x, d-1);
	}
	public static boolean amicabili(int x, int y){
		if(x<=0 || y<=0) throw new IllegalArgumentException();
		return y == sommaDivisori(x, Soluzione.ITERATIVA) &&
				x == sommaDivisori(y, Soluzione.RICORSIVA);
	}

	public static void main(String[] args) {
		for(int x=200; x<=20000; x++)
			for(int p=x+2; p<=20000; p+=2)
				if(amicabili(x,p)) System.out.println("<"+x+", "+p+">");
		/*
		int x = 220;
		int y = 284;
		System.out.println(amicabili(x,y));
		System.out.println(sommaDivisori(x, Soluzione.ITERATIVA));
		System.out.println(sommaDivisori(x, Soluzione.RICORSIVA));
		System.out.println(sommaDivisori(y, Soluzione.ITERATIVA));
		System.out.println(sommaDivisori(y, Soluzione.RICORSIVA));*/
	}
}//Mat
