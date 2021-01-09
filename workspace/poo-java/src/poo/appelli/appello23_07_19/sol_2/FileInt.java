package poo.appelli.appello23_07_19.sol_2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileInt {
    static void ordina(String nomeFile) throws IOException {
        RandomAccessFile f = new RandomAccessFile(nomeFile, "r");
        if(f.length() <= 4) return;
        RandomAccessFile tmp = new RandomAccessFile("tmp", "rw");
        int x = f.readInt();
        tmp.writeInt(x);
        int maxTmp = x;
        long i = 1;
        while(i*4 < f.length()){ //riempie tmp, copia di f, in modo ordinato
            x = f.readInt();
            if(x>maxTmp){
                tmp.writeInt(x); maxTmp = x;
            }
            else{
                tmp.close();
                tmp = inserisci("tmp", x);
            }
            i++;
        }
        f.close(); File f_del = new File(nomeFile); f_del.delete(); //elimina f
        DataOutputStream d = new DataOutputStream(new FileOutputStream(nomeFile));
        i = 0; tmp.seek(0);
        while(i < tmp.length()){//copia tmp in d
            d.writeInt(tmp.readInt());
            i+=4;
        }
        d.close();
        tmp.close(); File tmp_del = new File("tmp"); tmp_del.delete(); // elimina tmp
    }

    static RandomAccessFile inserisci(String nomeFile, int e) throws IOException {
        RandomAccessFile f = new RandomAccessFile(nomeFile, "r");
        RandomAccessFile tmp = new RandomAccessFile("temp", "rw");
        long i = 0; boolean flag = false;
        while(i < f.length()){
            int x = f.readInt();
            if(!flag && x >= e ){
                tmp.writeInt(e); flag = true;
            }
            tmp.writeInt(x);
            i+=4;
        }
        if(!flag) tmp.writeInt(e);
        f.close(); File f_del = new File(nomeFile); f_del.delete(); // elimina f
        RandomAccessFile f_2 = new RandomAccessFile(nomeFile, "rw");
        i = 0; tmp.seek(0);
        while(i< tmp.length()){
            f_2.writeInt(tmp.readInt());
            i+=4;
        }
        tmp.close(); File tmp_del = new File("temp"); tmp_del.delete(); //elimina tmp
        return f_2;
    }

}
