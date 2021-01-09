package poo.regex;

import java.util.Scanner;

public class Classificatore {
    /*Data una stringa classifica 3 categorie:
    * 1. numeri interi
    * 2. identificatori java
    * 3. numeri reali
    */

    public static void main(String[] args) {
        System.out.println("Inserisci un identificatore o un numero e termina con STOP");
        /*String INT = "[\\+\\-]?\\d+"; // il segno puó o non puó esserci, la cifra (\\d) puó essere una o piú | condizione necesseria
        String REAL1 = "[\\+\\-]?(\\d+|\\d*\\.\\d+)([eE][\\+\\-]?\\d{1,3})?[dDfF]?"; //condizione necessaria */
        String IDENT = "[a-zA-Z_]\\w"; // * = 0, 1 o piú volte |NOTA:  [a-z A_Z _ \d$]* = \\w*
        String SIGN = "[\\+\\-]";
        String UNSIGNED_INT = "\\d+";
        String INT = SIGN+"?"+UNSIGNED_INT;
        String MANTISSA = "("+UNSIGNED_INT+"|("+UNSIGNED_INT+")?"+"\\."+UNSIGNED_INT+")";
        String EXPO ="([eE]"+SIGN+"?"+UNSIGNED_INT+")";
        String SUF ="[dDfF]";
        String REAL = SIGN+"?"+MANTISSA+EXPO+"?"+SUF+"?";
        Scanner sc = new Scanner( System.in );
        for(;;){
            System.out.print("> "); // prompt
            String linea = sc.nextLine();
            if( linea.equalsIgnoreCase("STOP") ) break;
            if( linea.matches(INT))
                System.out.println(linea+" intero");
            else if ( linea.matches(REAL) )
                System.out.println(linea+" reale");
            else if( linea.matches(IDENT))
                System.out.println(linea+" identificatore Java");
            else
                System.out.println(linea+ " non riconosciuta!");
        }//for_ever
        System.out.println("Bye.");
        sc.close();
    }

}//Classificatore
