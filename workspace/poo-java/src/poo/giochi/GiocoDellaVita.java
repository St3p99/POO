package poo.giochi;

public class GiocoDellaVita{
    private char[][] mappa, nuovaMappa;
    private int n, m;
    
    public GiocoDellaVita(int n, int m){
        if(n<=0 || m<=0)
            throw new IllegalArgumentException("Dimensioni nulle o negative");
        this.n=n; this.m =m;
        mappa = new char[n][m]; 
        nuovaMappa = new char[n][m]; 
        for (int i = 0; i <n; i++)
            for (int j = 0; j <m; j++)
                mappa[i][j]='.';
    }

    public void aggiungiOrganismo(int i, int j){
        if(i<0 || i>=n || j<0 || j>=m)
            throw new IllegalArgumentException();
        mappa[i][j]='*';
    }

    public void configuraRandom(){
        for (int i = 0; i <n; i++)
            for (int j = 0; j <m; j++)
                if(Math.random()<0.5) mappa[i][j]='*';
                else mappa[i][j]='.';
    }//configuraRandom

    public void prossimaGenerazione(){
        for (int i = 0; i <n; i++)
            for (int j = 0; j <m; j++){
                int v = vicini(i,j);
                if(mappa[i][j]=='*')
                    nuovaMappa[i][j]= (v==2||v==3)?'*':'.'; //sopravvive se v ==2 o ==3- altrimenti muore
                else
                    nuovaMappa[i][j]= (v==3)?'*':'.'; //nasce se v==3
            }
        char[][] tmp = mappa;
        mappa = nuovaMappa;
        nuovaMappa = tmp;
    }

    private int vicini(int i, int j){
        int conta=0;
        if(i>0 && mappa[i-1][j]=='*') conta++; //NORD
        if(i>0 && j<m-1 &&  mappa[i-1][j+1]=='*') conta++; //NE
        if(i>0 && j>0 && mappa[i-1][j-1]=='*') conta++; //NO
        if(j<m-1 && mappa[i][j+1]=='*') conta++; //E
        if(j>0 && mappa[i][j-1]=='*') conta++; //O
        if(i<n-1 && mappa[i+1][j]=='*') conta++; //S
        if(i<n-1 && j<m-1 && mappa[i+1][j+1]=='*') conta++; //SE
        if(i<n-1 && j>0 && mappa[i+1][j-1]=='*') conta++; //SO
        return conta;

    }

    public String toString(){
        StringBuilder sb = new StringBuilder(n*m);
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++)
				sb.append(mappa[i][j]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String ... args){
        GiocoDellaVita gol = new GiocoDellaVita(10,10);
        System.out.println(gol); 
        gol.configuraRandom();
        System.out.println(gol); gol.prossimaGenerazione();
        System.out.println(gol); gol.prossimaGenerazione();
        int k=0;
        while(k<50){    
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    gol.prossimaGenerazione();
                }
            }
            System.out.println(gol);
            k++;
        }
        System.out.println(gol);
    }
}
