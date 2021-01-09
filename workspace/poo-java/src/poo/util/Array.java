package poo.util;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

@SuppressWarnings({"rawtypes","unchecked"})
public class Array{
    //RICERCA LINEARE
    public static int ricercaLineare(int[] v, int x){
        for (int i = 0; i <v.length ; i++)
            if(v[i]==x) return i;
        return -1;
    }

    public static int ricercaLineare(double[] v, double x){
        for (int i = 0; i <v.length ; i++)
            if(Mat.quasiUguali(v[i],x)) return i;
        return -1;
    }

    public static <T> int ricercaLineare(T[] v, T x){
        for (int i = 0; i <v.length ; i++)
            if(v[i].equals(x)) return i;
        return -1;
    }

    public static <T extends Comparable<? super T>> T max(T[] a){
        return max(a, 0, a.length-1);
    }

    private static <T extends Comparable<? super T>> T max(T[] a, int inf, int sup){
        if(inf == sup) return a[inf]; // caso banale: lista con un solo elemento
        int med = inf+sup/2;
        T m1 = max(a, inf, med);
        T m2 = max(a, med+1, sup);
        if(m1.compareTo(m2)>0) return m1;
        return m2;
    }

    //RICERCA BINARIA (Hyp: giá ordinato in senso crescente)
    public static int ricercaBinaria(int[] v, int x){
        return ricercaBinaria(v, x, 0, v.length-1);
    }

    private static int ricercaBinaria(int[] v, int x, int start, int stop){
        int centro=(stop+start)/2;
        if(stop<start) return -1;
        if(x==v[centro]) return centro;
        if(x>v[centro]) return ricercaBinaria(v,x,centro+1,stop);
        else return ricercaBinaria(v,x,start,centro-1);
    }
    
    public static <T extends Comparable<? super T>> int ricercaBinaria(T[] v, T x){
        return ricercaBinaria(v, x, 0, v.length-1);
    }
    
    private static <T extends Comparable<? super T>> int ricercaBinaria(T[] v, T x, int start, int stop){
        int centro=(stop+start)/2;
        if(stop-start<0) return -1;
        if(x.equals(v[centro])) return centro;
        if(x.compareTo(v[centro])>0) 
            return ricercaBinaria(v,x,centro+1,stop);
        else return ricercaBinaria(v,x,start,centro-1);
    }

    public static <T extends Comparable<? super T>> int ricercaBinaria(Vector<T> v, T x){
        return ricercaBinaria(v, x, 0, v.size()-1);
    }

    private static <T extends Comparable<? super T>> int ricercaBinaria(Vector<T> v, T x, int start, int stop){
        int centro=(stop+start)/2;
        if(stop-start<0) return -1;
        if(x.equals(v.get(centro))) return centro;
        if(x.compareTo(v.get(centro))>0) 
            return ricercaBinaria(v,x,centro+1,stop);
        else return ricercaBinaria(v,x,start,centro-1);
    }

    //ALG. DI ORDINAMENTO
    public static void bubbleSort(int[] v){
        int finoA=v.length-1; boolean scambi=true;
        while(scambi){
            scambi = false; int fine=finoA;
            for (int j=1; j<=fine; j++)
                if(v[j-1]>v[j]){
                    swap(v,j-1,j);
                    finoA=j;
                }//if
        }//while
    }//bubbleSort

    public static <T extends Comparable<? super T>> void bubbleSort(Vector<T> v){
        boolean scambi = true; int ius = v.size()-1;
        while(scambi){
            scambi=false; int limite=ius;
            for(int i=1; i<=limite; i++)
                if(v.get(i).compareTo(v.get(i-1))<0) {
                    T park = v.get(i); v.set(i,v.get(i-1)); v.set(i-1, park);
                    scambi = true;
                }//if
        }//while
    }//bubbleSort

    public static <T> void bubbleSort(Vector<T> v, Comparator<T> c){
        boolean scambi = true; int ius = v.size()-1;
        while(scambi){
            scambi=false; int limite=ius;
            for(int i=1; i<=limite; i++)
                if(c.compare(v.get(i-1),v.get(i))<0) {
                    T park = v.get(i); v.set(i,v.get(i-1)); v.set(i-1, park);
                    scambi = true;
                }//if
        }//while
    }

