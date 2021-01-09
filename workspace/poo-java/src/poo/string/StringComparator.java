package poo.string;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {
    public int compare(String s1, String s2){
        //String s1 = (String) x1; String s2 = (String) x2;
        if(s1.length() < s2.length()) return -1;
        if(s1.length() > s2.length()) return 1;
        return s1.compareTo(s2);
    }//compare
}//StringComparator
