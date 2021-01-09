package poo.recursion;

import poo.util.Stack;
import poo.util.StackConcatenato;

public class TorriDiHanoi{
    // un tipo enumerato ha il toString()
    enum Paletto{ SX, CL, DX }
    private int N;
    private int numeroMossa;

    /* Sappiamo risolvere il problema di spostare un disco
     *  dal paletto sorg al paletto dest, allora spostiamo gli N-1
     *  da sorg-->aus e il piú grande da sorg-->dest
     *  Non ci resta che applicare la stessa tecnica agli N-1 che
     *  avranno come paletto sorg=aus; dest=dest e come paletto
     *  ausiliario quello che era sorgente all'inizio.*/

    public void muovi(int N, Paletto sorg, Paletto aus, Paletto dest){
        if(N==1) sposta1Disco(sorg, dest);
        else{
            muovi(N-1, sorg, dest, aus);
            sposta1Disco(sorg, dest);  // sto spostando il piú grande
            muovi(N-1, aus, sorg, dest);
        }
    }// muovi

    public void muoviIte(int N, Paletto sorg, Paletto aus, Paletto dest){
        /* versione iterativa */
        class AreaDati{
            int N;
            Paletto sorg, aus, dest;

            public AreaDati(int N, Paletto sorg, Paletto aus, Paletto dest){
                this.N = N;
                this.sorg = sorg; this.aus = aus; this.dest = dest;
            }
        }//AreaDati
        Stack<AreaDati> pila = new StackConcatenato<>();
        /* Simula 1a invocazione */
        pila.push( new AreaDati(N, sorg, aus, dest));
        while(! pila.isEmpty()){
            AreaDati ad = pila.pop();
            if(ad.N == 1) sposta1Disco(ad.sorg, ad.dest);
            else{
                pila.push(new AreaDati(ad.N-1, ad.aus, ad.sorg, ad.dest));
                pila.push(new AreaDati(1, ad.sorg, ad.aus, ad.dest));
                pila.push(new AreaDati(ad.N-1, ad.sorg, ad.dest, ad.aus));
            }
        }
    }

    private void sposta1Disco(Paletto da, Paletto a){
        numeroMossa++;
        System.out.println(numeroMossa+") Sposta un disco dal paletto "+da+" al paletto "+a);
    }

    public static void main(String[] args) {
        new TorriDiHanoi().muoviIte(3,
                TorriDiHanoi.Paletto.SX,
                TorriDiHanoi.Paletto.CL,
                TorriDiHanoi.Paletto.DX);

        System.out.println("ricorsivo");
        new TorriDiHanoi().muovi(3,
                TorriDiHanoi.Paletto.SX,
                TorriDiHanoi.Paletto.CL,
                TorriDiHanoi.Paletto.DX);
    }
}//TorreDiHanoi
