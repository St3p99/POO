package poo.esempi;

import java.util.Scanner;

import poo.sistema.*;

public class SEL{
    public static void main(String[] args) {
        System.out.println("Risoluzione di un sistema di eq. lineari");
        Scanner sc = new Scanner(System.in);
        System.err.print("n=");
        int n = sc.nextInt();
        double[][] a = new double[n][n];
        double[] y = new double[n];
        System.out.println("Fornisci "+n+"*"+n+" double per righe");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("a["+i+","+j+"]=");
                a[i][j] = sc.nextDouble();
            }//for_j
            sc.nextLine();//andrá a capo in INPUT, pulirá il buffer e siamo pronti per riempirlo di nuovo
        }//for_i
        System.out.println("Ora fornisci "+n+" termini noti");
        for (int i = 0; i < n; i++) {
            System.out.print("y["+i+"]=");
                y[i] = sc.nextDouble();
        }
        sc.nextLine();
        Sistema s = new Gauss(a, y);//Gauss is a Sitesma
        double[] x = null;//l'array verrá creato dalla classe Gauss
        try{
            x=s.risolvi();
        }catch(RuntimeException e){
            System.out.println("Sistema singolare");
            System.exit(-1);// il programma si ferma
        }finally{sc.close();}
        for (int i = 0; i < n ; i++) {
            System.out.printf("x["+i+"]="+
                "%1.2f%n", x[i]);
        }
    }
}
