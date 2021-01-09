package gestioneStudente;

import java.util.*;
import util.*;
import gestioneEsami.*;

/**
 Oggetto studente(Triennale di ingegneria Informatica).
 Ogni studente é identificato dalla sua matricola, ed é caratterizzato da nome, cognome, anno di nascita,
 cfu e anno accademico.
 Inoltre sono presenti metodi per il calcolo della media ponderata, cfu rimanenti per la laurea, ecc...
 * */

public class Studente implements Comparable<Studente>{
    // attributi
    private String nome;
    private String cognome;
    private int annoDiNascita;
    private int mat;
    private int cfu;
    /** Un anno accademico é definito valido se vale 1, 2 o 3.*/
    private int annoAccademico;

    private HashMap<Esame, String> esamiSuperati = new HashMap<>();
    private Esami datiAnnoAcc;
    private ArrayList<Esame> esamiMancanti = new ArrayList<Esame>();
    private double mediaPon;
    private double sommaVotiPon;
    public final int cfuTotTriennale = 180;


    /** Costruisce l'oggetto studente a partire da nome, cognome, etá, matricola e anno accademico.
     * @param nome conterrá il nome dello studente
     * @param cognome conterrá il cognome dello studente
     * @param annoDiNascita conterrá l'anno di nascita dello studente
     * @param mat conterrá il numero di matricola dello studente, il quale lo identifica.
     * @param annoAccademico rappresenterá l'anno accademico dello studente (primo=1, secondo=2 o terzo=3)
     * */
    public Studente(String nome, String cognome, int annoDiNascita, int mat,  int annoAccademico)  {
        if(annoAccademico>3 || annoAccademico<1) throw new IllegalArgumentException("Anno accedemico errato.");
        this.nome = nome;
        this.cognome = cognome;
        this.annoDiNascita = annoDiNascita;
        this.mat = mat;
        this.annoAccademico = annoAccademico;
        this.datiAnnoAcc = new Esami();
        this.esamiMancanti = new ArrayList<>();
        int i = 1;
        while( i <= annoAccademico){
            esamiMancanti.addAll(datiAnnoAcc.getEsamiPerAnnoAccademico(i));
            i++;
        }
    }


    /**Costruttore per copia.
     * Costruisce un oggetto studente identico a quello ricevuto
     * @param s lo studente identico a quello che vogliamo costruire.
     * */
    public Studente(Studente s) {
        this.nome = s.nome;
        this.cognome = s.cognome;
        this.annoDiNascita = s.annoDiNascita;
        this.mat = s.mat;
        this.annoAccademico = s.annoAccademico;
        this.datiAnnoAcc = new Esami();
        this.esamiMancanti = new ArrayList<Esame>(datiAnnoAcc.getEsamiPerAnnoAccademico(s.annoAccademico));
    }

    /**Restituisce il nome dello studente
     * @return  il nome dello studente
     * */
    public String getNome() {
        return nome;
    }

    /**Restituisce il cognome dello studente
     * @return  il cognome dello studente
     * */
    public String getCognome() {
        return cognome;
    }

    /**Restituisce l'etá dello studente
     * @return  l'etá dello studente
     * */
    public int getAnnoDiNascita() {
        return annoDiNascita;
    }

    /**Restituisce il numero di matricola dello studente
     * @return  il numero di matricola dello studente
     * */
    public int getMat() {
        return mat;
    }

    /**Restituisce l'anno accademico dello studente
     * @return  l'anno accademico dello studente
     * */
    public int getAnnoAccademico(){
        return annoAccademico;
    }

    /**Restituisce i cfu ottenuti dallo studente
     * @return  i cfu ottenuti dallo studente
     * */
    public int getCfu() {
        return cfu;
    }

    /**Restituisce una matrice rappresentate gli esami superati dallo studente, con i corrispondenti cfu e voti.
     * @return Matrice rappresentante gli esami superati dallo studente
     * */
    public HashMap<Esame, Integer> getEsamiSuperati() {
        return new HashMap(esamiSuperati);
    }

