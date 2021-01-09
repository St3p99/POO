package poo.esempi;

import java.util.Scanner;

public class ValutaRPN {
    public static void main(String[] args) {
        //TODO: Valuta RPN
        Scanner sc = new Scanner(System.in);
        String EXP = "(\\d+\\s+\\d+(\\*|\\+|\\-))+";
        for(;;){
            System.out.print("Inserisci RPN, o 'STOP' per fermarti\n>");
            String inp = sc.nextLine();
            if(inp.toUpperCase().equals("STOP")) break;
            if(inp.matches(EXP)) System.out.println("RPN");
            else System.out.println("No RPN");
        }
        sc.close();
    }
}
