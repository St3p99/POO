package poo.appelli.appello23_07_19.sol_2;

import java.io.* ;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DataOutputStream fileTest = new DataOutputStream(
                new FileOutputStream("C:\\Users\\stp-99\\Documents\\poo_file\\appello23_07_19\\testSol2.dat"));
        fileTest.writeInt(42);fileTest.writeInt(22);fileTest.writeInt(4);fileTest.writeInt(7);
        fileTest.writeInt(9);fileTest.writeInt(3);fileTest.writeInt(2);fileTest.writeInt(5);
        fileTest.close();

        RandomAccessFile leggiTest = new RandomAccessFile(
                "C:\\Users\\stp-99\\Documents\\poo_file\\appello23_07_19\\testSol2.dat", "r");
        int i =0;
        while(i< leggiTest.length()){
            System.out.print(leggiTest.readInt()+", ");
            i+=4;
        }
        leggiTest.close();
        System.out.println();

        String nomeFile;
        Scanner sc = new Scanner( System.in);
        System.out.print("Inserisci il path del file di interi ordinare\n>");
        for(;;){
            nomeFile = sc.nextLine();
            File f = new File(nomeFile);
            if(f.exists()){ break;}
            System.out.print("Il file non esiste, reinserisci il path del file\n>");
        }
        FileInt.ordina(nomeFile);

        RandomAccessFile leggiTest_2 = new RandomAccessFile("C:\\Users\\stp-99\\Documents\\poo_file\\appello23_07_19\\testSol2.dat", "r");
        i =0;
        while(i< leggiTest_2.length()){
            System.out.print(leggiTest_2.readInt()+", ");
            i+=4;
        }
    }
}
