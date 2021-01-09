package poo.appelli.appello09_01_20;

import javax.print.DocFlavor;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class StatisticheVoti {
    public static void main(String[] args) throws IOException {
        BufferedReader poo = new BufferedReader(new FileReader("votiPoo.txt"));
        BufferedReader elettrom = new BufferedReader(new FileReader("votiElettrom.txt"));
        StringTokenizer st;
        HashMap<String, String> matricolaNome = new HashMap<>();
        for(;;){
            String linea = elettrom.readLine();
            if(linea==null) break;
            st = new StringTokenizer(linea, " ");
            String nome = "";
            String matricola = "";
            while(st.hasMoreTokens()){
                String tk = st.nextToken();
                if(tk.matches("\\d+")){ matricola += tk; break;}
                nome+=tk+" ";
            }
            matricolaNome.put(matricola, nome);

        }
        elettrom.close();
        System.out.println(matricolaNome);
        HashMap<String, Integer> voti = new HashMap<>();
        int ammessi = 0;
        int riserva = 0;
        int bocciati = 0;
        for(;;){
            String linea = poo.readLine();
            if(linea==null) break;
            st = new StringTokenizer(linea, " ");
            String MAT = st.nextToken();
            int voto = Integer.parseInt(st.nextToken());
            voti.put(MAT, voto);
            switch (st.nextToken()){
                case "SI":{ ammessi++; break;}
                case "AR":{ riserva++; break;}
                default: bocciati++;
            }
        }
        poo.close();
        StringBuilder tabellaVoti = new StringBuilder(500);
        System.out.println("Ammessi: "+ammessi);
        System.out.println("Ammessi con riserva: "+riserva);
        System.out.println("Bocciati: "+bocciati);
        double sommaVoti = 0;
        TreeMap<Integer, Integer> votiOccorrenze = new TreeMap<>();
        for(String mat: voti.keySet()){
            if(matricolaNome.containsKey(mat)) tabellaVoti.append(matricolaNome.get(mat)+" ");
            int voto = voti.get(mat);
            tabellaVoti.append(mat+" = "+voto+"\n");
            sommaVoti+=voto;
            if(!votiOccorrenze.containsKey(voto)) votiOccorrenze.put(voto, 1);
            else votiOccorrenze.put(voto, votiOccorrenze.get(voto)+1);
        }
        System.out.println("voto = occorrenze --> "+votiOccorrenze);
        System.out.println("media: "+sommaVoti/voti.size());
        System.out.println("IO: "+voti.get("201130"));
        System.out.println(tabellaVoti.toString());

        PrintWriter pw = new PrintWriter(new FileWriter("votiPooWName.txt"));
        pw.println(tabellaVoti.toString());
        pw.close();
    }
}
