package poo.appelli.appello02_03_18;

import java.util.*;

public class Labirinto {
    private int N;
    private String CAMERA = "(\\d+\\s+){4}\\d+";
    private Map<Integer, List<Integer>> labirinto;
    private LinkedList<Integer> percorso;
    final int WALL = 0, EXIT = 9999;
    private boolean risolto = false;

    public Labirinto(Map<Integer, List<Integer>> labirinto, int N){
        if(N<1 || N >= EXIT) throw new IllegalArgumentException();
        this.N = N;
        this.labirinto = labirinto;
    }

    public LinkedList<Integer> risolvi(){
        percorso = new LinkedList<>();
        if(labirinto.get(N).contains(EXIT)) risolto = true;
        else trovaPercorso(N);
        if(risolto){
            percorso.addFirst(N);
            percorso.addLast(EXIT);
        }
        return percorso;
    }

    private void trovaPercorso(int cameraIniziale){
        List<Integer> porte = labirinto.get(cameraIniziale);
        for(Integer porta: porte){
            if(porta==WALL) continue;
            percorso.addLast(porta);
            if(labirinto.get(porta).contains(EXIT)){ risolto = true; return;}
            else trovaPercorso(porta);
            percorso.removeLast();
        }
    }

}
