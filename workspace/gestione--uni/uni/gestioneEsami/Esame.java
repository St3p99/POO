package gestioneEsami;


public class Esame {
    private String nome;
    private int cfu;

    public String getNome() {
        return nome;
    }

    public int getCfu() {
        return cfu;
    }


    public Esame(String nome, int cfu){
        this.nome = nome;
        this.cfu = cfu;
    }


    public Esame(){
        return;
    }


    public String toString(){
        return ""+nome+", CFU:"+cfu;
    }

    public boolean equals(Object o){
        if (o==null)
            return false;
        if (o==this)
            return true;
        if (!(o instanceof Esame))
            return false;
        Esame e = (Esame) o;
        return this.nome.equals(e.nome);
    }

    public int hashCode(){
        return this.nome.hashCode();
    }

}
