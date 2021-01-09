package poo.esempi;

public class TestEtichetta{
    public static void main(String[] args) {
        double[] x = {1,2,3,4,5,6,7,8};
        aLoopName: for (int i=0;i<x.length;i*=10)
            while (i<5){
                if (x[i]==2)
                    continue aLoopName;
                System.out.println(x[i]);
                i++;
            }
    }
}