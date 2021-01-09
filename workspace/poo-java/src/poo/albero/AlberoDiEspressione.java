package poo.albero;

import java.util.LinkedList;

import poo.util.Stack;
import poo.util.StackConcatenato;

import java.util.List;
import java.util.StringTokenizer;

public class AlberoDiEspressione {
    private class Nodo {
        Nodo fS, fD;
    }

    private class NodoOperando extends Nodo {
        int opnd;
        public String toString() {
            return opnd + "";
        }
    }

    private class NodoOperatore extends Nodo {
        char op;
        public String toString() {
            return op + "";
        }
    }

    private Nodo radice = null;

    public void build(String expr) {
        String EXPR = "(\\d+|[\\+\\-\\*/\\(\\)])+";
        if(!expr.matches(EXPR)) throw new RuntimeException("Espressione malformata");
        StringTokenizer st = new StringTokenizer(expr, "+-*/()", true);
        radice = creaEspressione(st);
    }

    public void buildPost(String rpn){
        String EXPR = "((\\d+|[\\+\\-\\*\\/])\\s+)+";
        if(! rpn.matches(EXPR)) throw new RuntimeException();
        radice = creaEspressionePost(rpn);
    }

    public Nodo creaEspressionePost(String rpn){
        String OPERANDO = "\\d+";
        String OPERATORE = "[\\+\\-\\*\\/]";
        StackConcatenato<Nodo> pila = new StackConcatenato<>();
        StringTokenizer st = new StringTokenizer(rpn, " ");
        while(st.hasMoreTokens()){
            String tk = st.nextToken();
            if(tk.matches(OPERANDO)){
                NodoOperando n = new NodoOperando();
                n.opnd = Integer.parseInt(tk);
                n.fS = null; n.fD = null;
                pila.push(n);
            }
            else{
                NodoOperatore nop = new NodoOperatore();
                nop.op = tk.charAt(0);
                nop.fD = pila.pop(); nop.fS = pila.pop();
                pila.push(nop);
            }
        }
        Nodo radice = pila.pop();
        if(!pila.isEmpty()) throw new RuntimeException();
        return radice;
    }

    private Nodo creaEspressione(StringTokenizer st){
        Nodo radice = creaOperando(st); //temporanea
        while(st.hasMoreTokens()){
            char op = st.nextToken().charAt(0);
            if(op==')') return radice;
            Nodo opnd = creaOperando(st);
            NodoOperatore nop = new NodoOperatore();
            nop.op = op; nop.fS = radice; nop.fD = opnd;
            radice = nop;
        }
        return radice;
    }

    private Nodo creaOperando(StringTokenizer st){
        String tk = st.nextToken();
        if(tk.matches("\\d+")){
            NodoOperando nopnd = new NodoOperando();
            nopnd.opnd = Integer.parseInt(tk);
            nopnd.fS = null; nopnd.fD = null;
            return nopnd;
        }
        if(tk.charAt(0)=='(') return creaEspressione(st);
        throw new RuntimeException("Espressione malformata");
    }

    public void preOrder(List<String> l) {
        preOrder(radice, l);
    }

    private void preOrder(Nodo radice, List<String> l) {
        if (radice != null) {
            l.add(radice.toString());
            preOrder(radice.fS, l);
            preOrder(radice.fD, l);
        }
    }

    public void postOrder(List<String> l) {
        postOrder(radice, l);
    }

    private void postOrder(Nodo radice, List<String> l) {
        if (radice != null) {
            postOrder(radice.fS, l);
            postOrder(radice.fS, l);
            l.add(radice.toString());
        }
    }

    public void inOrder(List<String> l) {
        inOrder(radice, l);
    }

    private void inOrder(Nodo radice, List<String> l) {
        if (radice != null) {
            if (radice instanceof NodoOperatore) l.add("(");
            inOrder(radice.fS, l);
            l.add(radice.toString());
            inOrder(radice.fD, l);
            if (radice instanceof NodoOperatore) l.add(")");
        }
    }

    public int valore() {
        if (radice == null) throw new RuntimeException("Nessuna espressione!");
        return valore(radice);
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
                    else{ lv.add(radice.fD.toString()); break;}
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

    private int valore(Nodo radice) {
        // non arrivo mai a null
        if (radice instanceof NodoOperando)
            return ((NodoOperando) radice).opnd;
        else {
            int v1 = valore(radice.fS);
            int v2 = valore(radice.fD);
            char op = ((NodoOperatore) radice).op;
            switch (op) {
                case '+':
                    return v1 + v2;
                case '-':
                    return v1 - v2;
                case '*':
                    return v1 * v2;
                case '/':
                    return v1 / v2;
                default:
                    throw new RuntimeException("Espressione malformata");
            }
        }
    }

    public static void main(String[] args) {
        AlberoDiEspressione abr = new AlberoDiEspressione();
        abr.buildPost("2 5 3 * + ");
        LinkedList<String> l = new LinkedList<>();
        abr.inOrderIte(l);
        System.out.println(l);
    }
}
