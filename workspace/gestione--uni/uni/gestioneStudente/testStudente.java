package gestioneStudente;

import gestioneEsami.*;
import util.*;

import java.util.ArrayList;
import java.util.Collections;

public class testStudente {
    public static void main(String[] args){
        Studente io = new Studente("Stefano", "Perna", 1999, 201130, 2);

       Esame Elementi = new Esame("Elementi di matematica computazionale", 6);
        io.aggEsame(Esami.getElementiDiMatematicaComp(),30);
        io.aggEsame(Esami.getAlgebraLineare(), 24);
        io.aggEsame(Esami.getAnalisi1(), 28);
        io.aggEsame(Esami.getFondamentiInformatica(), 30);

        //io.aggEsame(Esami.getFisica1(), 30, false);
        io.aggEsame(Esami.getFisica1(), 30, true);

        //io.aggEsame(Esami.getRetiLogiche(), 30, false);
        io.aggEsame(Esami.getRetiLogiche(), 30, true);

        io.aggEsame(Esami.getPOO(), 28);
        io.aggEsame(Esami.getMetodiProbabilistici(), 26);
        io.aggEsame(Esami.getAnalisi2(), 24);
        io.aggEsame(Esami.getElettromElettrotecnica(), 28);

        //io.aggEsame(Esami.getAlgoritmiSD(), 30, false);
        io.aggEsame(Esami.getAlgoritmiSD(), 30, true);
        io.aggEsame(Esami.getSO(), 29);
        //io.aggEsame(Esami.getFondamentiAutomatica(), ?);




        IO.println(io);
        IO.println(" media="+io.getMediaPonderata());
        IO.println(" Base Di Laurea ="+io.getBaseLaurea());
        IO.println("ESAMI MANCANTI");
        for(Esame e: io.getEsamiMancanti())
            IO.println(e);
        IO.println();
        IO.println("ESAMI SUPERATI ");
        for(Esame e: io.getEsamiSuperati().keySet()){
            IO.println(e+" ESITO="+io.getEsamiSuperati().get(e));
        }

//        ArrayList studenti = new ArrayList<Studente>();
//        studenti.add(io);
//        Studente.sortByAge(studenti);
//        IO.println("sortByAge: "+ studenti);
//        Studente.sortByCfu(studenti);
//        IO.println("sortByCfu: "+ studenti);
//        Studente.sortBySerialNum(studenti);
//        IO.println("sortSerialNum: "+ studenti);
//        Studente.sortBySurname(studenti);
//        IO.println("sortBySurname: "+ studenti);
//        Collections.sort(studenti, Studente.mediaComp); // funziona anche cosi
//        IO.println("sortByAverage (Collections): "+ studenti);
    }
}
