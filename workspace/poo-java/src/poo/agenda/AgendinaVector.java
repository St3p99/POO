package poo.agenda;

import java.util.Iterator;

import poo.util.Array;
import poo.util.ArrayVector;

public class AgendinaVector extends AgendinaAstratta {
    private ArrayVector<Nominativo> tabella;


    public AgendinaVector(){
        this(100);
    }

    public AgendinaVector(int n){
        if(n<=0) throw new IllegalArgumentException();
        tabella = new ArrayVector<>(n);
    }

    @Override
    public int size(){
        return tabella.size();
    }

    @Override
    public void aggiungi(Nominativo n) {
        int i = Array.ricercaBinaria(tabella, n);
        if(i>=0){ tabella.set(i, n); return;} 
        i=0;
        Iterator<Nominativo> it =iterator();
        while(it.hasNext()){
            Nominativo m = it.next();
            if(m.compareTo(n)>0){
                break;
            }
            i++;
        }
        tabella.add(i, n);   
    }//aggiungi

    @Override
    public Nominativo cerca(Nominativo n){
        int i = Array.ricercaBinaria(tabella, n);
        if(i<0) return null;
        i=0;
        return tabella.get(i);
    }

    @Override
    public void rimuovi(Nominativo n){
        int i = Array.ricercaBinaria(tabella, n);
        if(i<0) return;
        tabella.remove(i);

    }

    @Override
    public Iterator<Nominativo> iterator() {
        return tabella.iterator();
    }

    @Override
    public void svuota(){
        tabella.clear();
    }   


    

    
}