    /**Restituisce una matrice rappresentate gli esami non ancora superati dallo studente, con i corrispondenti cfu.
     * @return Matrice rappresentante gli esami non ancora superati dallo studente
     * */
    public ArrayList<Esame> getEsamiMancanti(){
        return new ArrayList<Esame>(esamiMancanti);
    }

    /**Restituisce una matrice rappresentate tutti gli esami dell'anno accademico dello studente, con i corrispendti cfu.
     * @return Matrice rappresentante tutti gli esami dell'anno accademico dallo studente
     * */
    public ArrayList<Esame> getEsamiAnnoAccademico(){
        return new ArrayList<Esame>(datiAnnoAcc.getEsamiPerAnnoAccademico(annoAccademico));}

    /**Restituisce i cfu mancanti per il corrispondente anno accademico dello studente
     * @return i cfu mancanti per il corrispondente anno accademico dello studente
     * */
    public int getCfuMancantiAnnoAccademico(){
        int cfuTot = datiAnnoAcc.cfuTotPrimoAnno;
        return cfuTot - this.cfu;
    }

    /**Restituisce i cfu mancanti per laurearsi dello studente
     * @return i cfu mancanti per laurearsi dello studente
     * */
    public int getCfuMancantiTriennale(){
        return cfuTotTriennale - this.cfu;
    }

    /**Restituisce la media ponderata dei voti ottenuti dallo studente
     * @return la media ponderata dei voti ottenuti dallo studente
     * */
    public double getMediaPonderata(){
        return mediaPon;
    }

    public double getBaseLaurea(){
        return mediaPon*110.0/30.0;
    }

    // metodi standard

    /**Rappresenta l'oggetto come stringa
     * @return una stringa che rappresenta l'oggetto
     * */
    @Override
    public String toString() {
        return "Studente: "+nome +  " " + cognome +" con N. matricola="+mat;
    }

    /**Verifica se l'oggetto é uguale ad un altro oggetto studente
     * @param obj un oggetto studente
     * @return true se l'oggetto é uguale ad obj; false altrimenti.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Studente)) return false;
        Studente o = (Studente) obj;
        return this.mat == o.mat;
    }

    /**
     Calcola un valore hash per l'oggetto.
     @return il valore hash.
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode(); //return mat;
    }

    // mutatori
    /** Aggiunge un esame superato dallo studente con il corrispettivo voto, e lo rimuove da quelli non superati.
     * @param e l'esame da aggiungere
     * @param voto il voto ottenuto per l'esame da aggiungere
    */
    public void aggEsame(Esame e, int voto, boolean lode) {
        if(esamiSuperati.containsKey(e))
            return;
        if(voto<18 || voto>30 || (voto!=30 && lode)) throw new IllegalArgumentException("Voto errato.");
        if(lode){
            esamiSuperati.put(e, voto+"L");
            voto+=3;
        }
        else esamiSuperati.put(e, voto+"");
        this.cfu += e.getCfu();
        sommaVotiPon += voto * e.getCfu();
        calcolaMediaPon();
        esamiMancanti.remove(e);
    }

    public void aggEsame(Esame e, int voto){
        aggEsame(e,voto, false);
    }


    /** Calcola la media ponderata in base ai voti ottenuti dallo studente
     */
    private void calcolaMediaPon(){
        mediaPon = sommaVotiPon/(cfu);
    }

    // comparator

    public static final Comparator<Studente> etaComp
            = new etaComparator();
    /** Permette di confrontare gli oggetti studente in base all'etá.
     * Uno studente é definito maggiore rispetto ad un altro (per etá) se la sua etá é maggiore, quindi se
     * l'anno di nascita é minore.
     * */
    public static class etaComparator implements Comparator<Studente> {
        public int compare(Studente s1, Studente s2){
            // s2 - s1 poiché maggiore é l'etá e minore é la data di nascita
            return s2.getAnnoDiNascita() - s1.getAnnoDiNascita();
        }
    }

