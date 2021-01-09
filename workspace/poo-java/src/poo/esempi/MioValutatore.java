package poo.esempi;

import java.util.Scanner;
import java.util.StringTokenizer;

public class MioValutatore{
    public static double valutaEspressione(StringTokenizer st){
        double ret = valutaOperando(st);//Ritorna il primo operando
        while(st.hasMoreTokens()){
            char operatore = st.nextToken().charAt(0);//dopo aver trovato il primo operando troveremo un operatore
            if(operatore==')')//espressione finita
                return ret;
            double operando = valutaOperando(st);
            switch(operatore){
                case '+': ret+=operando; break;
                case '-': ret-=operando; break;
                case '*': ret*=operando; break;
                case '/': ret/=operando; break;
                case '^': ret = Math.pow(ret, operando); break;
                default: throw new RuntimeException("Amico mioo!!!\nMa che mi passi!?");//case '(': dopo un'operatore non é consentito
            }//switch
        }//while
        return ret;
    }//valutaEspressione

    public static double valutaOperando(StringTokenizer st){
        String nT = st.nextToken();
        if(nT.charAt(0)=='(') return valutaEspressione(st);
        return Double.parseDouble(nT);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();
        StringTokenizer st = new StringTokenizer(line, "()_+*^/", true);
        System.out.println(line + "=" + valutaEspressione(st));
    }
}