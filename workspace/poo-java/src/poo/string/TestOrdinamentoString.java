package poo.string;

import java.util.Comparator;
import poo.util.Array;

public class TestOrdinamentoString{
    public static void main(String[] args) {
        String[] v = {"edificio", "abaco", "tana","cagliostro", "zen", "VOLPE"};
        System.out.println(java.util.Arrays.toString(v));
        Array.insertionSort(v, new StringComparator());
        System.out.println(java.util.Arrays.toString(v));
    }//main
}//TestOrdinamentoString