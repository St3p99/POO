package poo.appelli.appelloStraordinario;

public class Parola implements Comparable<Parola> {
    String parola;

    public Parola(String p){
        parola = p;
    }

    public String get() { return parola; }

    public int length(){ return parola.length();}

    public String toString() { return parola; }

    public int hashCode(){ return parola.hashCode();}

    public boolean equals(Object o){
        if( o == this ) return true;
        if( ! (o instanceof Parola)) return false;
        Parola p = (Parola) o;
        return p.parola.equals(this.parola);
    }

    public int compareTo(Parola p){
        if(p.length() != this.length())
            return this.length() - p.length();
        return this.parola.compareTo(p.parola);
    }
}
