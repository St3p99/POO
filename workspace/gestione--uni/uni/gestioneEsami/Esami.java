package gestioneEsami;

import java.util.ArrayList;

public class Esami {
    private ArrayList<Esame> esamiPrimoAnno = new ArrayList<Esame>();
    public final int cfuTotPrimoAnno = 54;

    public final int nEsamiPrimoAnno = 6;

    private ArrayList<Esame> esamiSecondoAnno= new ArrayList<Esame>();
    public final int cfuTotSecondoAnno = 60;

    private ArrayList<Esame> esamiTerzoAnno= new ArrayList<Esame>();
    public final int cfuTotTerzoAnno = 60;

    private ArrayList<ArrayList> esamiTriennale = new ArrayList<ArrayList>();

    public Esami(){
        return;
    }


    public int getCfuTotAnnoAccademico(int annoAccademico){
        switch (annoAccademico){
            case 1: return cfuTotPrimoAnno;
            case 2: return cfuTotSecondoAnno;
            case 3: return cfuTotTerzoAnno;
            default : throw new IllegalArgumentException("Anno accademico errato.");
        }
    }

    private void costruisciEsamiPrimoAnno() {
        esamiPrimoAnno.add(getElementiDiMatematicaComp());
        esamiPrimoAnno.add(getAlgebraLineare());
        esamiPrimoAnno.add(getFondamentiInformatica());
        esamiPrimoAnno.add(getAnalisi1());
        esamiPrimoAnno.add(getRetiLogiche());
        esamiPrimoAnno.add(getFisica1());
    }

    public static Esame getElementiDiMatematicaComp(){
        return new Esame("Elementi di matematica computazionale", 6);
    }

    public static Esame getAlgebraLineare(){
        return new Esame("Algebra lineare e matematica discreta", 6);
    }
    public static Esame getInglese(){
        return new Esame("Inglese", 4);
    }

    public static Esame getFondamentiInformatica(){
        return new Esame("Fondamenti di informatica", 12);
    }

    public static Esame getAnalisi1(){
        return new Esame("Analisi matematica 1", 12);
    }

    public static Esame getRetiLogiche(){
        return new Esame("Reti logiche e calcolatori", 9);
    }

    public static Esame getFisica1(){
        return new Esame("Fisica 1", 9);
    }

    public static Esame getAlgoritmiSD(){
        return new Esame("Algoritmi e strutture dati", 6);
    }

    public static Esame getAnalisi2() {
        return new Esame("Analisi matematica 2", 12);
    }

    public static Esame getElettromElettrotecnica() {
        return new Esame("Elettromagnetismo ed elettrotecnica", 12);
    }

    public static Esame getFondamentiAutomatica(){
        return new Esame("Fondamenti di automatica", 9);
    }

    public static Esame getMetodiProbabilistici(){
        return new Esame("Metodi probabilistici della ricerca operativa", 6);
    }
    public static Esame getPOO(){
        return new Esame("Programmazione orienta agli oggetti", 9);
    }
    public static Esame getSO() {
        return new Esame("Sistemi operativi", 9);
    }

    private void costruisciEsamiSecondoAnno(){
        esamiSecondoAnno.add(new Esame("Algoritmi e strutture dati", 6));
        esamiSecondoAnno.add(new Esame("Analisi matematica 2", 12));
        esamiSecondoAnno.add(new Esame("Elettromagnetismo ed elettrotecnica", 12));
        esamiSecondoAnno.add(new Esame("Fondamenti di automatica", 9));
        esamiSecondoAnno.add(new Esame("Metodi probabilistici della ricerca operativa", 6));
        esamiSecondoAnno.add(new Esame("Programmazione orienta agli oggetti", 9));
        esamiSecondoAnno.add(new Esame("Sistemi operativi", 9));
    }

    private void costruisciEsamiTerzoAnno(){
        ArrayList<Esame> esamiTerzoAnno = new ArrayList<>();
        esamiTerzoAnno.add(new Esame());
    }

    private void costruisciEsamiTriennale(){
        esamiTriennale.add(esamiPrimoAnno);
        esamiTriennale.add(esamiSecondoAnno);
        esamiTriennale.add(esamiTerzoAnno);
    }

    public ArrayList<Esame> getEsamiPerAnnoAccademico(int annoAccademico) {
        switch(annoAccademico){
            case 1: costruisciEsamiPrimoAnno();
                    return new ArrayList<Esame>(esamiPrimoAnno);
            case 2: costruisciEsamiSecondoAnno();
                    return new ArrayList<Esame>(esamiSecondoAnno);
            case 3: costruisciEsamiTerzoAnno();
                    return new ArrayList<Esame>(esamiTerzoAnno);
            default : throw new IllegalArgumentException("Anno accademico errato.");
        }
    }


    public ArrayList<ArrayList> getEsamiTriennale() {
        return new ArrayList<ArrayList>(esamiTriennale);
    }
}
