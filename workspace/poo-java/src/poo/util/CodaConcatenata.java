package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CodaConcatenata<T> extends CodaAstratta<T>{
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }

    private Nodo<T> first = null, last = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        first=last=null;
        size=0;
    }

    public boolean isEmpty() {
        return first==null;
    }

    public boolean isFull() {
        return false;
    }

    public void put(T x) {
        Nodo<T> n = new Nodo<>();
        n.info = x; n.next=null;
        if( first==null ) first = n;
        else last.next = n;  // il vecchio last punta ad n
        last = n;  // aggiorni il last
        size++;
    }

    public T get() {
        if(first==null) throw new NoSuchElementException();
        T x = first.info;
        first = first.next;
        if(first==null) last = null;
        size--;
        return x;
    }

    public T peek() {
        if(first==null) throw new NoSuchElementException();
        return first.info;
    }

    public Iterator<T> iterator() {
        return new CodaConcatenataIterator();
    }

    private class CodaConcatenataIterator implements Iterator<T>{
        private Nodo<T> pre = null, cor = null;

        public boolean hasNext(){
            if(cor==null) return first!=null;
            return cor.next != null;
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = first;
            else{ pre = cor; cor = cor.next; }
            return cor.info;
        }

        public void remove(){
            if( pre==cor ) throw new IllegalStateException();
            if( cor==first ){  // se ha un solo elemento
                first = first.next;
                if(first==null) last = null;
            }
            else if( cor == last){  // alla fine
                pre.next = null; last = pre;
            }
            else{ pre.next = cor.next;}
            cor = pre; size--;
        }//remove
    }//CodaConcatenataIterator

    public static void main(String[] args) {
        /* SIMULAZIONE DI UNA FILA DI PERSONE */
        Scanner sc = new Scanner( System.in );
        Coda<String> fila = new CodaConcatenata<>();
        String COMANDO = "([Aa]\\s+\\w+|[Pp]|[Qq])";;
        //A=arrivo + nome | P = partenza | Q = quit
        for_ever: for(;;){
            System.out.println("> ");
            String comando = sc.nextLine();
            if(! comando.matches(COMANDO)){
                System.out.println("Comando sconosciuto");
            }
            else{
                char c = Character.toUpperCase(comando.charAt(0));
                switch (c){
                    case 'A': {
                        int i = comando.lastIndexOf(" ");
                        String s = comando.substring(i+1);
                        fila.put(s);
                        System.out.println(s+" entra");
                        break;
                    }
                    case 'P':{
                        String s = fila.get();
                        System.out.println(s+" va via");
                        System.out.println("Residuo coda = "+fila);
                        break;
                    }
                    case 'Q':{
                        System.out.println("SI CHIUDE !!");
                        System.out.println();
                        break for_ever;
                    }
                }
            }
        }

    }
}//CodaConcatenata
