package poo.esempi;

import java.util.Scanner;

public class App{
    public static void main(String... args) {
        int[] v=null;
        if(args.length > 0){
            v = new int[args.length];
            for (int i = 0; i < v.length; i++)
                v[i] = Integer.parseInt(args[i]); // converte la Stringa in un intero
        }//if
        else{//prendere i dati da tastiera
            Scanner sc = new Scanner(System.in);
            System.out.print("n=");
            int n = sc.nextInt();
            v = new int[n];
            System.out.println("Fornisci "+n+" interi");
            for (int i = 0; i < v.length; i++)
                v[i] = sc.nextInt();
            sc.close();    
        }//else
        poo.util.IO.printlnArray(v);
    }
}