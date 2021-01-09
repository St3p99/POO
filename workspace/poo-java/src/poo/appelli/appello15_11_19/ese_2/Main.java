package poo.appelli.appello15_11_19.ese_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserire il numero intero N (Grandezza sistema da risolvere) \n>");
        final int N = sc.nextInt();
        double[][] a = new double[N][N];
        System.out.println("Inserire i coefficienti");
        for(int r = 0; r<N; r++)
            for(int c = 0; c<N; c++){
                System.out.print("a["+(r+1)+"]["+(c+1)+"] = ");
                a[r][c] = sc.nextDouble();
            }
        System.out.println("Inserire i termini noti");
        double[] b = new double[N];
        for(int r = 0; r<N; r++){
            System.out.print("b["+(r+1)+"] = ");
            b[r] = sc.nextDouble();
        }
        sc.close();
        double[][] inv = null;
        try{
            inv = Array.matriceInversa(a);
        }
        catch (RuntimeException e){
            System.out.println("Sistema singolare");
            System.exit(-1);
        }
        double[] x = Array.prodotto(inv, b);
        System.out.println("x = "+java.util.Arrays.toString(x));
    }
}
