package poo.polinomi;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Applicazione {
    // legge da tastiera due polinomi

    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        Polinomio p1, p2;
        for(;;){
            System.out.print("Scrivi il primo polinomio\nP1(x) = ");
            String linea1 = sc.nextLine();
            if(! linea1.matches(ValutaPolinomio.POLINOMIO)){
                System.out.println("Amico mio, non matcha"); continue;
            }
            p1 = ValutaPolinomio.interpretaPolinomio(linea1);
            break;
        }//estrai_p1
        for(;;){
            System.out.print("Scrivi il secondo polinomio\nP2(x) = ");
            String linea2 = sc.nextLine();
            if(! linea2.matches(ValutaPolinomio.POLINOMIO)){
                System.out.println("Amico mio, non matcha"); continue;
            }
            p2 = ValutaPolinomio.interpretaPolinomio(linea2);
            break;
        }//estrai_p2
        System.out.println("P1(x) = " + p1);
        System.out.println("P2(x) = " + p2);
        System.out.println("P1*P2(x) = " + p1.mul(p2));
        System.out.println("P1+P2(x) = " + p1.add(p2));
        System.out.println("P1-P2(x) = " + p1.add(p2.mul(new Monomio(-1,0))));
        System.out.println("P1'(x) = " + p1.derivata());
        System.out.println("P2'(x) = " + p2.derivata());
    }
}

/*
    private static Polinomio interpretaPolinomio(String linea){
        Polinomio ret = new PolinomioAL();
        StringTokenizer st = new StringTokenizer(linea, "+-", true);
        while(st.hasMoreTokens()){
            String tk = st.nextToken();
            switch (tk){
                case "+":{ ret.add(interpretaMonomio(st.nextToken())); break;}
                case "-":{ ret.add(interpretaMonomio(st.nextToken()).mul(-1)); break;}
                default: ret.add(interpretaMonomio(tk));
            }
        }
        return ret;
    }

    private static Monomio interpretaMonomio(String linea){
        int coeff = 1, grado = 0;
        StringTokenizer st = new StringTokenizer(linea, "x^", true);
        int nToken = st.countTokens();
        switch (nToken) {
            case 1: { // solo coeff || solo "x"
                String tk = st.nextToken();
                if (tk.equals("x")) grado = 1;
                else coeff = Integer.parseInt(tk);
                break;
            }//1
            case 2: { // "coeff_x"
                coeff = Integer.parseInt(st.nextToken());
                grado = 1; break;
            }//2
            case 3: {// x^grado
                st.nextToken(); st.nextToken(); //salta x e ^
                grado = Integer.parseInt(st.nextToken()); break;
            } //2
            default: { //monomio completo
                coeff = Integer.parseInt(st.nextToken());
                st.nextToken(); st.nextToken(); //salta x e ^
                grado = Integer.parseInt(st.nextToken());
            } //4
        }//switch
        return new Monomio(coeff, grado);
    }


     private static Monomio interpretaMonomio2(String linea){
        int coeff =1, grado =0;
        StringTokenizer st = new StringTokenizer(linea, "x^", true);
        String MONOMIO_PRIMO_GRADO = "("+UNSIGNED_INT+")?x";
        String MONOMIO_NO_COEFF = "x\\^"+UNSIGNED_INT+"";
        if(linea.matches(UNSIGNED_INT)) // solo coefficiente
            coeff= Integer.parseInt(linea);
        else if(linea.matches(MONOMIO_PRIMO_GRADO)){ // monomio di primo grado
            String tk = st.nextToken();
            if(! tk.equals("x"))
                coeff = Integer.parseInt(tk);
            grado = 1;
        }
        else if(linea.matches(MONOMIO_NO_COEFF)){
            st.nextToken(); st.nextToken(); //salta x e ^
            grado = Integer.parseInt(st.nextToken());
        }
        else if(linea.matches(MONOMIO)){ // monomio completo
            coeff = Integer.parseInt(st.nextToken());
            st.nextToken(); st.nextToken(); //salta x e ^
            grado = Integer.parseInt(st.nextToken());
        }
        return new Monomio(coeff, grado);
    }*/

