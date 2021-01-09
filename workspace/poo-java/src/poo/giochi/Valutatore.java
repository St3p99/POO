package poo.giochi;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Valutatore{
   static int valutaEspressione(StringTokenizer st){
        int ris = valutaOperando(st);
        while(st.hasMoreTokens()){
            char operatore = st.nextToken().charAt(0);
            if(operatore==')') return ris;
            int operando = valutaOperando(st);
            switch(operatore){
                case '+': ris = ris+operando; break;
                case '-': ris = ris-operando; break;
                case '*': ris = ris*operando; break;
                case '/': ris = ris/operando; break;
                default: throw new RuntimeException("Mannaia l'ostia, ma che mi passi!?");
            }//switch
        }//while
        return ris;
    }//valutaEspressione

    static int valutaOperando(StringTokenizer st){
        String tk = st.nextToken();
        if(tk.equals("("))
            return valutaEspressione(st);
        return Integer.parseInt(tk);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String espr = sc.nextLine();
        sc.close();
        StringTokenizer st = new StringTokenizer(espr, "+-*/()", true);
        int ris = valutaEspressione(st);
        System.out.println(espr + "="+ris);
    }
}