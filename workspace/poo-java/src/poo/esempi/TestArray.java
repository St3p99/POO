package poo.esempi;

import poo.util.ArrayVector;
import poo.util.Vector;
import poo.string.StringComparator;

import java.util.Comparator;

public class TestArray {
    public static void main(String[] args) {
        Vector<String> vs = new ArrayVector<>();
        poo.util.Array.bubbleSort(vs, new StringComparator());
        //creaiamo e istanziamo un classe anonima
        poo.util.Array.bubbleSort(vs, (s1, s2) -> {
            if(s1.length()<s2.length() ||
                s1.length()==s2.length() &&
                s1.compareTo(s2)<0) return -1;
            if(s1.equals(s2)) return 0;
            return 1;
        });
    }
}