    public static <T extends Comparable<? super T>> void bubbleSort(LinkedList<T> l){
        //deve conseguire l'ordinamento a bolle usando esclusivamente listIterator di l
        if(l.size()<=1) return;
        boolean scambi = true;
        int ius = l.size()-1;  // indice ultimo scambio
        T bubble, n;
        while(scambi){
            ListIterator<T> lit = l.listIterator();
            int i=1, limite = ius;
            scambi = false;
            bubble = lit.next();
            while(i<=limite){
                n = lit.next();
                if(bubble.compareTo(n) > 0 ){
                    lit.set(bubble);
                    lit.previous(); lit.previous(); lit.set(n);
                    lit.next(); lit.next();
                    scambi = true; ius=i-1;
                }
                else bubble=n;  //  se (n >= bubble) la "bolla" da far "salire" sará n.
                i++;
            }//while_2
        }//while_1
    }//bubbleSort (ListIterator)

    public static void selectionSort(int[] v){
        for (int i = 0; i <v.length-1 ; i++) {
            int imin=i;
            for (int j=i+1; j<v.length; j++)
                if(v[j]<v[imin])
                    imin=j;
            swap(v, i, imin);
        }
    }//selectionSort

    public static void selectionSort(double[] v){
        for (int i = 0; i <v.length-1 ; i++) {
            int imin=i;
            for (int j=i+1; j<v.length; j++)
                if(v[j]<v[imin])
                    imin=j;
            swap(v, i, imin);
        }
    }//selectionSort

    public static void selectionSort(Comparable[] v){
        for (int i = 0; i <v.length-1 ; i++) {
            int imin=i;
            for (int j=i+1; j<v.length; j++)
                if(v[j].compareTo(v[imin])<0)
                    imin=j;
            swap(v, i, imin);
        }
    }//selectionSort applied to Comparable object


    public static void insertionSort(int[] v){
        for (int i = 1; i <v.length ; i++) {
            int val = v[i];
            for (int j = i-1; j >-1; j--)
                if(val<v[j])
                    swap(v,j+1,j);
        }
    }//insertionSort

    public static void insertionSort(Object[] v, Comparator c){
        for (int i = 0; i <v.length ; i++) {
            Object x = v[i];
            int j = 1;
            while(j>0 && c.compare(v[i-1], x)>0){
                v[j]=v[j-1]; 
                j--;
            }
            v[j] = x;
        }//for_i
    }//insertionSort

    private static void swap(int[] v, int i, int j){
        if(i==j) return;
        int z = v[j];
        v[j] = v[i];
        v[i]=z;
    }//swap
    
    private static void swap(double[] v, int i, int j){
        if(i==j) return;
        double z = v[j];
        v[j] = v[i];
        v[i]=z;
    }//swap

    private static void swap(Comparable[] v, int i, int j){
        if(i==j) return;
        Comparable z = v[j];
        v[j] = v[i];
        v[i]=z;
    }//swap

    //OPERAZIONI SU VETTORI
    public static double[] prodottoScalare(double[] v1, double[] v2){
        if(v1.length != v2.length)
            throw new IllegalArgumentException();
        double[] ret = new double[v1.length];
        for (int i = 0; i <v1.length ; i++)
            ret[i]=v1[i]*v2[i];
        return ret;
    }

    public static void mergeSortIte(int[] a){
        //merge di array (a due a due)
        // individuati da [i, i+passo-1] e [i+passo, i+passo*2-1]
        // finché (i+passo) < a.length bisogna continuare perché
        // il secondo sotto-array ha almeno un elemento
        int passo = 1;
        while(passo < a.length){
            for(int i=0; (i+passo)<a.length; i+=passo*2)
                merge(a, i, passo);
            passo*=2;
        }
    }

