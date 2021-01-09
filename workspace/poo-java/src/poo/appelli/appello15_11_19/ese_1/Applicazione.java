package poo.appelli.appello15_11_19.ese_1;

import java.util.Scanner;

public class Applicazione {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserire un numero maggiore o uguale a 2 \n> ");
        final int N = sc.nextInt();
        LinkedSortedSet<Integer> crivello = new LinkedSortedSet<>();
        for(int x = 2; x<=N; x++) crivello.add(x);
        for(int x=2; x<=Math.sqrt(N); x++){
            if(crivello.belongs(x)){
                int mx = x+x;
                while(mx<=N){
                    crivello.remove(mx);
                    mx += x;
                }
            }
        }//for
        System.out.println(crivello);
        StringBuilder sb = new StringBuilder(500);
        sb.append("Numeri primi tra 2 e "+ N+ "\n");
        int c = 1;
        for(Integer n: crivello){
            sb.append(String.format("%10d", n));
            if(c%6==0) sb.append("\n");
            c++;
        }
        System.out.println(sb.toString());
        sc.close();
    }
}
