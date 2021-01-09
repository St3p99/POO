package poo.appelli.appello23_07_19.sol_1;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    public static void main(String... args) throws IOException {
        DataOutputStream fileTest = new DataOutputStream(new FileOutputStream(
                "C:\\Users\\stp-99\\Documents\\poo_file\\appello23_07_19\\testSol1.dat"));
        fileTest.writeInt(10);fileTest.writeInt(1);fileTest.writeInt(4);fileTest.writeInt(2);
        fileTest.writeInt(1);fileTest.writeInt(32);fileTest.writeInt(12);fileTest.writeInt(0);
        fileTest.close();

        Scanner sc = new Scanner( System.in );
        System.out.print("Inserisci il path del file da ordinare\n>");
        String nomeFile = "";
        for(;;){
            nomeFile = sc.nextLine();
            File f = new File(nomeFile);
            if(f.exists()) break;
            System.out.print("Il file non esiste, reinsirisci il path del file\n>");
        }

        DataInputStream dis = new DataInputStream( new FileInputStream(nomeFile));
        ListaConcatenata<Integer> lc = new ListaConcatenata<>();
        for(;;){
            try {
                lc.add(dis.readInt());
            }
            catch (EOFException e){
                dis.close(); break;
            }
        }
        System.out.println(lc);
        lc.sort();
        System.out.println(lc);
        File f = new File(nomeFile); f.delete();

        DataOutputStream fSorted = new DataOutputStream( new FileOutputStream(nomeFile));
        for(Integer x: lc){
            fSorted.writeInt(x);
        }
        fSorted.close();
    }
}
