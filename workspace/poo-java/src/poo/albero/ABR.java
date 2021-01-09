package poo.albero;

import poo.util.Coda;
import poo.util.CodaConcatenata;
import poo.util.CollezioneOrdinata;
import poo.util.StackConcatenato;
//import poo.util.LinkedList;
//import poo.util.List;

import java.util.*;

public class ABR <T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
    private static class Nodo<E>{
        E info;
        Nodo<E> fS, fD;
        public String toString(){
            return info.toString();
        }
    }

    private Nodo<T> radice = null, nodoSbilanciato = null;
    private int size;

    public int size() {
        return size(radice);
    }

    private int size(Nodo<T> radice){
        // il campo size non viene utilizzato a scopo didattico
        if(radice==null) return 0;
        return 1+size(radice.fS)+size(radice.fD);
    }

    public ABR<T> copia(){
        ABR<T> ret = new ABR<>();
        ret.radice = copia(this.radice);
        ret.size = this.size;
        return ret;
    }

    private Nodo<T> copia(Nodo<T> radice ){
        if(radice==null) return null;
        Nodo<T> ret = new Nodo<>();
        ret.info = radice.info;
        ret.fS = copia(radice.fS);
        ret.fD = copia(radice.fD);
        return ret;
    }

    private void scrivi(ABR<T> abr, Nodo<T> radice){
        if(radice==null) return;
        abr.add(radice.info);
        scrivi(abr, radice.fS);
        scrivi(abr, radice.fD);
    }

    public void build(T[] a){
        radice = build(radice, a, 0, a.length-1);
    }

    private Nodo<T> build(Nodo<T> radice, T[] a, int in, int fine){
        if(fine < in) return null;
        int med = (fine+in)/2;
        radice.info = a[med];
        radice.fS = build(radice.fS, a, in, med-1);
        radice.fD = build(radice.fD, a, med+1, fine);
        return radice;
    }

    public boolean bilanciato(){
        return bilanciato(radice);
    }

    private boolean bilanciato(Nodo<T> radice){
        if(radice==null) return true;
        if( Math.abs(size(radice.fS) - size(radice.fD)) > 1 ) {
            nodoSbilanciato = radice;
            return false;
        }
        return bilanciato(radice.fS) && bilanciato(radice.fD);
    }

    private void bilancia(){
        ArrayList<T> l = new ArrayList<>();
        visitaSimmetrica(l, nodoSbilanciato);
        int med = (l.size()-1)/2;
        nodoSbilanciato.info = l.get(med);
        nodoSbilanciato.fS = addBinaria(l, 0, med-1);
        nodoSbilanciato.fD = addBinaria(l, med+1, l.size()-1);
    }

    private Nodo<T> addBinaria(ArrayList<T> l, int in, int fine){
        if(fine<in) return null;
        int med = (fine+in)/2;
        Nodo<T> n = new Nodo<>();
        n.info = l.get(med);
        n.fS = addBinaria(l, in, med-1);
        n.fD = addBinaria(l, med+1, fine);
        return n;
    }

    public void clear() {
        radice = null;
    }

    public boolean contains(T e) {
        return contains(radice, e);
    }

    private boolean contains(Nodo<T> radice, T e){
        if(radice==null) return false;
        if(radice.info.equals(e)) return true;
        if(radice.info.compareTo(e)>0) return contains(radice.fS, e);
        return contains(radice.fD, e);
    }//contains

    public boolean isEmpty() {
        return radice==null;
    }

    public boolean isFull() {
        return false;
    }

    public T get(T e) {
        return get(radice, e);
    }

    private T get(Nodo<T> radice, T e){
        if(radice==null) return null;
        if(radice.info.equals(e)) return radice.info;
        if(radice.info.compareTo(e)>0) return get(radice.fS, e);
        return get(radice.fD, e);
    }

    public int altezza(){
        if(radice==null) return 0;
        return altezza(radice);
    }

    private int altezza(Nodo<T> radice){
        if(radice.fS==null && radice.fD==null) return 0;
        int hS = 0, hD = 0;
        if(radice.fS!=null) hS = 1+altezza(radice.fS);
        if(radice.fD!=null) hD = 1+altezza(radice.fD);
        return hS >= hD ? hS: hD; // return max(h1,h2)
    }

    public void visioneLivelliArray(List<T> l){
        //1. inizializzo l'array con la radice
        //2. per ogni elemento dell'array inserisco i suoi figli

        ArrayList<Nodo<T>> buffer = new ArrayList<>(size);
        buffer.add(0, radice);
        int i = 0;
        int posLibera = 1;
        while(i<size){
            if(buffer.get(i).fS != null){
                buffer.add(posLibera, buffer.get(i).fS);
                posLibera++;
            }
            if(buffer.get(i).fD != null){
                buffer.add(posLibera, buffer.get(i).fD);
                posLibera++;
            }
            i++;
        }
        for(Nodo<T> n: buffer) l.add(n.info);
    }

    public void visioneLivelli(List<T> l){
        // 1. inizializzo la coda con il nodo radice
        // 2. finché la coda non si svuota per ogni nodo all'interno aggiungo i suoi figli,
        //      rimuovo il nodo e aggiungo l'info del nodo alla lista l
        Coda<Nodo<T>> coda = new CodaConcatenata<>();
        coda.put(radice);
        while(! coda.isEmpty()){
            if(coda.peek().fS != null){
                coda.put(coda.peek().fS);
            }
            if(coda.peek().fD != null){
                coda.put(coda.peek().fD);
            }
            l.add(coda.get().info); // rimuovi nodo dalla coda e aggiungi info
        }
    }

    /*public boolean equals(Object o){
        if(o==this) return true;
        if(! (o instanceof ABR)) return false;
        ABR a = (ABR) o;
        if(a.size != this.size) return false;
        List<T> lA = new LinkedList<>(); List<T> l = new LinkedList<>();
        a.visitaSimmetrica(lA); this.visitaSimmetrica(l);
        return lA.equals(l);
    }*/

    public boolean equals(Object o){
        if(o==this) return true;
        if(! (o instanceof ABR)) return false;
        ABR a = (ABR) o;
        if(a.size != this.size) return false;
        return equals(radice, a.radice);
    }

    private boolean equals(Nodo<T> r1, Nodo<T> r2){
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null) return false;
        if(! r1.info.equals(r2.info)) return false;
        return equals(r1.fS, r2.fS) && equals(r1.fD, r2.fD);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append("[");
        toStringRec(sb, radice);
        if(size>0) sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

    private void toStringRec(StringBuilder sb, Nodo<T> radice){
        if(radice!=null){
            toStringRec(sb, radice.fS);
            sb.append(radice.info+", ");
            toStringRec(sb, radice.fD);
        }
    }

    public int hashCode(){
        return hashCode(radice);
    }

    private int hashCode(Nodo<T> radice){
        if(radice==null) return 0;
        return 83*(43*hashCode(radice.fD) + 43*hashCode(radice.fS)) + radice.info.hashCode();
    }

    public void visitaSimmetrica(List<T> l){
        visitaSimmetrica(l, radice);
    }

    private void visitaSimmetrica(List<T> l, Nodo<T> radice){
        //visita prima a sinistra, poi la radice, poi a destra - COME? CON LA STESSA TECNICA
        if(radice!=null){
            visitaSimmetrica(l, radice.fS);
            l.add(radice.info);
            visitaSimmetrica(l, radice.fD);
        }
    }

    public void remove(T e) {
        radice = remove(radice, e);
        if(!bilanciato()) bilancia();
    }

    private Nodo<T> remove(Nodo<T> radice, T e){
        if(radice==null) return null; // albero vuoto
        /* trovo elemento da rimuovere */
        if(radice.info.compareTo(e)>0){ // sará nel sottalbero sinistro
            radice.fS = remove(radice.fS, e);
            return radice;
        }
        else if(radice.info.compareTo(e)<0){ //sará nel sottalbero destro
            radice.fD = remove(radice.fD, e);
            return radice;
        }
        size--;
        // else radice equals e
        if(radice.fS == null && radice.fD==null){  // foglia
            return null; // il padre del nodo di radice e punterá a null
        }
        if(radice.fS == null) {  // solo figlio dx
            return radice.fD;
        }
        if(radice.fD == null) {  // solo figlio sx
            return radice.fS;
        }
        //else due figli
        /* ricerca minore del sottalbero destro */
        if(radice.fD.fS == null) { //3 CASO  radice.fD é il minore
            radice.info = radice.fD.info; //promozione
            radice.fD = radice.fD.fD; //bypass
            return radice;
        }
        //4 CASO - trova vittima come minimo del sottalbero destro
        Nodo<T> padre = radice.fD, figlio = radice.fD.fS;
        while(figlio.fS!=null){
            padre = figlio; figlio = figlio.fS;
        }//figlio sará il minimo
        radice.info = figlio.info; // promozione
        padre.fS = figlio.fD; // bypass
        return radice;
    }

    public void add(T e) { //ogni elemento verrá aggiunto ad un albero vuoto
        radice = add(radice, e);
        if(!bilanciato()){
            System.out.println(e+": "+nodoSbilanciato.info);
            bilancia();
        }
        size++;
    }

    private Nodo<T> add(Nodo<T> radice, T e){
        if(radice==null){  /* 1 caso - albero vuoto */
            Nodo<T> n = new Nodo<>();
            n.info = e; n.fS = null; n.fD = null;
            radice = n;
        }
        else if(radice.info.compareTo(e)>0){//add a sx
            radice.fS = add(radice.fS, e);
        }
        // else radice <= e add a dx
        else radice.fD = add(radice.fD, e);
        return radice;
    }

    public void addIte(T e){
        boolean flag = false; // flag = true se mossa a sx
        //      pre           cor
        Nodo<T> padre = null, figlio = radice;
        while(figlio!=null){
            if(figlio.info.compareTo(e)>0){
                padre = figlio; figlio = figlio.fS;
                flag = true;
            }
            else{
                padre = figlio; figlio = figlio.fD;
                flag = false;
            }
        }//while
        Nodo<T> n = new Nodo<>(); n.info = e; n.fD = null; n.fS = null;
        if(padre==null){
            radice = n;
        }
        else{
            if(flag) padre.fS = n;
            else padre.fD = n;
        }
        if(!bilanciato()) bilancia();
        size++;
    }//addIte

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T>{
        private poo.util.Stack<Nodo<T>> pila;
        Nodo<T> padre=null, consumato = null;
        // il nodo corrente é in cima allo stack

        public StackIterator(){
            /* inizializza stack col percorso piú a sinistra a partire dalla radice */
            pila = new StackConcatenato<>();
            if(radice==null) return;
            pila.push(radice);
            Nodo<T> figlio = radice.fS;
            while(figlio!=null){
                pila.push(figlio);
                figlio = figlio.fS;
            }
        }

        public boolean hasNext() {
            return !pila.isEmpty();
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            Nodo<T> cor = pila.pop();
            /* sostituire nodo corrente (nello stack) con il percorso piú a sinistra
               del sottalbero destro del nodo cor */
            if(cor.fD!=null){
                Nodo<T> figlio = cor.fD.fS;
                pila.push(cor.fD);
                while(figlio!=null){
                    pila.push(figlio);
                    figlio = figlio.fS;
                }
            }
            consumato = cor;
            return cor.info;
        }

        public void remove(){
            if(consumato==null) throw  new IllegalStateException();
            T next = null;
            if(hasNext()) next = pila.top().info;

            ABR.this.remove(consumato.info);
            consumato = null;
            //se c'é un prossimo bisogna aggiornare la pila a
            //  seguito della remove che ha modifito l'albero
            if(next==null) return;
            StackIterator it = new StackIterator();
            while(it.hasNext()){
                if(it.pila.top().info.equals(next)) break;
                it.next();
            }
            pila = it.pila;
        }

        /*public void remove(){
            if(consumato==null) throw new IllegalStateException();
            size--;
            if(consumato.fS == null && consumato.fD==null){  // foglia
                consumato=null;
            }
            if(consumato.fS == null) {  // solo figlio dx
                //
            }
            if(consumato.fD == null) {  // solo figlio sx
                //
            }
            //else due figli
            // ricerca minore del sottalbero destro
            if(radice.fD.fS == null) { //3 CASO  radice.fD é il minore
                // promozione e bypass
            }
            //4 CASO - trova vittima come minimo del sottalbero destro
        }*/


    }

    private class ABRIterator implements Iterator<T>{
        List<T> l = new LinkedList<>();
        Iterator<T> it;
        T cor;

        public ABRIterator(){
            visitaSimmetrica(l);
            it = l.iterator();
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public T next() {
            if(! hasNext()) throw new NoSuchElementException();
            cor = it.next();
            return cor;
        }

        public void remove() {
            it.remove(); // l'eccezione, in caso, verrá sollevata da it
            ABR.this.remove(cor);
        }
    }//ABRIterator

    public static void main(String[] args) {
        ABR<Integer> abr = new ABR<>();
        abr.add(12);
        abr.add(2);
        abr.add(30);
        abr.add(1);
        abr.add(5);
        abr.add(20);
        abr.add(40);
        abr.add(0);
        abr.add(50);
        abr.add(52);

        for(Integer n: abr){
            System.out.print(n+ " " );
        }
        System.out.println();

        Iterator<Integer> it = abr.iterator();
        while(it.hasNext()){
            int cor = it.next();
            if( cor != 1 )  it.remove();
        }
        System.out.println(abr);
        TreeMap<String,Integer> test = new TreeMap<>();
        test.put("A",3);test.put("A",2);
        System.out.println(test);

    }

}
