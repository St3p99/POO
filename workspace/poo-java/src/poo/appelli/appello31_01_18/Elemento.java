package poo.appelli.appello31_01_18;

public class Elemento implements Comparable<Elemento>{
    private int riga;
    private int colonna;
    private int valore;

    public Elemento(int i, int j, int v){
        riga = i;
        colonna = j;
        valore = v;
    }

    public Elemento(Elemento e){
        riga = e.riga;
        colonna = e.colonna;
        valore = e.valore;
    }

    public void setValore(int v){
        valore = v;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public int getValore() {
        return valore;
    }

    public int hashCode(){
        final int M = 43;
        int hc = riga*M + colonna;
        hc = hc*M + valore;
        return hc;
    }

    public boolean equals(Object o){
        if( o==this ) return true;
        if( !( o instanceof Elemento)) return false;
        Elemento e = (Elemento) o;
        return riga == e.riga && colonna == e.colonna;
    }

    public String toString() {
        return "<" + riga +
                ", " + colonna +
                ", " + valore +  '>';
    }

    public int compareTo(Elemento e) {
        if (riga == e.riga && colonna != e.colonna)
            return colonna - e.colonna;
        if (colonna == e.colonna && riga != e.riga)
            return riga - e.riga;
        return valore - valore;
        // per come verra usato l'oggetto elemento non avremo mai in una linkedList oggetti Elemento con
        // sia riga che colonna diversi
    }
}

