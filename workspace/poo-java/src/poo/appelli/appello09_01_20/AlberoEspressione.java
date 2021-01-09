package poo.appelli.appello09_01_20;

import poo.util.Stack;
import poo.util.StackConcatenato;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//GIUSTO AL 50-60%
public class AlberoEspressione {
    private static class Nodo {
        Nodo fS, fD;
    }

    private static class NodoOperando extends Nodo {
        int info;
        public String toString() {
            return info + "";
        }
    }

    private static class NodoOperatore extends Nodo {
        char info;
        public String toString() {
            return info + "";
        }
    }

    private Nodo radice = null;

    public void build(String rpn){
        //ERRORE 1: Non considerare lo spazio all'ultimo elemento
        String EXPR = "((\\d+|[\\+\\-\\*\\/])\\s+)+";
        if(! rpn.matches(EXPR)) throw new RuntimeException();
        radice = creaEspressione(rpn);
    }

    public Nodo creaEspressione(String rpn){
        //GIUSTO
        String OPERANDO = "\\d+";
        String OPERATORE = "[\\+\\-\\*\\/]";
        StackConcatenato<Nodo> pila = new StackConcatenato<>();
        StringTokenizer st = new StringTokenizer(rpn, " ");
        while(st.hasMoreTokens()){
            String tk = st.nextToken();
            if(tk.matches(OPERANDO)){
                NodoOperando n = new NodoOperando();
                n.info = Integer.parseInt(tk);
                n.fS = null; n.fD = null;
                pila.push(n);
            }
            else{ //else if (tk.matches(OPERATORE))
                NodoOperatore nop = new NodoOperatore();
                nop.info = tk.charAt(0);
                nop.fD = pila.pop(); nop.fS = pila.pop();
                pila.push(nop);
            }
        }
        Nodo radice = pila.pop();
        if(!pila.isEmpty()) throw new RuntimeException();
        return radice;
    }

    public void inOrderIte(List<String> lv) {
        if(radice==null) return;
        Stack<Nodo> pila = new StackConcatenato<>();
        pila.push(radice);
        //AGGIUNTA 1
        boolean flag = false, flag2 = false;
        //FINE AGGIUNTA 1
        while(!pila.isEmpty()) {
            //AGGIUNTA 2
            if(pila.top() == radice){
                if(flag2){ break;}
                else if(flag){
                    lv.add(radice.toString());
                    flag2 = true;
                    if(radice.fD instanceof NodoOperatore) pila.push(radice.fD);
                    else{ lv.add(radice.fD.toString()); continue;}
                }
                else flag = true;
            }
            //FINE AGGIUNTA 2
            if (pila.top().fS instanceof NodoOperatore) {
                pila.push(pila.top().fS);
            }else {
                Nodo cor = pila.pop();
                lv.add(cor.fS.toString());
                lv.add(cor.toString());
                if (cor.fD instanceof NodoOperatore)
                    pila.push(cor.fD);
                else lv.add(cor.fD.toString());
            }
        }
    }

    public static void main(String[] args){
        AlberoEspressione ade = new AlberoEspressione();
        ade.build("2 5 6 + * 2 - ");
        LinkedList<String> l = new LinkedList<>();
        ade.inOrderIte(l);
        System.out.println(l);
    }
}
