package poo.calendario;

import java.util.*;
import poo.util.*;

public class Data implements Comparable<Data>{//classi di ogg. immutabili
    public final int G, M, A; //public poiché costanti
    //	public static final int GIORNO=0, MESE=1, ANNO=2; --- Utilizziamo ENUM sotto riportato
    public enum Cosa {GIORNO, MESE, ANNO};// l'enumerazione Cosa é una classe. Quelli tra parentesi graffe sono oggetti della classe Cosa.

    public Data(){
        GregorianCalendar gc = new GregorianCalendar();
        G = gc.get(GregorianCalendar.DAY_OF_MONTH);//DAY_OF_MONTH é una costante static
        M = gc.get((GregorianCalendar.MONTH))+1;//i mesi nella classe G.C. vanno da 0 a 11
        A = gc.get(GregorianCalendar.YEAR);
    }//costruttore data odierna

    public Data(int g, int m, int a){
        if(a<0 || m<1 || m>12 || g<1 || g>durataMese(m,a))
            throw new IllegalArgumentException("Dati sballati");
        G = g; M = m; A = a;
    }

    public Data(Data d){
        G = d.G; M = d.M; A = d.A;
    }

    public int get(Cosa cosa){// Cosa é un enumerazione
        switch(cosa){
            case GIORNO: return G;
            case MESE: return M;
            default: return A;
        }
    }//get

    public static boolean bisestile(int a){
        if(a<0) throw new IllegalArgumentException("Anno negativo");
        if(a%4 !=0) return false;
        if(a%100 == 0 && a%400 != 0) return false;
        return true;
    }//bisestile

    public static int durataMese(int m, int a){
        if(m<1 || m>12 || a<0) throw new IllegalArgumentException("Dati sballati");
        int durata;
        switch(m){
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12: durata = 31; break;

            case 2: durata = bisestile(a)? 29 : 28; break;
            default: durata = 30;
        }//switch
        return durata;
    }//durataMese


	public int distanza(Data d){
        int confronto = this.compareTo(d);
        if(confronto<0) return distanza(this, d);
        else if(confronto>0) return distanza(d, this);
        else return 0;
    }

    private static  int distanza(Data pre, Data post){
        int distanza=post.G - pre.G;
        int mese = pre.M;
        int anno = pre.A;
        while(anno<post.A || anno==post.A && mese<post.M){
            distanza+=durataMese(mese, anno);
            if(mese==12){ mese=0; anno=post.A;}
            mese++;
        }
        anno = pre.A+1;
        for(; anno<post.A; anno++){
                if(bisestile(anno)) distanza+=366;
                else distanza+=365;
        }
        return distanza;
	}//distanza

    public Data giornoPrima(){
        if(A==0 && M==1 && G==1)
            throw new IllegalArgumentException("Non esistono date precedenti a quella data");
        int g1,m1,a1;
        if(G==1){
            if(M==1){ a1=A-1;m1=12; g1 = durataMese(m1,a1);}
            else{ a1=A; m1=M-1; g1=durataMese(m1,a1);}
        }else{ a1=A; m1=M; g1=G-1;}
        return new Data(g1,m1,a1);
    }//giornoPrima

    public Data giornoDopo(){
        int durata=durataMese(M,A);
        int g1, m1, a1;
        if(G==durata){
            g1=1;
            if(M==12){m1=1; a1=A+1;}
            else{m1=M+1; a1 = A;}
        }//if_1
        else{g1=G+1; m1=M; a1=A;}
        return new Data(g1,m1,a1);
    }//giornoDopo

    @Override
    public String toString(){
        return ""+G+"/"+M+"/"+A;
    }

    @Override
    public boolean equals(Object x){
        if(!(x instanceof Data))  // copre anche la verifica che sia null o meno
            return false;
        if(x==this)  // equivale all'equals di Object, uguaglianza di riferimenti
            return true;
        Data d = (Data) x;
        return this.G==d.G && this.M==d.M && this.A==d.A;
    }//equals

    @Override
    public int hashCode(){
        final int MULT=43; // numero primo
        int h=0;
        h=h*MULT+G;
        h=h*MULT+M;
        h=h*MULT+A;
        return h;
    }

    @Override
    public int compareTo(Data d){
        if(this.A > d.A) return 1;
        if(this.A < d.A) return -1;
        //this.A==d.A
        if(this.M > d.M) return 1;    
        if(this.M < d.M) return -1;
        //this.M==d.M
        if(this.G > d.G) return 1;
        if(this.G < d.G) return -1;
        //this.G == d.G
        return 0;
    }

    public static void main(String[] args){
        Data d1 = new Data(1,8,2009);
        Data d2 = new Data(1,3,2010);
        Data d3 = new Data();
        Data d4 = new Data(1,3,1999);
        System.out.println(d1.distanza(d3));
        System.out.println(d3.distanza(d1));
        Data[] v = {d1, d2, d3, d4};
        IO.printlnArray(v);
        Array.selectionSort(v);
        IO.printlnArray(v);
    }

}//Data
