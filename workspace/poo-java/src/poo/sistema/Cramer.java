package poo.sistema;

import java.util.Locale;

import poo.util.Mat;
import poo.util.Matrix;

public class Cramer extends Sistema{
    double[][] c;//matrice dei coefficienti
    double[] t;// vettore termini noti

    public Cramer(double[][] a, double[] y) {
        super(a, y);
        this.c = new double[a.length][a.length];
        this.t = new double[y.length];
        for (int i = 0; i < a.length; i++){
            System.arraycopy(a[i], 0, this.c[i], 0, a.length);
            //              (src, srcPos, dest, destPost, length)
            this.t[i] = y[i];
        }
    }

    @Override
    public double[] risolvi() {
        double det = Matrix.determinante(c);
        if(Mat.quasiUguali(det, 0D)) throw new RuntimeException("Sistema singolare");
        int n = getN();
        double[] x = new double[n];
        for(int j=0; j<n; j++){//per ogni colonna scambio la colonna j con il vettore termini noti
            for (int i = 0; i <n; i++){
                double park = t[i]; t[i] = c[i][j];
                c[i][j] = park;
            }
            x[j] = Matrix.determinante(c)/det;
            for (int i = 0; i <n; i++){
                double park = t[i]; t[i] = c[i][j];
                c[i][j] = park;
            }
        }//for_j
        return x;
    }

    public String toString(){
        int n = getN();
        StringBuilder sb = new StringBuilder(500);
        for (int i = 0; i < n; i++) { 
            sb.append("[");
            for (int j = 0; j <n; j++){//includiamo i termini noti
                sb.append(String.format(Locale.ROOT,"%10.2f", c[i][j]));
                if(j==n-1) sb.append("|");
                else sb.append(", ");
            }
            sb.append(String.format(Locale.ROOT, "%10.2f", t[i])+"]\n");
        }//for_i
        return sb.toString();
    }

    public static void main(String[] args) {
        double[][] a = {{2,1,-1},
                       {3,-2,+2},
                       {1,-3,-3}};
        double[]   b = {5,-3,-2};

        Cramer c = new Cramer(a,b);
        poo.util.IO.printlnArray(c.risolvi());
        System.out.println(c);
    }
    
    
}