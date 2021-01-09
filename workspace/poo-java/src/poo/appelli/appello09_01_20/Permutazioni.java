package poo.appelli.appello09_01_20;

public class Permutazioni {
    private int[] a, b;

    public Permutazioni(int[] a){
        // si assume che gli elementi siano distinti
        this.a = new int[a.length];
        for(int i = 0; i<a.length; i++)
            this.a[i] = a[i];
        this.b = new int[a.length];
    }

    private void scriviSoluzione(){
        System.out.println(java.util.Arrays.toString(b));
    }

    public void risolvi(){
        permuta(0);
    }

    private void permuta(int ps){
        if(ps==a.length) scriviSoluzione();
        for(int s=0; s<a.length; s++){
            if(assegnabile(s,ps)){
                assegna(s,ps);
                permuta(ps+1);
                deassegna(s,ps);
            }
        }
    }

    private boolean assegnabile(int s, int ps){
        for(int i = 0; i<ps; i++)
            if(b[i] == a[s]) return false;
        return true;
    }

    private void assegna(int s, int ps){
        b[ps] = a[s];
    }

    private void deassegna(int s, int ps){}

    public static void main(String[] args) {
        int[] a = {1,2,3};
        Permutazioni p = new Permutazioni(a);
        p.risolvi();
    }
}
