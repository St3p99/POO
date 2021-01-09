package poo.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReplaceDemo {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner( System.in );
        System.out.println("Fornisici il nome del file di testo: ");
        String nomeFile = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader( nomeFile ));
        StringBuilder sb = new StringBuilder(2000);
        for(;;){
            String linea = br.readLine();
            if( linea== null ) break;
            sb.append(linea+ "\n");
        }//for_ever
        br.close();
        String documento = sb.toString();
        documento = documento.replaceAll("java", "JAVA");
        System.out.println(documento);
        sc.close();
    }
}
