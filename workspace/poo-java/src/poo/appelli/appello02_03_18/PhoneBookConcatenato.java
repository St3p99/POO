package poo.appelli.appello02_03_18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PhoneBookConcatenato extends AbstractPhoneBook {
    private static class Nodo<Persona>{
        Persona info;
        Nodo<Persona> next;
    }

    private int size;
    private Comparator<Persona> cmp;
    private Nodo<Persona> testa = null;

    public int size(){ return size;}

    public void add(Persona p){
        Nodo<Persona> n = new Nodo<>(); n.info = p;
        if(testa==null) { n.next = null; testa = n; size++;}
        else{
            Nodo<Persona> pre = null, cor = testa;
            while(cor!=null){
                if(cmp==null && cor.info.compareTo(p)>=0) break;
                if(cmp!=null && cmp.compare(cor.info, p)>=0) break;
                pre = cor; cor = cor.next;
            }
            if(cor == testa && ! cor.info.equals(p)){
                size++; n.next = testa; testa = n;
            }
            else if( (cor!=null && ! cor.info.equals(p) || cor==null)){
                size++; pre.next = n; n.next = cor;
            }
        }
    }//add

    public Iterator<Persona> iterator(){
        return new PBIterator();
    }

    public void resort(Comparator<Persona> c ){
        cmp = c;
        boolean scambi = true;
        while(scambi){
            scambi = false;
            Nodo<Persona> cor = testa;
            while(cor.next!=null){
                if(c.compare(cor.info, cor.next.info) > 0){
                    scambi = true;
                    Persona tmp = cor.info;
                    cor.info = cor.next.info;
                    cor.next.info = tmp;
                }
                cor = cor.next;
            }
        }
    }

    private class PBIterator implements Iterator<Persona>{
        Nodo<Persona> pre = null, cor = null;

        public boolean hasNext(){
            if(cor==null) return testa != null;
            return cor.next != null;
        }

        public Persona next(){
            if(! hasNext()) throw new NoSuchElementException();
            if(cor==null) cor = testa;
            else{ pre = cor; cor = cor.next; }
            return cor.info;
        }

        public void remove(){
            if( pre == cor ) throw new IllegalStateException();
            if(cor==testa) testa = testa.next;
            else{ pre.next = cor.next; }
            cor = pre; size--;
        }
    }

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBookConcatenato();
        pb.add(new Persona("Stefano Perna", "003896"));
        pb.add(new Persona("Stefano Morrone", "533366"));
        pb.add(new Persona("Fabrizio gay", "363866"));
        pb.add(new Persona("Sesso anale", "233866"));
        System.out.println(pb);
        pb.resort((p1, p2) -> {
            return Integer.parseInt(p1.getTelefono())- Integer.parseInt(p2.getTelefono());
        });
        System.out.println(pb);
    }
}
