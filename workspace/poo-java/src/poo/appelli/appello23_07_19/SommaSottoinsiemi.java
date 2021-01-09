package poo.appelli.appello23_07_19;

import poo.util.Array;

import java.util.Arrays;
import java.util.HashSet;

public class SommaSottoinsiemi {
    private int[] a;
    private int[] b;
    private int m;
    private int sommaB;
    private HashSet<HashSet<Integer>> soluzioni = new HashSet<>();
    public SommaSottoinsiemi(int[] a, int m){
        for(int i=0; i<a.length; i++)
            for(int j=0; j<a.length; j++)
                if(i!=j && a[i]==a[j]) throw new IllegalArgumentException();
        this.a = Arrays.copyOf(a, a.length);
        this.b = new int[a.length];
        this.m = m;
    }

    public void risolvi(){
        colloca(0);
    }

    private void colloca(int i){
        for (int j = 0; j < a.length ; j++) {
            if(assegnabile(a[j], i)){
                b[i] = a[j];
                sommaB+=a[j];
                if(sommaB==m) scriviSoluzione(i);
                else colloca(i+1);
                sommaB-=a[j];
            }
        }
    }

    private boolean assegnabile(int v, int i){
        for (int j = 0; j < i ; j++)
            if(b[j] == v) return false;
        return true;
    }

    private void scriviSoluzione(int i){
        HashSet<Integer> soluzione = new HashSet<>();
        for(int j = 0; j <= i; j++) soluzione.add(b[j]);
        if(!soluzioni.contains(soluzione))System.out.println(soluzione);
        soluzioni.add(soluzione);
    }

    public static void main(String[] args) {
        int[] a = {2,-4,5,6,9,1,8};
        int m = 11;
        SommaSottoinsiemi s = new SommaSottoinsiemi(a, m);
        s.risolvi();
    }
}
