package poo.appelli.appello15_11_19.ese_2;

import poo.util.IO;

public class Array {
    public static double[][] minore(double[][] a, int i, int j){
        if(i<0 || i>=a.length || j<0 || j >= a[0].length)
            throw new IllegalArgumentException();
        double[][] ret = new double[a.length-1][a[0].length-1];
        int rigaRet = 0;
        for(int r=0; r<a.length; r++){
            if(r==i) continue;
            int colonnaRet = 0;
            for(int c=0; c<a[0].length; c++){
                if(c==j) continue;
                ret[rigaRet][colonnaRet] = a[r][c];
                colonnaRet++;
            }//for_c
            rigaRet++;
        }//for_r
        return ret;
    }

    public static double determinanteL(double[][] a){
        if(a.length != a[0].length) throw new IllegalArgumentException();
        return determinanteL(a, 0);
    }

    private static double determinanteL(double[][] a, int r){
        if(a.length == 2)
            return a[0][0]*a[1][1] - a[0][1]*a[1][0];
        double det = 0D;
        for (int j = 0; j<a[0].length; j++){
            int sgn = ((r+j)%2 == 0)? 1 : -1;
            det += sgn*a[r][j]*determinanteL(minore(a,r,j), r);
        }
        return det;
    }

    public static double compAlgebrico(double[][] a, int r, int c){
        int sgn = ((r+c)%2 == 0) ? 1: -1;
        return sgn*determinanteL(minore(a,r,c));
    }

    public static double[][] matriceInversa(double[][] a){
        //inv(a) = 1/det(a) * matrice trasporta dei complAlgebrici
        if(a.length != a[0].length)
            throw new IllegalArgumentException();
        double det = determinanteL(a);
        if(det==0) throw new RuntimeException();
        double[][] ret = new double[a.length][a.length];
        for(int i=0; i<a.length; i++)
            for (int j = 0; j < a.length; j++)
                ret[j][i] = ((double) 1/det)*compAlgebrico(a,i,j);
        return ret;
    }

    public static double[] prodotto(double[][] m1, double[] v){
        //prodotto matrice-vettore
        if(m1[0].length != v.length)
            throw new IllegalArgumentException();
        double[] ret = new double[m1.length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j <v.length ; j++)
                ret[i]+=m1[i][j]*v[j];
        return ret;
    }

    public static void main(String[] args){
        double[][] test = new double[3][3];
        /*Random n = new Random();
        for (int i = 0; i < test.length; i++)
            for (int j = 0; j < test.length; j++){
                test[i][j] = n.nextInt();
            }
        */
        test[0][0]=5; test[0][1]=-7;test[0][2]=2;
        test[1][0]=11; test[1][1]=3; test[1][2]=1;
        test[2][0]=3; test[2][1]=1; test[2][2]=9;
        //IO.printlnMatrix(test);
        //System.out.println(determinanteL(test));
        //IO.printlnMatrix(matriceInversa(test));

        double[][] a = {{2,1,-1},
                        {3,-2,+2},
                        {1,-3,-3}};
        double[]   b = {5,-3,-2};

        double[][] inv = matriceInversa(a);
        IO.printlnMatrix(inv);
        double[] x = prodotto(inv, b);
        System.out.println();
        IO.printlnArray(x);
    }
}
