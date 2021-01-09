package poo.appelli.appello02_03_18;

public class Persona implements Comparable<Persona>{
    private String nome;
    private String telefono;

    public Persona(String n, String t){
        nome = n;
        telefono = t;
    }

    public String getNome(){ return nome;}

    public String getTelefono() { return telefono; }

    public String toString() {
        return "<"+nome+", "+telefono+">";
    }

    public int hashCode(){
        return nome.hashCode() + 43*telefono.hashCode();
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(! (o instanceof Persona )) return false;
        Persona p = (Persona) o;
        return nome.equals(p.nome) &&
                telefono.equals(p.telefono);
    }//equals

    public int compareTo(Persona p) {
        return nome.compareTo(p.nome);
    }
}