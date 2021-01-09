package poo.polinomi;

import java.util.StringTokenizer;

public class Monomio implements Comparable<Monomio>{
    private final int coeff, grado;

    public Monomio(int coeff, int grado) {
        if(grado<0)
            throw new RuntimeException("Grado negativo");
        this.coeff = coeff;
        this.grado = grado;
    }

    public Monomio(Monomio m){
        coeff = m.coeff; grado = m.grado;
    }

    public Monomio(String linea){
        String MONOMIO = "[\\+\\-]?"+ValutaPolinomio.MONOMIO_NO_SGN;
        if(! linea.matches(MONOMIO))
            throw new IllegalArgumentException("Stringa non interpretabile come monomio");
        int coeff = 1, grado = 0;
        boolean positivo  = true;
        StringTokenizer st = new StringTokenizer(linea, "+-x^", true);
        int nToken = st.countTokens();
        if(linea.charAt(0) == '-'){ positivo = false; st.nextToken(); nToken--;}
        if(linea.charAt(0) == '+'){ st.nextToken(); nToken--;}
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
            }//2
            default: { //monomio completo
                coeff = Integer.parseInt(st.nextToken());
                st.nextToken(); st.nextToken(); //salta x e ^
                grado = Integer.parseInt(st.nextToken());
            }//4
        }//switch
        coeff = (positivo)? coeff: coeff*(-1);
        this.coeff = coeff;
        this.grado = grado;
    }

    public int getCoeff() { return coeff; }

    public int getGrado() { return grado; }

    public Monomio add(Monomio m){
        if(!this.equals(m))
            throw new RuntimeException("Monomi non simili");
        return new Monomio(coeff+m.coeff, grado);
    }

    public Monomio mul(Monomio m){
        return new Monomio(coeff*m.coeff, grado+m.grado);
    }

    public Monomio mul(int s){
        return new Monomio(coeff*s, grado);
    }

    @Override
    public int compareTo(Monomio m){
        return m.grado - this.grado; //il monomio di grado maggiore é minore.
    }//compareTo

    @Override
    public boolean equals(Object o){
        if(! (o instanceof  Monomio)) return false;
        if(o == this) return true;
        Monomio m = (Monomio) o;
        return this.grado == m.grado; //equals significa SIMILITUDINE in questo caso
    }//equals

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if( coeff<0 ) sb.append('-');
        if( Math.abs(coeff) != 1 || grado==0)
            sb.append( Math.abs(coeff));
        if( coeff!=0 && grado>0 ) sb.append('x');
        if(coeff!=0 && grado>1) {
            sb.append('^'); sb.append(grado);
        }
        return sb.toString();
    }

    public int hashCode(){
        return grado;
    }
}//Monomio
