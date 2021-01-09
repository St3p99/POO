package poo.esempi;

import poo.util.*;
import java.util.Scanner;

public class TestPalindroma {
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        String INPUT = "\\w+\\$\\w+";
        System.out.println("Fornisci String1$String2 > ");
        String linea = sc.nextLine();
        sc.close();
        if(! linea.matches(INPUT)){
            System.out.println(linea+" scorretta");
            System.exit(-1);
        }

        Stack<Character> pila = new StackConcatenato<>();
        int i=0;
        while(i<linea.length() && linea.charAt(i)!='$'){
            pila.push(linea.charAt(i));
            i++;
        }
        i++; // salta dollaro
        while(i<linea.length()){
            if(linea.charAt(i)!=pila.pop()) break;
            else i++;
        }
        if(i < linea.length() || ! pila.isEmpty())
            System.out.println(linea+ " non é palindroma");
        else
            System.out.println(linea+ " é palindroma");
    }//main
}//TestPalindroma
