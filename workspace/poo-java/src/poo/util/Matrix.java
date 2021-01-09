package poo.util;

public final class Matrix {
    private Matrix(){}

    public static double[][] trasposta(double[][] m){
        double[][] ret = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length ; j++)
                ret[j][i] = m[i][j];
        return ret;
    }

    public static boolean eSimmetrica(double[][] m){
        for (int i = 0; i < m.length; i++)
            if(m.length!=m[i].length)
                throw new RuntimeException("Matrice non quadrata");
        for(int i=0; i<m.length; i++)
            for(int j=0; j<i; j++)
                if(m[i][j]!=m[j][i])
                    return false;
        return true;
    }

    public static double[][] somma(double[][] m1, double[][]m2){
        if(m1.length!=m2.length || m1[0].length!=m2[0].length)
            throw new IllegalArgumentException();
        double[][] ret = new double[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j < m2[0].length ; j++)
                ret[i][j] = m1[i][j]+m2[i][j];
        return ret;
    }

    public static double[][] mul(double[][] m, double x){
        //moltiplica per uno scalare x
        double[][] ret = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length ; j++)
                ret[i][j] = m[i][j]*x;
         return ret;
    }

    public static double[][] prodotto(double[][] m1, double[][] m2){
        //prodotto tra matrici
        if(m1[0].length != m2.length)
            throw new IllegalArgumentException();
        double[][] ret = new double[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j <m2[0].length ; j++)
                for (int k = 0; k <m2.length ; k++)
                    ret[i][j]+=m1[i][k]*m2[k][j];
        return ret;
    }//prodotto

    public static double[] prodotto(double[][] m1, double[] v){
        //prodotto matrice-vettore
        if(m1[0].length != v.length)
            throw new IllegalArgumentException();
        double[] ret = new double[m1.length];
        for (int i = 0; i < m1.length; i++)
            for (int j = 0; j <v.length ; j++)
                    ret[i]+=m1[i][j]*v[j];
        return ret;
    }

    public static void scambiaRighe(double[][] m,  int ind1, int ind2){
        double[] tmp = m[ind1]; 
        m[ind1] = m[ind2]; m[ind2] = tmp; 
    }

    public static double[][] copiaMatrice(double[][] m){
        double[][] ret = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++){
            System.arraycopy(m[i], 0, ret[i], 0, m[0].length);
        }
        return ret;
    }

    public static double determinante(double[][] m){
        for (int i = 0; i < m.length; i++)
            if(m[i].length!=m.length) throw new RuntimeException("Determinante non definito per matrici non quadrate");
        int n = m.length;
        int contaScambi = 0;
        double[][] a = copiaMatrice(m);
        for (int j = 0; j < n; j++) {
            if(Mat.quasiUguali(a[j][j], 0D)){
                int p=j+1, pmax = p;
                for(;p<n;p++)
                    if(Math.abs(a[p][j]) > Math.abs(a[pmax][j])) pmax = p;
                if(pmax==n || Mat.quasiUguali(a[pmax][j], 0D)) return 0D;
                double[] tmp = a[j]; // swap
                a[j] = a[p]; a[p]=tmp;
                contaScambi++;
            }//if_j_j
            for (int i = j+1; i < n; i++) {
                if(!Mat.quasiUguali(a[i][j], 0D)){
                    double c= a[i][j]/a[j][j];
                    //comb lineare
                    for(int k=j; k<n; k++)
                        a[i][k] = a[i][k] - a[j][k]*c;
                }//if_i_j
            }//for_i
        }//for_j
        double d=1;
        for(int i=0; i<n; i++)
            d = d*a[i][i];
        if(contaScambi%2 != 0)
            d = d*(-1);
        return d;
    }//determinante

    public static double[][] riduzioneGauss(double[][] a){
        //check quadrata
        int n = a.length;
        for(int i=0; i<n;i++)
            if(a[i].length != n) throw new IllegalArgumentException();
        double[][] m = copiaMatrice(a);
        for(int p = 0; p<n; p++){
            int max = p;
            for(int i=p+1; i<n; i++){ // cerca riga max
                if(Math.abs(a[i][p]) > Math.abs(a[max][p])) max = i;
            }
            if(max!=p){//swap
                double[] tmp = m[max]; m[max] = m[p]; m[p] = tmp;

            }
            if(Mat.quasiUguali(m[p][p], 0D)) continue; //singolaritá
            for(int i = p+1; i<n; i++){//comb lineare
                double c = m[i][p]/m[p][p];
                for(int j = p; j<n; j++)
                    m[i][j] = m[i][j] - c*m[p][j];
            }
        }
        return m;
    }

    public static double[][] inversaGJ(double[][] a){
        //check quadrata
        int n = a.length;
        for(int i=0; i<n;i++)
            if(a[i].length != n) throw new IllegalArgumentException();
        double[][] b = new double[n][2*n];
        // crea matrice b = a | I ;
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                b[i][j] = a[i][j];
        for(int i= 0; i<n; i++) b[i][i+n] = 1;
        //riduci a scala
        for(int p =0; p<n; p++){
            int max = p;
            for(int i=p+1; i<n; i++){ // cerca riga max - meno errori di approssimazione
                if(Math.abs(a[i][p]) > Math.abs(a[max][p])) max = i;
            }
            if(Mat.quasiUguali(b[p][p], 0D)) throw new RuntimeException("Matrice non invertibile"); //singolaritá
            if(max!=p){//swap
                double[] tmp = b[max]; b[max] = b[p]; b[p] = tmp;
            }
            for(int i = p+1; i<n; i++){//comb lineare
                double c = b[i][p]/b[p][p];
                for(int j = p; j<2*n; j++)
                    b[i][j] = b[i][j] - c*b[p][j];
            }

        }
        //rendi prima metá di b matrice unitaria
        for(int p =0; p<n; p++){
            double c = 1/b[p][p];
            for(int j=p; j<2*n; j++){//1 sulla diagonale
                b[p][j] *= c;
            }
            for(int i=p-1; i>=0; i--){//azzera elementi non sulla diagonale
                if(! Mat.quasiUguali(b[i][p], 0D)){
                    c = b[i][p];
                    for(int k=p+1; k<2*n; k++) b[i][k] -= c*b[p][k];
                }
            }
        }
        double[][] inv = new double[n][n];
        //estrai inversa
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                inv[i][j] = b[i][j+n];
        return inv;
    }

    private static double sviluppoLaplaceRiga(double[][] m, int r){
        //Algoritmo ricorsivo: sviluppo di laplace secondo la riga r-sima
        // la ricerca della riga con meno zeri non viene effett. per calcolare i det delle minori.
        if(m.length==2) return m[0][0]*m[1][1] - m[0][1]*m[1][0];// caso banale
        double det = 0;
        for(int i=0; i<m.length; i++){
            double compAlgebrico = m[r][i];
            if(compAlgebrico==0) continue;// si annullebbe l'addendo
            if(((r+i) & 1)==1)//and bit a bit con 1,se ==1 allora r+i é dispari
                compAlgebrico*=-1;
            det += compAlgebrico*sviluppoLaplaceRiga(minore(m, r, i),0);
        }
        return det;
    }

    public static double sviluppoLaplace(double[][] m){
        //sviluppo di laplace secondo la riga con piú zeri
        for (int i = 0; i < m.length; i++)//check matrice quadrata
            if(m[i].length!=m.length) throw new RuntimeException("Determinante non definito per matrici non quadrate");
        int r=0; int maxZeri = 0;
        for (int i = 0; i < m.length; i++){
            int zeriRigaIesima=0;
            for (int j = 0; j < m.length; j++)
                if(m[i][j]==0) zeriRigaIesima++;
            if(maxZeri<zeriRigaIesima) r=i; maxZeri = zeriRigaIesima;
        }
        //System.out.println(r);
        double[][] a = copiaMatrice(m);
        return sviluppoLaplaceRiga(a, r);
    }

    public static double[][] minore(double[][] a, int i, int j){
        //ritorna il minore che si ottiene da a rimuovendo la riga i e la colonna j
        int n = a.length;
        double[][] ret = new double[n-1][n-1];
        int rigaRet = 0;
        for(int r=0; r<n; r++){
            int colonnaRet=0;
            if(r==i) continue;
            for(int c=0; c<n; c++){
                if(c==j) continue;
                ret[rigaRet][colonnaRet] = a[r][c];
                colonnaRet++;
            }//for_c
            rigaRet++;
        }//for_r
        return ret;
    }

    public static double determinanteL(double[][] a){
        for(int i=0; i<a.length; i++)
            if(a.length!=a[i].length) throw new IllegalArgumentException();
        return determinanteL(a, 0);
    }

    private static double determinanteL(double[][] a, int r){
        if(a.length == 2)
            return a[0][0]*a[1][1] - a[0][1]*a[1][0];
        double det = 0D;
        for (int j = 0; j<a[0].length; j++){
            int sgn = ((r+j)%2 == 0)? 1 : -1;
            det += sgn*a[r][j]*determinanteL(minore(a,r,j), r);
        }
        return det;
    }


    public static void main(String[] args){
        /*double[][] test = new double[3][3];
        Random n = new Random();
        for (int i = 0; i < test.length; i++)
            for (int j = 0; j < test.length; j++){
                if(i==2 && (j!=3))
                    test[i][j] = 0;
                else
                    test[i][j] = n.nextInt();
            }*/
        //IO.printlnMatrix(test);
        //double[][] test2 = copiaMatrice(test);
        //IO.printlnMatrix(test2);
        //scambiaRighe(test2, 0, 1);
        //System.out.println();
        //IO.printlnMatrix(test2);
        //IO.println(eSimmetrica(test));
        //final long startTimeL = System.nanoTime();
        //System.out.println(sviluppoLaplace(test));
        //final long endTimeL   = System.nanoTime();
        //System.out.println("Total execution time: (LAPLACE) " + (endTimeL - startTimeL));*/

        //final long startTime = System.nanoTime();
        //System.out.println(determinante(test));
        //final long endTime = System.nanoTime();
        //System.out.println("Total execution time: " + (endTime - startTime));
        double[][] test = new double[3][3];
        test[0][0] = 0; test[0][1] = -1; test[0][2] = 2;
        test[1][0] = 1; test[1][1] = 0; test[1][2] = -1;
        test[2][0] = 0; test[2][1] = 1; test[2][2] = 0;
        System.out.println("Matrice");
        IO.printlnMatrix(test);
        System.out.println("Matrice ridotta a scala");
        IO.printlnMatrix(riduzioneGauss(test));
        System.out.println("Matrice inversa GJ");
        double[][] invTest = inversaGJ(test);
        IO.printlnMatrix(invTest);
        System.out.println("Matrice identitá");
        IO.printlnMatrix(prodotto(test, invTest));

    }
}
