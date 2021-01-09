package poo.appelli.appello21_06_19;

import poo.util.StackConcatenato;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Valutatore {
    private static HashMap<Character, Integer> priorita = new HashMap<>();
    static{//blocco inizializzazione
        priorita.put('+',1);priorita.put('-',1);
        priorita.put('*',2);priorita.put('%',2);
        priorita.put('^',3);
    }
    private static Comparator<Character> cmp = (o1,o2)->
    { return priorita.get(o1) - priorita.get(o2);};

    private static int applica(int o1, char opt, int o2){
        switch (opt){
            case '+': return o1+o2;
            case '-': return o1-o2;
            case '*': return o1*o2;
            case '&': return o1%o2;
            default: return (int) Math.pow(o1,o2);
        }
    }

    private static int valutaOperando(StringTokenizer st){
        String tk = st.nextToken();
        if(tk.charAt(0)=='(') return valutaEspressione(st);
        return Integer.parseInt(tk);
    }

    public static int valutaEspressione(StringTokenizer st){
        StackConcatenato<Integer> operandi = new StackConcatenato<>();
        StackConcatenato<Character> operatori = new StackConcatenato<>();
        operandi.push(valutaOperando(st));
        while(st.hasMoreTokens()){
            char opc = st.nextToken().charAt(0);
            if(opc==')'){
                while(! operatori.isEmpty()){
                    int o1 = operandi.pop(), o2 = operandi.pop();
                    char op = operatori.pop();
                    operandi.push(applica(o1,op,o2));
                }
                int ris = operandi.pop();
                if(! operandi.isEmpty()) throw new RuntimeException();
                return ris;
            }
            if(operatori.isEmpty() || cmp.compare(opc, operatori.top()) > 0)
                operatori.push(opc);
            else{
                while(!operatori.isEmpty() && cmp.compare(opc, operatori.top())>=0){
                    int o1 = operandi.pop(), o2 = operandi.pop();
                    char op = operatori.pop();
                    operandi.push(applica(o1,op,o2));
                }
                operatori.push(opc);
            }
            operandi.push(valutaOperando(st));//dopo un operatore c'é il secondo operatore
        }//hasMoreTokens
        while(!operatori.isEmpty()){
            int o1 = operandi.pop(), o2 = operandi.pop();
            char op = operatori.pop();
            operandi.push(applica(o1,op,o2));
        }
        int ris = operandi.pop();
        if(!operandi.isEmpty()) throw new RuntimeException();
        return ris;
    }//valutaEspressione

    public static void main(String[] args) {
        String EXPR = "(\\d+|[\\+\\-\\*\\%\\^\\(\\)])+";
        String linea = null;
        StringTokenizer st = null;
        System.out.println("Inserire l'espressione da valutare"+
                " oppure '.' per terminare");
        Scanner sc = new Scanner(System.in);
        for(;;){
            linea = sc.nextLine();
            if(linea.equals(".")) break;
            if(! linea.matches(EXPR)) System.out.println(linea+" espressione malformata");
            st = new StringTokenizer(linea, "+-*%^()", true);
            int ris;
            try{
                ris = Valutatore.valutaEspressione(st);
            }
            catch (RuntimeException e){ System.out.println(linea+" espressione malformata"); continue;}
            System.out.println(linea+"= "+ris);
        }
        sc.close();
    }
}
