package poo.appelli.appello09_01_20;

import java.util.*;

//GIUSTO PORCODDDDDD
public class Schema{
    private Comparator<String> cmp = (s1, s2)->{
        if(s1.length() != s2.length()) return s1.length() - s2.length();
        return s1.compareTo(s2);
        };

    private final char[][] schema;

    private List<String> pO, pV; // create a tempo di costruzione

    public Schema(char[][] m){
        schema = new char[m.length][m[0].length];
        for(int i=0; i<schema.length; i++)
            for(int j=0; j<schema[0].length; j++)
                schema[i][j] = m[i][j];
        pO = paroleOrizzontali(); pV = paroleVerticali();
        }

    public int getNumeroRighe(){ return schema.length;}
    public int getNumeroColonne(){ return schema[0].length;}

    public boolean equals(Object o){
        if(o==this) return true;
        if(!(o instanceof Schema)) return false;
        Schema s = (Schema) o;
        if(s.getNumeroRighe() != getNumeroRighe() ||
                s.getNumeroColonne() != getNumeroColonne())
            return false;
        return s.paroleVerticali().equals(paroleVerticali()) &&
                    s.paroleOrizzontali().equals(paroleOrizzontali());
    }

//hashCode = hashCode*numero primo + arrayRiga
//toString --> toString di ogni riga - vai a capo se non é l'ultima riga

    public int hashCode(){
        int hc = 0;
        final int M = 43;
        for(int i =0; i<schema.length; i++)
            hc = hc*M + schema[i].hashCode();
        return hc;
    }

    public boolean contains(String parola){
        return pV.contains(parola) || pO.contains(parola);
    }

    public List<String> paroleVerticali(){
        if(pV != null) return pV;
        LinkedList<String> ret = new LinkedList<>();
        for(int j=0; j<schema[0].length; j++){
            String linea = "";
            for(int i=0; i<schema.length; i++){
                linea += schema[i][j];
            }//for_i
            if(linea.length()==0) continue; // non serve a un cazzo. che mi sono fumato?
            StringTokenizer st = new StringTokenizer(linea, " ");
            while(st.hasMoreTokens()) ret.add(st.nextToken());
        }//for_j
        Collections.sort(ret, cmp);
        return ret;
    }

    public List<String> paroleOrizzontali(){
        if(pO != null) return pO;
        LinkedList<String> ret = new LinkedList<>();
        for(int i=0; i<schema.length; i++){
            String linea = "";
            for(int j=0; j<schema[0].length; j++){
                linea += schema[i][j];
            }//for_j
            if(linea.length()==0) continue; // non serve a un cazzo. che mi sono fumato?
            StringTokenizer st = new StringTokenizer(linea, " ");
            while(st.hasMoreTokens()) ret.add(st.nextToken());
        }//for_i
        Collections.sort(ret, cmp);
        return ret;
    }

    public static void main(String[] args) {
        char[][] m = new char[5][5];
        char[][] a = new char[5][5];
        /*m[0][0] = ' '; m[0][1] = ' '; m[0][2] = ' '; m[0][3] = ' '; m[0][4] = ' ';
        m[1][0] = ' '; m[1][1] = 'S'; m[1][2] = 'E'; m[1][3] = 'I'; m[1][4] = ' ';
        m[2][0] = 'A'; m[2][1] = ' '; m[2][2] = 'U'; m[2][3] = 'V'; m[2][4] = ' ';
        m[3][0] = 'N'; m[3][1] = 'T'; m[3][2] = 'O'; m[3][3] = 'N'; m[3][4] = 'I';
        m[4][0] = 'T'; m[4][1] = ' '; m[4][2] = 'A'; m[4][3] = 'N'; m[4][4] = 'O';*/

        m[0][0] = ' '; m[0][1] = ' '; m[0][2] = ' '; m[0][3] = ' '; m[0][4] = ' ';
        m[1][0] = ' '; m[1][1] = 'S'; m[1][2] = ' '; m[1][3] = ' '; m[1][4] = ' ';
        m[2][0] = ' '; m[2][1] = ' '; m[2][2] = 'A'; m[2][3] = ' '; m[2][4] = ' ';
        m[3][0] = ' '; m[3][1] = 'T'; m[3][2] = ' '; m[3][3] = ' '; m[3][4] = ' ';
        m[4][0] = ' '; m[4][1] = ' '; m[4][2] = ' '; m[4][3] = ' '; m[4][4] = ' ';
        
        a[0][0] = ' '; a[0][1] = ' '; a[0][2] = ' '; a[0][3] = ' '; a[0][4] = ' ';
        a[1][0] = ' '; a[1][1] = ' '; a[1][2] = ' '; a[1][3] = ' '; a[1][4] = ' ';
        a[2][0] = ' '; a[2][1] = 'S'; a[2][2] = ' '; a[2][3] = ' '; a[2][4] = ' ';
        a[3][0] = ' '; a[3][1] = ' '; a[3][2] = 'A'; a[3][3] = ' '; a[3][4] = ' ';
        a[4][0] = ' '; a[4][1] = 'T'; a[4][2] = ' '; a[4][3] = ' '; a[4][4] = ' ';
        Schema s = new Schema(m);
        Schema s2 = new Schema(a);
        System.out.println(s.paroleOrizzontali());
        System.out.println(s.paroleVerticali());
        System.out.println(s.equals(s2));
    }
}//Schema
