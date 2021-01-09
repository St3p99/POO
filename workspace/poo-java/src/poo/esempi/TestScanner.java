package poo.esempi;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("N=");
        @SuppressWarnings("unused")
        int N=sc.nextInt();
        @SuppressWarnings("unused")
        String linea =  sc.nextLine();
        
        sc.close();
        int x = 73;
        double z = -37.458;
        String s1 = "CASA";
        System.out.printf("%5d%n",x);//almeno 5 colonne
        System.out.printf("%7.2f%n",z);//usa 7 carattteri(almeno) in totale di cui 2 decimali
        System.out.printf("%10s%n",s1);
        String s = String.format("%5d %7.2f%n", x, z);
        System.out.println(s);

    }
}
