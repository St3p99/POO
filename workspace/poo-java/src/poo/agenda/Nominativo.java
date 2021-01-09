package poo.agenda;

public class Nominativo  implements Comparable<Nominativo>{
    private String cognome, nome, prefisso, telefono;

    public Nominativo(String cog, String nom, String pre, String tel) {
        cognome = cog;
        nome = nom;
        prefisso = pre;
        telefono = tel;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return cognome.toUpperCase() + " " + nome.toUpperCase() + " " + prefisso + "-" + telefono;
    }//toString

    @Override
    public boolean equals(Object x) {
        if (!(x instanceof Nominativo)) return false;
        if (x == this) return true;
        Nominativo n = (Nominativo) x;
        return this.cognome.equals(n.cognome) &&
                this.nome.equals(n.nome);
    }//equals

    @Override
    public int hashCode() {
        final int M = 83;
        int h = 0;
        h = h * M + cognome.hashCode();
        h = h * M + nome.hashCode();
        return h;
    }//hashCode


    @Override
    public int compareTo(Nominativo n) {
        if (cognome.compareTo(n.cognome) < 0 ||
                (cognome.equals(n.cognome) && nome.compareTo(n.nome) < 0)) return -1;
        if (this.equals(n)) return 0;
        return 1;
    }//compareTo

    public static void main(String... args) {
        Nominativo n = new Nominativo("Alessandro", "Mileto", "0344", "333123123");
        Nominativo x = new Nominativo("Alessandro", "Mileto", "", "");
        System.out.println(n);
        if (n.equals(x)) System.out.println(n + " equals " + x);
        else System.out.println("Nominativi non uguali");
        System.out.println(n.hashCode());
        Nominativo m = new Nominativo("Mario", "antonio", "031231", "31233");
        System.out.println(n.compareTo(m));
    }
}