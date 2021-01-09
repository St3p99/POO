package poo.sistema;

public abstract class Sistema{
    private int n;

    public Sistema(double[][] a, double[] y){
        if(y.length != a.length) throw new RuntimeException("Sistema malformato");
        for (int i = 0; i < a.length; i++)
            if(a[i].length != a.length) throw new RuntimeException("Sistema malformato");
        n = a.length;
    }

    public int getN(){ return n;}

    public abstract double[] risolvi();
}