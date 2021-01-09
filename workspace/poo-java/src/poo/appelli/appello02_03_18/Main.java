package poo.appelli.appello02_03_18;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String... args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserire il numero delle camere (tra 1 e 9999)\n>");
        final int N = Integer.parseInt(sc.nextLine());

        System.out.println("Inserire il path del file che descrive il labirinto\n>");
        String nomeFile = sc.nextLine();

        String CAMERA = "(\\d+\\s+){4}\\d+";
        Map<Integer, List<Integer>> percorsi = new HashMap<>();
        /* leggi file */
        boolean okLettura = true;
        try{
            BufferedReader br = new BufferedReader(new FileReader(nomeFile));
            int c = 0;
            for(;;){
                String linea = br.readLine();
                if(linea==null) break;
                if( ! linea.matches(CAMERA)) throw new IOException();
                StringTokenizer st = new StringTokenizer(linea, " ");
                LinkedList<Integer> porte = new LinkedList<>();
                int camera  = Integer.parseInt(st.nextToken());
                if(camera<1 || camera > N){
                    okLettura = false; break;
                }
                while(st.hasMoreTokens()){
                    int porta = Integer.parseInt(st.nextToken());
                    if(porta<0 || (porta>N && porta != 9999) || porta == camera ){
                        okLettura=false; break;
                    }
                    porte.add(porta);
                }
                percorsi.put(camera, porte);
                c++;
            }
            if(c!=N) okLettura= false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(! okLettura){
            System.out.println("File non valido"); return;
        }

        System.out.println("Inserire il numero della camera di partenza\n>");
        int da = Integer.parseInt(sc.nextLine());
        sc.close();

        System.out.println(percorsi);
        Labirinto lab = new Labirinto(percorsi, da);
        LinkedList<Integer> soluzione = lab.risolvi();
        if(soluzione.size()==0) System.out.println("Nessuna soluzione");
        else{
            System.out.print("Percorso risolutivo: ");
            int i = 0;
            for(Integer x: soluzione){
                System.out.print(x);
                if(i!=soluzione.size()-1) System.out.print("-");
                i++;
            }
        }
    }
}
