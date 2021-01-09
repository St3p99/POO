package poo.appelli.appelloStraordinario;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static Elenco<Parola> ripristina(String nomeFile) throws IOException{
        Elenco<Parola> elenco = new ElencoConcatenato<>();
        BufferedReader br = new BufferedReader(new FileReader(nomeFile));
        String linea;
        for(;;){
            linea = br.readLine();
            if(linea == null) break; //EOF
            elenco.add(new Parola(linea));
        }
        br.close();
        return elenco;
    }

    public static void main(String[] args){
        Elenco<Parola> elenco = new ElencoConcatenato<>();
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = null;
        String linea = null;
        String COMANDO_W_PARAM = "[A-Za-z]\\s+.*";
        String COMANDO_WOUT_PARAM = "[A-Za-z]";
        comandi();
        System.out.println("Inserisci comando: ");
        ciclo: for(;;){
            linea = sc.nextLine();
            if(! (linea.matches(COMANDO_W_PARAM) || linea.matches(COMANDO_WOUT_PARAM))){
                System.out.println("Comando non valido"); continue;
            }
            st = new StringTokenizer(linea, " ");
            char comando = (st.nextToken().toUpperCase()).charAt(0);
            switch (comando){
                case 'A': {
                    if(!linea.matches(COMANDO_W_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    StringBuilder parola = new StringBuilder(100);
                    while(st.hasMoreTokens()){
                        parola.append(st.nextToken());
                        if(st.hasMoreTokens()) parola.append(" ");
                    }
                    elenco.add(new Parola(parola.toString()));
                    break;
                }
                case 'D':{
                    if(!linea.matches(COMANDO_WOUT_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    if(elenco.size()==0) System.out.println("*elenco vuoto*");
                    System.out.println(elenco);
                    break;
                }
                case 'C':{
                    if(!linea.matches(COMANDO_WOUT_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    elenco.compatta();
                    break;
                }
                case 'E':{
                    if(!linea.matches(COMANDO_W_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    StringBuilder parola = new StringBuilder(100);
                    while(st.hasMoreTokens()){
                        parola.append(st.nextToken());
                        if(st.hasMoreTokens()) parola.append(" ");
                    }
                    elenco.remove(new Parola(parola.toString()));
                    break;
                }
                case 'O':{
                    if(!linea.matches(COMANDO_WOUT_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    elenco.ordina();
                    break;
                }
                case 'P':{
                    if(!linea.matches(COMANDO_W_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    StringBuilder nomeFile = new StringBuilder(100);
                    while(st.hasMoreTokens()){
                        nomeFile.append(st.nextToken());
                        if(st.hasMoreTokens()) nomeFile.append(" ");
                    }
                    try{
                        elenco.scarica(nomeFile.toString());
                    }catch (IOException e){
                        System.out.println("nome file non valido");
                    }
                    break;
                }
                case 'R':{
                    if(!linea.matches(COMANDO_W_PARAM)){
                        System.out.println("Comando non valido"); continue;
                    }
                    StringBuilder nomeFile = new StringBuilder(100);
                    while(st.hasMoreTokens()){
                        nomeFile.append(st.nextToken());
                        if(st.hasMoreTokens()) nomeFile.append(" ");
                    }
                    try{
                        elenco = ripristina(nomeFile.toString());
                    }catch (IOException e){
                        System.out.println("nome file non valido");
                    }
                    break;
                }
                case 'T': { sc.close(); break ciclo;}
                default:
                    System.out.println("Comando non valido");
            }
        }
    }


    public static void comandi(){
        System.out.println("AGGIUNGI: A nome");
        System.out.println("VISUALIZZA: D");
        System.out.println("COMPATTA: C");
        System.out.println("RIMUOVI: E");
        System.out.println("ORDINA: O");
        System.out.println("SCARICA: P");
        System.out.println("RIPRISTINA: R");
        System.out.println("TERMINA: T");
    }
}
