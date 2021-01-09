package poo.esempi;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import poo.calendario.Data;


public class TestSet{   
    public static void main(String[] args) {
        HashSet<Integer> insieme = new HashSet<>();
        insieme.add(5); insieme.add(4); insieme.add(0);
        Integer x = 5;
        System.out.println("hashCode di Integer(5): " + x.hashCode());
        System.out.println("HashSet creato in ordine [5,4,0]-->"+insieme+ "  E' SOLO UN CASOOOOOO");
        TreeSet<Data> date = new TreeSet<>();
        date.add(new Data()); date.add(new Data(10,3,2010)); date.add(new Data(10,2,2018));
        System.out.println("TreeSet ordina il Set usando compareTo sull'oggetto"+date);
        LinkedHashSet<Data> dateLHS = new LinkedHashSet<>();
        dateLHS.add(new Data()); dateLHS.add(new Data(10,3,2010)); dateLHS.add(new Data(10,2,2018));
        System.out.println("LinkedList mantiene ordine di inserimento:"+dateLHS);

    }
}