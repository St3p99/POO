package poo.esempi;

import java.util.Scanner;
import java.util.StringTokenizer;

public class TestTokenizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();
        sc.close();
        //ip: le parole sono separate da spazi e segni di punteggiatura
        StringTokenizer st = new StringTokenizer(linea, " ,.;:");//col secondo argomento indichiamo i separatori
        while(st.hasMoreTokens()){
            String parola = st.nextToken(); //dammi il prossimo
            System.out.println(parola);
        }

    }

}