    public static final Comparator<Studente> cfuComp
            = new cfuComparator(); // CHIEDERE PERCHE FINAL
    /** Permette di confrontare gli oggetti studente in base ai cfu ottenuti.
     * Uno studente é definito maggiore rispetto ad un altro (per numero di cfu ottenuti) se il numero di cfu é maggiore.
     * */
    public static class cfuComparator implements Comparator<Studente> {
        public int compare(Studente s1, Studente s2){
            return s1.getCfu() - s2.getCfu();
        }
    }

    public static final Comparator<Studente> matComp
            = new matComparator(); // CHIEDERE PERCHE FINAL
    /** Permette di confrontare gli oggetti studente in base al numero di matricola.
     * Uno studente é definito maggiore rispetto ad un altro (per numero di matricola) se il numero di matricola é maggiore.
     * */
    public static class matComparator implements Comparator<Studente> {
        public int compare(Studente s1, Studente s2){
            return s1.getMat() - s2.getMat();
        }
    }


    public static final Comparator<Studente> cognomeComp
            = new cognomeComparator(); // CHIEDERE PERCHE FINAL
    /** Permette di confrontare gli oggetti studente in base al cognome (ordine alfabetico).
     * */
    public static class cognomeComparator implements Comparator<Studente> {
        public int compare(Studente s1, Studente s2){
            return s1.getCognome().compareTo(s2.getCognome());
        }
    }

    public static final Comparator<Studente> mediaComp
            = new mediaComparator(); // CHIEDERE PERCHE FINAL
    /** Permette di confrontare gli oggetti studente in base alla media ottenuta.
     * Uno studente é definito maggiore rispetto ad un altro (per valore della media ottenuta) se il valore della media é maggiore.
     * */
    public static class mediaComparator implements Comparator<Studente> {
        public int compare(Studente s1, Studente s2){
            return (int) (s1.getMediaPonderata() - s2.getMediaPonderata());
        }
    }

    // compareTo

    /** Confronta gli oggetti studente in base al cognome (ordine alfabetico)
     * @param s lo studente con cui fare il confronto
     * @return il valore del confronto: positivo se l'oggetto é maggiore
     * di s, negativo se é minore di s oppure pari a 0 se uguali.
     * */
    public int compareTo(Studente s){
        return cognomeComp.compare(this, s);
    }

    /** Confronta gli oggetti studente in base ai cfu ottenuti
     * @param s lo studente con cui fare il confronto
     * @return il valore del confronto: positivo se l'oggetto é maggiore
     * di s, negativo se é minore di s oppure pari a 0 se uguali.
     * */
    public int compareToCfu(Studente s){
        return cfuComp.compare(this, s);
    }

    /** Confronta gli oggetti studente in base al numero di matricola
     @param s lo studente con cui fare il confronto
     @return il valore del confronto: positivo se l'oggetto é maggiore
     di s, negativo se é minore di s oppure pari a 0 se uguali.
     * */
    public int compareToMatricola(Studente s){
        return matComp.compare(this, s);
    }

    /** Confronta gli oggetti studente in base all'etá
     * @param s lo studente con cui fare il confronto
     * @return il valore del confronto: positivo se l'oggetto é maggiore
     * di s, negativo se é minore di s oppure pari a 0 se uguali.
     * */
    public int compareToEta(Studente s){
        return etaComp.compare(this, s);
    }

    /** Confronta gli oggetti studente in base alla media ottenuta
     * @param s lo studente con cui fare il confronto
     * @return il valore del confronto: positivo se l'oggetto é maggiore
     * di s, negativo se é minore di s oppure pari a 0 se uguali.
     * */
    public int compareToMediaVoti(Studente s){
        return mediaComp.compare(this, s);
    }

    // sort
    public static void sortByAge(ArrayList<Studente> s){
        Collections.sort(s, etaComp);
    }

    public static void sortByCfu(ArrayList<Studente> s){
        Collections.sort(s, cfuComp);
    }

    public static void sortBySurname(ArrayList<Studente> s){
        Collections.sort(s, cognomeComp);
    }

    public static void sortBySerialNum(ArrayList<Studente> s){
        Collections.sort(s, matComp);
    }

    public static void sortByAverage(ArrayList<Studente> s){
        Collections.sort(s, mediaComp);
    }

}
