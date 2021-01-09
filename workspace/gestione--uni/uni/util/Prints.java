package util;

import java.util.*;

public class Prints {
    //MATRICI

    public static void print(int[][] m){
        for (int[] ints : m) {
            print(ints);
        }
        }//print


    public static void print(boolean[][] m){
        for (boolean[] booleans : m) {
            print(booleans);
        }
    }//print


    public static void print(float[][] m){
        for (float[] floats : m) {
            print(floats);
        }
    }//print


    public static void print(double[][] m){
        for (double[] doubles : m) {
            print(doubles);
        }
    }//print


    public static void print(long[][] m){
        for (long[] longs : m) {
            print(longs);
        }
    }//print


    public static void print(char[][] m){
        for (char[] chars : m) {
            print(chars);
        }
    }//print


    public static void print(String[][] m){
        for (String[] strings : m) {
            print(strings);
        }
    }//print


    public static void print(short[][] m){
        for (short[] shorts : m) {
            print(shorts);
        }
    }//print

    public static void print(Object[][] m){
        for (Object[] obj : m) {
            print(obj);
        }
    }//print



    //ARRAY
    public static void print(int[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++)
            if (i == n - 1)
                s+=v[i];
            else
                s = s + v[i] + ", ";
        s += "]";
        IO.println(s);
    }//print


    public static void print(boolean[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print


    public static void print(float[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print


    public static void print(double[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print


    public static void print(long[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print


    public static void print(char[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print


    public static void print(String[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++)
            if (i == n - 1)
                s+=v[i];
            else
                s+=v[i] + ", ";

        s += "]";
        IO.println(s);
    }//print


    public static void print(short[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print

    public static void print(Object[] v){
        int n = v.length;
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v[i];
            }
            else{
                s = s + v[i] + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print

    public static void print(ArrayList v){
        int n = v.size();
        String s = "[";
        for (int i=0; i<n; i++) {
            if (i == n - 1){
                s+=v.get(i);
            }
            else{
                s = s + v.get(i) + ", ";
            }
        }
        s += "]";
        IO.println(s);
    }//print

}//Prints
