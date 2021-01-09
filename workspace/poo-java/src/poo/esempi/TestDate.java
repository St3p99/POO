package poo.esempi;

import poo.calendario.Data;
import poo.util.*;


public class TestDate{
    public static int distanza(Data pre, Data post){
        int distanza=post.G - pre.G;
        int mese = pre.M;
        int anno = pre.A;
        while(anno<post.A || anno==post.A && mese<post.M){
            distanza+=Data.durataMese(mese, anno);
            if(mese==12){ mese=0; anno=post.A;}
            mese++;
        }
        anno = pre.A+1;
        for(; anno<post.A; anno++){
                if(Data.bisestile(anno)) distanza+=366;
                else distanza+=365;
        }
        return distanza;
	}//distanza

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
}