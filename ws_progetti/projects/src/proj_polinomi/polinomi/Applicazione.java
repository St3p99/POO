package proj_polinomi.polinomi;

import java.util.Scanner;

public class Applicazione {
    // legge da tastiera due polinomi

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        Polinomio p1, p2;
        for(;;){
            System.out.print("Scrivi il primo polinomio\nP1(x) = ");
            String linea1 = sc.nextLine();
            if(! linea1.matches(ValutaPolinomio.POLINOMIO)){
                System.out.println("Amico mio, non matcha"); continue;
            }
            p1 = ValutaPolinomio.interpretaPolinomio(linea1);
            break;
        }//estrai_p1
        for(;;){
            System.out.print("Scrivi il secondo polinomio\nP2(x) = ");
            String linea2 = sc.nextLine();
            if(! linea2.matches(ValutaPolinomio.POLINOMIO)){
                System.out.println("Amico mio, non matcha"); continue;
            }
            p2 = ValutaPolinomio.interpretaPolinomio(linea2);
            break;
        }//estrai_p2
        sc.close();
        System.out.println("P1(x) = " + p1);
        System.out.println("P2(x) = " + p2);
        System.out.println("P1*P2(x) = " + p1.mul(p2));
        System.out.println("P1+P2(x) = " + p1.add(p2));
        System.out.println("P1-P2(x) = " + p1.add(p2.mul(new Monomio(-1,0))));
        System.out.println("P1'(x) = " + p1.derivata());
        System.out.println("P2'(x) = " + p2.derivata());
    }
}


