package poo.sistema;

import java.util.Locale;

import poo.util.Mat;

public class Gauss extends Sistema{
    protected double[][] a;
    public Gauss(double[][] a, double[] y){
        super(a,y);
        this.a = new double[a.length][a.length+1];//+1--> i termini noti
        for (int i = 0; i < a.length; i++){
            System.arraycopy(a[i], 0, this.a[i], 0, a.length);
            //              (src, srcPos, dest, destPost, length)
            this.a[i][a.length] = y[i]; // [a.length] non va fuori dalla matrice poiché abbiamo aggiunto una colonna a this.a
        }
    }

    @Override
    public double[] risolvi(){ 
        triangolarizza();
        double[] x = calcolaSoluzione();
        return x;
    }

    protected void triangolarizza(){
        int n=getN();
        //elemento di pivot a[j][j]
        for(int j=0; j<n; j++){//j= riga di pivot
            if(Mat.quasiUguali(a[j][j],0D)){
                int p = j+1;
                while(p<n && Mat.quasiUguali(a[p][j], 0D))
                    p++;
                if(p==n)//gli elementi sotto a[j][j] sono tutti nulli, a[j][j] compreso
                    throw new RuntimeException("Sistema singolare");
                //else sostituire riga_j con riga_i
                double[] tmp = a[j];
                a[j]=a[p]; a[p]=tmp;
            }//if_j_j
            for (int i = j+1; i <n; i++)//azzera tutti gli elementi sotto a[j][j]
                if(!Mat.quasiUguali(a[i][j],0D)){
                    double c = a[i][j]/a[j][j]; //coeff. moltiplicativo
                    // comb. lineare: a[i] - a[j]*c
                    for (int k = j; k <=n; k++)//k indice di colonna; ricorda ci sono n+1 colonne (vettore term. noti)
                        a[i][k]=a[i][k] - a[j][k]*c;
                }//if_i_j
        }//for_j
    }//triangolarizza


    protected double[] calcolaSoluzione(){
        int n=getN(); double[] x = new double[n];
        for (int i=n-1; i>=0; --i) {
            double sm = a[i][n];
            for (int k=i+1; k<n; k++) //spostiamo al secondo membro (sm) tutti i termini incogniti giá calcolati (con i coefficienti)
                sm = sm - a[i][k]*x[k];
            x[i] = sm/a[i][i];
        }//for_i
        return x;
    }

    public String toString(){
        int n = getN();
        StringBuilder sb = new StringBuilder(500);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=n; j++)//includiamo i termini noti
                sb.append(String.format(Locale.ROOT,"%10.2f", a[i][j]));
            sb.append("\n");
        }//for_i
        return sb.toString();
    }

    public static void main(String[] args) {
        double[][] a = {{2,1,-1},
                       {3,-2,+2},
                       {1,-3,-3}};
        double[]   b = {5,-3,-2};

        double[][] test = {{2,3,4},{0.5,2,0},{5,4,1}};
        Gauss sistema = new Gauss(a, b);
        Gauss sistema2 = new Gauss(test, b);
        poo.util.IO.printlnArray(sistema.risolvi());
        poo.util.IO.printlnArray(sistema2.risolvi());
        System.out.println(sistema);
        System.out.println(sistema2);
    }
}