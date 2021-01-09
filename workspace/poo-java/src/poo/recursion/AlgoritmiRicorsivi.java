package poo.recursion;

import java.util.Arrays;

public class AlgoritmiRicorsivi {
    public static void permuta(int[] a){
        permuta(a, 0);
    }

    private static void permuta(int[] a, int i){
        // fino ad i rimane tutto ferma
        if(i == a.length)
            System.out.println(java.util.Arrays.toString(a));
        else{
            for(int j=i; j<a.length; j++){
                int park = a[i]; a[i]=a[j]; a[j]=park;
                permuta(a, i+1);
                park = a[i]; a[i]=a[j]; a[j]=park;
            }//for
        }//else
    }//permuta

    public static void scriviInverso(int n){
        if(n<0) throw new IllegalArgumentException();
        inverti(n);
        //Arrays.copyOf()
    }

    private static void inverti(int n){
        if(n == 0) return;
        System.out.print(n%10);
        scriviInverso(n/10);
    }

    public static boolean palindroma(String s){
        if(s.length()==1) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        return palindroma(s.substring(0, s.length()-1));

    }

    public static long potenza(int a, int n){
        if(n<0 || a == 0 && n==0) throw new IllegalArgumentException();
        return pot_2(a, n);
    }

    private static long pot_1(int a, int n){
        if(n==0) return 1L;
        if(n==1) return a;
        return a*pot_1(a,n-1);
    }

    private static long pot_2(int a, int n){
        if(n==0) return 1L;
        if(n==1) return a;
        if(n%2 == 0) return pot_2(a, n/2)*pot_2(a, n/2);
        return a*pot_2(a, n/2)*pot_2(a, n/2);
    }

    public static void main(String[] args) {
        int[] v = new int[3];
        v[0] = 1; v[1] = 2; v[2] = 3;
        //permuta(v);
        //scriviInverso(93123);
        System.out.println(potenza(2,31));
    }
}
