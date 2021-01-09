package poo.appelli.appello31_01_18;

import java.util.LinkedList;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class MatriceSparsaLL extends MatriceSparsaAstratta {
    private Map<Integer, LinkedList<Elemento>> righe = new HashMap<>();
    private Map<Integer, LinkedList<Elemento>> colonne = new HashMap<>();
    private int n;

    public MatriceSparsaLL(int n){
        if(n<0) throw new IllegalArgumentException();
        this.n = n;
    }

    public MatriceSparsaLL(MatriceSparsaLL m){
        this.n = m.getN();
        this.righe = m.getRighe();
        this.colonne = m.getColonne();
    }

    public int getN() {
        return n;
    }

    public Map<Integer, LinkedList<Elemento>> getRighe(){
        return new HashMap<>(righe);
    }

    public Map<Integer, LinkedList<Elemento>> getColonne() {
        return new HashMap<>(colonne);
    }

    public void clear() {
        righe.clear();colonne.clear();
    }

    public void set(int i, int j, int v) {
        if(i<0 || j<0 || i>=n || j>=n) throw new IndexOutOfBoundsException();
        Elemento e = new Elemento(i,j,v);
        if(! righe.containsKey(i)){
            LinkedList<Elemento> l = new LinkedList<>();
            l.add(e);
            righe.put(i, l);
        }
        else{
            LinkedList<Elemento> riga = righe.get(i);
            ListIterator<Elemento> litR = riga.listIterator();
            boolean flag = false;
            while(litR.hasNext() && !flag){//inserimento in ordine riga
                Elemento x = litR.next();
                if(x.compareTo(e) > 0){
                    litR.previous();
                    litR.add(e);
                    flag = true;
                }
                if(x.equals(e)){
                    x.setValore(v); flag = true;
                }
            }
            if(!flag) litR.add(e);
            righe.put(i, riga);
        }
        if(! colonne.containsKey(j)){
            LinkedList<Elemento> l = new LinkedList<>();
            l.add(e);
            colonne.put(j, l);
        }
        else{
            LinkedList<Elemento> colonna = colonne.get(j);
            ListIterator<Elemento> litC = colonna.listIterator();
            boolean flag = false;
            while(litC.hasNext() && !flag ){//inserimento in ordine colonna
                Elemento x = litC.next();
                if(x.compareTo(e) > 0){
                    litC.previous();
                    litC.add(e);
                    flag = true;
                }
                if(x.equals(e)){
                    x.setValore(v); flag = true;
                }
            }
            if(!flag) litC.add(e);
            colonne.put(j, colonna);
        }
    }

    public void set(Elemento e) {
        set(e.getRiga(), e.getColonna(), e.getValore());
    }

    public int sizeRiga(int i) {
        if(righe.containsKey(i)) return righe.get(i).size();
        return 0;
    }

    public int sizeColonna(int j) {
        if(colonne.containsKey(j)) return colonne.get(j).size();
        return 0;
    }

    public MatriceSparsa crea() {
        return new MatriceSparsaLL(n);
    }

    public Iterable<Elemento> riga(int i) {
        if(righe.containsKey(i)) return new LinkedList<>(righe.get(i));
        return null;
    }

    public Iterable<Elemento> colonna(int j) {
        if(colonne.containsKey(j)) return new LinkedList<>(colonne.get(j));
        return null;
    }


    public static void main(String[] args) {
        MatriceSparsaLL m = new MatriceSparsaLL(10);
        m.set(0,0,1);
        m.set(0,3,2);
        m.set(0,0,3);//sovrascive
        m.set(0,1,3);
        m.set(1,0,3);
        m.set(3,0,2);
        System.out.println(m);
        MatriceSparsaLL m2 = new MatriceSparsaLL(10);
        m2.set(0,0,5);
        m2.set(0,3,3);
        m2.set(0,1,7);
        System.out.println(m2);
        MatriceSparsa somma = m.add(m2);
        System.out.println(somma);
        System.out.println(m.simmetrica());
    }
}
