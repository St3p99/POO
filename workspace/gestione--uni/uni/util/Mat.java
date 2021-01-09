package util;

public final class Mat{//una classe é final se non é estendebile.
	
	private Mat(){};//costruttore di default che non fa niene e privato affinché non si possano creare oggetti Mat
	public static double EPSILON = 1.0E-10;

	public static boolean isEqual(double x, double y){
		//true se entro le prime 10 cifre sono uguali
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
}//Mat