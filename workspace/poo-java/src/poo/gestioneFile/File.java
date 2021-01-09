package poo.gestioneFile;


import java.io.*;
import java.util.Scanner;

public class File {
    private static Object Class;

    public static boolean ricercaBinaria(String nome, int x) throws IOException{
        RandomAccessFile f = new RandomAccessFile(nome, "r"); //  "r" modalitá read
        long inf=0;
        long sup = (f.length()/4) - 1;
        for(;;){
            if(inf>sup) break;
            long med = (inf+sup)/2;
            f.seek( med*4 );
            int elem = f.readInt();
            if( elem == x ) return true;
            if( elem > x ) sup = med - 1;
            else inf = med + 1;
        }
        f.close();
        return false;
    }

    public static void mergeFile(String pathSrc1, String pathSrc2, String pathDst) throws IOException{
        RandomAccessFile src1 = new RandomAccessFile(pathSrc1, "r");
        RandomAccessFile src2 = new RandomAccessFile(pathSrc2, "r");
        RandomAccessFile dst = new RandomAccessFile(pathDst, "rw");
        long length1 = src1.length(), length2 = src2.length();
        int i = 0, j =0;
        int x1, x2;
        while(i < length1 && j < length2){
            x1 = src1.readInt(); x2 = src2.readInt();
            if(x1 <= x2){
                dst.writeInt(x1);
                i+=4; src2.seek(j);
            }
            else{
                dst.writeInt(x2);
                j+=4; src1.seek(i);
            }
        }
        while(i<length1){//dati rimanenti src1
            x1 = src1.readInt();
            dst.writeInt(x1); i+=4;
        }

        while(j<length2){//dati rimanenti src2
            x2 = src2.readInt();
            dst.writeInt(x2); j+=4;
        }
        src1.close(); src2.close(); dst.close(); //chiudi il gas e vieni via
    }

    /*public static <T extends Comparable<? super T> & Serializable> void mergeObjectFile(String pathSrc1, String pathSrc2, String pathDst) throws IOException, ClassNotFoundException {
        // si assume che gli oggetti sono comparabili e che i file siano ordinati
        poo.file.ObjectFile src1 = new poo.file.ObjectFile(pathSrc1, ObjectFile.Modo.LETTURA);
        poo.file.ObjectFile src2 = new poo.file.ObjectFile(pathSrc2, ObjectFile.Modo.SCRITTURA);
        poo.file.ObjectFile dst = new poo.file.ObjectFile(pathDst, ObjectFile.Modo.SCRITTURA);
        Comparable e1, e2;
        for (; ; ) {
            try {
                e1 = (Serializable) src1.peek();
                e2 = (Serializable) src2.peek();
                if (e1.)
            } catch (EOFException e) {
                break;
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        RandomAccessFile f1 = new RandomAccessFile("src1.dat", "rw");
        RandomAccessFile f2 = new RandomAccessFile("src2.dat", "rw");
        RandomAccessFile f3 = new RandomAccessFile("dst.dat", "rw");
        f1.writeInt(2); f1.writeInt(4); f1.writeInt(8); f1.writeInt(12); f1.writeInt(19);
        f2.writeInt(1); f2.writeInt(3); f2.writeInt(9); f2.writeInt(15); f2.writeInt(22);
        f1.close(); f2.close(); f3.close(); //chiudi il gas e vieni via

        System.out.println("Fusione ordinata di due file di interi src1 ed src2 in un file dst");
        Scanner sc=new Scanner( System.in );
        System.out.print("path src1 = ");
        String src1=sc.nextLine();
        System.out.print("path src2 = ");
        String src2=sc.nextLine();
        System.out.print("path dst = ");
        String dst=sc.nextLine();
        mergeFile(src1, src2, dst);
        System.out.println(stampaRafInt(dst));
    }

    public static String stampaRafInt(String path) throws IOException {
        RandomAccessFile f = new RandomAccessFile(path, "r");
        StringBuilder sb = new StringBuilder(500);
        sb.append("[");
        int i=0;
        while(i<f.length()){
            sb.append(f.readInt()); i+=4;
            sb.append(", ");
        }
        sb.setLength(sb.length()-2);
        sb.append("]");
        return sb.toString();
    }

}
