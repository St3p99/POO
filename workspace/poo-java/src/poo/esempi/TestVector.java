package poo.esempi;

import poo.util.*;

public class TestVector{
    public static void main(String[] args) {
        String[] s= {"zaino", "lupo", "tana", "abaco"};
        Vector<String> v = new ArrayVector<>(s.length);
        for (int i = 0; i < s.length; i++){
            String x = s[i];
            int j = 0;
            while(j<v.size() && (v.get(j)).compareTo(x)<0)//essendo un vector di string prende direttamente il tipo dinamico
                j++;
            v.add(j,x);
        }
        System.out.println(v);
    }
}