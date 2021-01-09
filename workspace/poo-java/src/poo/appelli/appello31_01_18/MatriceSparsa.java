package poo.appelli.appello31_01_18;

import java.util.NoSuchElementException;

public interface MatriceSparsa {
    int getN();
    void clear();

    default double grado(){
        int n = getN();
        int nElementi = 0;
        for(int i=0; i<n; i++)
            nElementi += sizeRiga(i);
        return ((double) nElementi)/ n*n;
    }

    default int get(int i, int j){
        int n = getN();
        if( i<0 || j<0 || i>=n || j>=n) throw new IndexOutOfBoundsException();
        if(rigaVuota(i)) throw new NoSuchElementException();
        for( Elemento e: riga(i)){
            if(e.getColonna() > j) break;
            if(e.getColonna()==j) return e.getValore();
        }
        throw new NoSuchElementException();
    }
    default int get(Elemento e){
        return get(e.getRiga(), e.getColonna());
    }

    void set(int i, int j, int v);
    void set(Elemento e);

    default boolean rigaVuota(int i){
        return riga(i) == null;
    }
    default boolean colonnaVuota(int j){
        return colonna(j) == null;
    }
    default int sizeRiga(int i){
        int n = getN();
        if(i<0 || i>=n) throw new IndexOutOfBoundsException();
        if(riga(i) == null) return 0;
        int c = 0;
        for(Elemento e: riga(i)) c++;
        return c;
    }
    default int sizeColonna(int j){
        int n = getN();
        if(j<0 || j>=n) throw new IndexOutOfBoundsException();
        if(colonna(j) == null) return 0;
        int c = 0;
        for(Elemento e: colonna(j)) c++;
        return c;
    }

    MatriceSparsa crea();
    Iterable<Elemento> riga(int i);
    Iterable<Elemento> colonna(int j);

    default MatriceSparsa add(MatriceSparsa m){
        if(getN() != m.getN()) throw new IllegalArgumentException();
        MatriceSparsa somma = crea();
        int n = getN();
        for(int i=0; i<n; i++){
            if(! rigaVuota(i)){
                for(Elemento e1: riga(i)){
                    int v = e1.getValore();
                    try{ v+= m.get(e1); }
                    catch (Exception e){}
                    somma.set(i, e1.getColonna(), v);
                }
            }
            if(! m.rigaVuota(i)){
                for(Elemento e2: m.riga(i)){
                    int v = e2.getValore();
                    try{ v+= get(e2);}
                    catch (Exception e){}
                    somma.set(i, e2.getColonna(), v);
                }
            }
        }
        return somma;
    }

    default MatriceSparsa mul(MatriceSparsa m){
        if(getN() != m.getN()) throw new IllegalArgumentException();
        MatriceSparsa mul = crea();
        int n = getN();
        for(int i=0; i<n; i++){
            if(! rigaVuota(i)){
                for(Elemento e1: riga(i)){
                    int v = e1.getValore();
                    try{ v*= m.get(e1); }
                    catch (Exception e){}
                    mul.set(i, e1.getColonna(), v);
                }
            }
            if(! m.rigaVuota(i)){
                for(Elemento e2: m.riga(i)){
                    int v = e2.getValore();
                    try{ v*= get(e2);}
                    catch (Exception e){}
                    mul.set(i, e2.getColonna(), v);
                }
            }
        }
        return mul;
    }

    default boolean simmetrica(){
        int n = getN();
        for(int i = 0; i<n; i++){
            if(rigaVuota(i)) continue;
            for(Elemento e: riga(i)){
                boolean flag = false;
                if(e.getRiga()==e.getColonna()) continue;
                for(Elemento e2: colonna(i)){
                    if(e2.getRiga() > e.getColonna() ) break;
                    if(e2.getRiga() == e.getColonna() && e2.getValore()==e.getValore())
                        flag = true;
                }
                if(! flag ) return false;
            }
        }
        return true;
    }
}
