package poo.esempi;

import java.util.Scanner;

public class TestString{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();
        linea = linea.trim();
        int i = linea.indexOf(" ");
        String cognome = linea.substring(0, i);
        while(i<linea.length() && linea.charAt(i)=='b')
            i++;
        String nome = linea.substring(i);
        System.out.println(nome.charAt(0)+" "+cognome);
        sc.close();
    }
}