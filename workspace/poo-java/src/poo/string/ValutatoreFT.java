package poo.string;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

class Espressione{
    private String expr;
    private StringTokenizer st;
    private int cont=0;

    public Espressione(String expr){
        // un UnsignedInt oppure un operatore
        String EXPR="(\\d+|[\\+\\-\\*/\\(\\)])+"; //espressione regolare, solo condizione necessaria. il + finale indica (uno o almeno)
        if(! expr.matches(EXPR)) throw new RuntimeException();
        this.expr = expr; st = new StringTokenizer(expr, "+-*/()", true);
    }

    public String toString(){
        return expr;
    }

    /* Ricaviamo il primo operando, se troviamo una parentesi tonda (ne teniamo conto) e valutiamo
    *  prima l'espressione 'innestata', una volta valutata avremo il primo operando, prendiamo
    *  l'operatore e il prossimo operando con la stessa tecnica */

    public int valutaEspressione(){
        int ris = valutaOperando();
        while(st.hasMoreTokens()){
            char operatore = st.nextToken().charAt(0);
            if(operatore==')'){
                if(cont==0) throw new RuntimeException("Parentesi chiusa inattesa");
                cont--;
                return ris;
            }
            int operando = valutaOperando();
            switch(operatore){
                case '+': ris = ris+operando; break;
                case '-': ris = ris-operando; break;
                case '*': ris = ris*operando; break;
                case '/': ris = ris/operando; break;
                default: throw new RuntimeException("Mannaia l'ostia, ma che mi passi!?");
            }//switch
        }//while
        if(cont!=0) throw new RuntimeException(expr+" é malformata, parentesi errate");
            return ris;
    }//valutaEspressione

    private int valutaOperando(){
        String tk = st.nextToken();
        if(tk.equals("(")){ 
            cont++;
            return valutaEspressione();
        }
        return Integer.parseInt(tk);
    }//valutaOperando
}//Espressione

public class ValutatoreFT{//ValutatoreFaultTollerance
    public static void main(String... args) {
        System.out.println("***Il programma valuta espressioni algebrica, senza tenere conto delle prioritá degli operatori algebrici.***");
        Scanner sc = new Scanner(System.in);
        for(;;){//for infinito
            System.out.print("> ");
            String espr = sc.nextLine();
            if(espr.equals(".")) break;
            try{
                int ris = new Espressione(espr).valutaEspressione();
                System.out.println(espr+"="+ris);
            }//try
            catch(RuntimeException e){//che cosa faccio se try solleva una eccezione
                System.out.println(espr+ " é malformata\n");
                e.printStackTrace(); //stampa la traccia di tutte le eccezioni sollevate. INTERROMPE IL PROGRAMMA
            }//catch
        }//for
        sc.close();
    }//main
}//ValutatoreFT