    public static void merge(int[] a, int da, int passo){
        //Esegue il merge di due sotto-array di a individuati come segue:
        // 1: [i, i+passo-1] e 2: [i+passo, i+passo*2-1]
        // Il secondo sotto-array potrebbe non esistere,
        // o essere di lunghezza minore di passo
        System.out.println("a: "+java.util.Arrays.toString(a));
        System.out.println("da: "+da+" passo: "+passo);
        int[] a1 = new int[passo];
        int[] a2 = new int[passo]; // al piú lunghezza==passo
        int sizeA2 = -1; // lunghezza effettiva di a2
        boolean flag=false;
        for(int j=da, i=0; j<da+passo; j++, i++) a1[i] = a[j]; //riempi a1
        for(int j=da+passo, i=0; j<da+passo*2; j++, i++){//riempi a2
            if(j==a.length) break;
            a2[i] = a[j];
            sizeA2 = i+1;
        }
        System.out.println("a1: "+java.util.Arrays.toString(a1));
        System.out.println("a2: "+java.util.Arrays.toString(a2));
        if(sizeA2==-1) return;
        //merge di 'a1' e 'a2' in 'a' - a partire da 'da'
        int i=0, j=0, pos = da;
        while(i<a1.length && j<sizeA2){
            if(a1[i]<=a2[j]){
                a[pos] = a1[i]; i++;
            }
            else{
                a[pos] = a2[j]; j++;
            }
            pos++;
        }
        while(i<a1.length){
            a[pos] = a1[i];
            i++; pos++;
        }
        while(j<sizeA2){
            a[pos] = a2[j];
            j++; pos++;
        }
    }


    public static void main(String[] args){
        int[] v1 = {3,1,5,1,7,2,7,7,8};
        mergeSortIte(v1);
        System.out.println(java.util.Arrays.toString(v1));
        /*int[] v2 = {3,1,5,1,5,1,5,6,12,1,6,0};
        int[] v3 = {1,2,4,3,5,6,7,8};
        bubbleSort(v3);
        IO.printlnArray(v3);
        selectionSort(v2);
        IO.printlnArray(v2);
        insertionSort(v1);
        IO.printlnArray(v1);
        Punto p1= new Punto();
        Punto p2= new Punto(3,5);
        Disco d1= new Disco(p2,6);
        Data data1 = new Data();
        Data data2 = new Data(5,7,2018);
        Data data3 = new Data();
        Object[] o = {p1,p2,d1,data1,data2};
        System.out.println(o[ricercaLineare(o,data3)]);
        System.out.println(ricercaBinaria(v2, 1));
        System.out.println(ricercaBinaria(v3, 5));
        IO.printlnArray(o);

        LinkedList<Data> l= new LinkedList<>();
        l.add(new Data());
        l.add(new Data(1,3,2010));
        l.add(new Data(1,3,1999));
        l.add(new Data(1,8,2009));
        System.out.println("ORIGINAL: "+l);
        bubbleSort(l);
        System.out.println("SORTED: "+l);
        */

        /*LinkedList<Monomio> p1= new LinkedList<>();
        p1.add(new Monomio(5,3));
        p1.add(new Monomio(-2,1));
        p1.add(new Monomio(7,0));
        p1.add(new Monomio(-12,0));
        p1.add(new Monomio(2,4));
        p1.add(new Monomio(3,3));
        p1.add(new Monomio(-4,2));
        p1.add(new Monomio(-3,5));
        System.out.println("ORIGINAL: "+p1);
        bubbleSort(p1);
        System.out.println("SORTED: "+p1);

        LinkedList<Integer> l2 = new LinkedList<>();
        for(int i =0; i< v1.length; i++) l2.add(v1[i]);
        System.out.println("ORIGINAL: "+l2);
        bubbleSort(l2);
        System.out.println("SORTED: "+l2);

        Polinomio pol = new PolinomioSet();
        for(Monomio m: p1) pol.add(m);
        System.out.println(pol);
        Scanner sc = new Scanner(System.in);
        System.out.println("inserisci un intero maggiore o uguale a 2");
        final int N = sc.nextInt();*/


    }
}
