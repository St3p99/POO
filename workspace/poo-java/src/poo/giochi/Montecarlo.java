package poo.giochi;


public class Montecarlo{
    /**
     * Approssima il risultato dell'integrale definito
     * della funzione x^2 in un intervallo [a,b].
     * Si assume a < b.
     *
     * @param a estremo inferiore dell'intervallo
     * @param b estremo superiore dell'intervallo
     * @param iterazioni il numero di iterazioni da fare per approssimare in modo piú accurato il calcolo.
     * @return Il risultato approssimato dell'integrale definito della f(x)=x^2 in un intervallo [a,]
     ***/
    public static double xSquared(double a, double b, long iterazioni){
        double xMaxAbsValue = Math.max(Math.abs(a),Math.abs(b));
        double yMaxValue = xMaxAbsValue*xMaxAbsValue;
        double areaRettangolo = ((b-a)*yMaxValue);// base: lunghezza intervallo [a,b]; altezza: valMax di f(x)=x^2 in [a,b]
        long colpiInterni =0;
        long colpiTotali =0;
        long k = 0;
        double x,y;
        while(k<iterazioni){
            double randx= Math.random();
            double randy= Math.random();
            x = (1-randx)*a + randx*b; // x in [a, b]
            y = randy*yMaxValue; // y in [0, yMaxValue]
            colpiTotali++;
            if (y < x*x)
                colpiInterni++;
            if( k%1000000 == 0)
               System.out.println(((double)colpiInterni/colpiTotali)*areaRettangolo);
            k++;
        }
        return ((double)colpiInterni/colpiTotali)*areaRettangolo;
    }
    

    public static double pi(long iterazioni){//metodo montecarlo
        long colpiInterni=0;
        long colpiTotali=0;
        long k =0;
		double x,y,distanzaDaOrigine;
		while(k<iterazioni){		
			x = Math.random()*2-1;//genera numeri tra [-1,1]
            y = Math.random()*2-1;
            distanzaDaOrigine = Math.sqrt(x*x + y*y);
            colpiTotali++;
			if(distanzaDaOrigine<=1)
                colpiInterni++;
            if(k%1000000==0)                
                System.out.println(4*((double)colpiInterni/colpiTotali));	  
            k++;	
        }
        return 4*((double)colpiInterni/colpiTotali);
	}
    public static void main(String[] args) {
        //final double PI = pi(Long.MAX_VALUE);
        //System.out.println(PI);
        final double xSqrd = xSquared(-1,7,Long.MAX_VALUE);
        System.out.println(xSqrd);
    }
}