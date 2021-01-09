package proj_polinomi.polinomi;

import java.util.StringTokenizer;

public class ValutaPolinomio {
    private static String SGN = "[\\+\\-]";
    private static String UNSIGNED_INT = "\\d+";
    private static String VARIABILE = "(x|x\\^" + UNSIGNED_INT + ")";
    static String MONOMIO_NO_SGN = "(" + UNSIGNED_INT + "|" + VARIABILE + "|" + UNSIGNED_INT + VARIABILE + ")";
    static String POLINOMIO = SGN + "?" + MONOMIO_NO_SGN + "(" + SGN + MONOMIO_NO_SGN + ")*";

    static Polinomio interpretaPolinomio(String linea){
        if (!linea.matches(POLINOMIO))
            throw new IllegalArgumentException("Stringa non interpretabile come polinomio");
        Polinomio p = new PolinomioMap();
        StringTokenizer st = new StringTokenizer(linea, "+-", true);
        while (st.hasMoreTokens()) {
            String tk = st.nextToken();
            switch (tk) {
                case "+": {
                    p.add(interpretaMonomio(st.nextToken()));
                    break;
                }
                case "-": {
                    p.add(interpretaMonomio(st.nextToken()).mul(-1));
                    break;
                }
                default:
                    p.add(interpretaMonomio(tk));
            }//switch
        }//while
        return p;
    }//interpretaPolinomio

    private static Monomio interpretaMonomio(String linea){
        int coeff = 1, grado = 0;
        StringTokenizer st = new StringTokenizer(linea, "x^", true);
        int nToken = st.countTokens();
        switch (nToken) {
            case 1: {  // "coeff" oppure "x"
                String tk = st.nextToken();
                if (tk.equals("x")) grado = 1;
                else coeff = Integer.parseInt(tk);
                break;
            }//1
            case 2: {  // "coeff_x"
                coeff = Integer.parseInt(st.nextToken());
                grado = 1;
                break;
            }//2
            case 3: {  // "x^grado"
                st.nextToken(); st.nextToken(); //salta x e ^
                grado = Integer.parseInt(st.nextToken());
                break;
            }//2
            default: {  // monomio completo
                coeff = Integer.parseInt(st.nextToken());
                st.nextToken(); st.nextToken();  // salta x e ^
                grado = Integer.parseInt(st.nextToken());
            }//4
        }//switch
        return new Monomio(coeff, grado);
    }//interpretaMonomio
}//ValutaPolinomio
