package poo.esempi;

public class IntPerRiferimento{
    public static void swap(Integer x, Integer y){
        /* Non funziona */
        Integer park = x;
        x = y;
        y = park;
    }

    public static void swapLikeC(int[] x, int[] y){
        if(x.length>1 || y.length >1) throw new IllegalArgumentException();
        int z = x[0];
        x[0]=y[0];
        y[0]=z;
    }
    public static void main(String[] args){
        int[] x = new int[1]; int[] y = new int[1];
        x[0] = 5; y[0] = -4;
        swapLikeC(x,y);
        System.out.println("Metodo 'Array':");
        System.out.println(x[0]);
        System.out.println(y[0]);
        System.out.println();

        Integer a = 5, b = -4;
        System.out.println("Metodo 'Integer':");
        swap(a,b);
        System.out.println(a);
        System.out.println(b);
        System.out.println();
    }
}
