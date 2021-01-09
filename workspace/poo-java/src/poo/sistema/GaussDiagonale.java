package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss{

    public GaussDiagonale(double[][] a, double[] y){
        super(a,y);
    }//costruttore normale

    @Override
    protected void triangolarizza(){//diagonalizza
        super.triangolarizza();
        int n=getN();
        for (int j = 1; j<n; j++)
            for (int i = j-1; i>-1; i--) {
                if (!Mat.quasiUguali(a[i][j], 0D)){
                    double c = a[i][j]/a[j][j];
                    for (int k = j; k<=n; k++)
                        a[i][k]-=a[j][k]*c;
                }//if
            }//for_i
    }//diagonalizza

    @Override
    protected double[] calcolaSoluzione(){
        int n=getN(); double[] x = new double[n];
        for (int i=n-1; i>=0; --i) {
            x[i] = a[i][n]/a[i][i];
        }//for_i
        return x;
    }

    public static void main(String[] args) {
        double[][] a = {{2,1,-1},
                       {3,-2,2},
                       {1,-3,-3}};
        double[]   b = {5,-3,-2};
        Gauss sistema = new GaussDiagonale(a, b);
        poo.util.IO.printlnArray(sistema.risolvi());
        System.out.println(sistema);
    }
}