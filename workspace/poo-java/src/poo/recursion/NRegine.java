package poo.recursion;

public class NRegine {
    /* Si vogliono trovare tutte le soluzioni */
    private int numSol = 0;
    private boolean board[][];  // scacchiera
    private int n;

    public NRegine(int n){
        if(n<4) throw new IllegalArgumentException("Non esistono soluzioni");
        this.n = n;
        board = new boolean[n][n];  // all false by default
    }

    public void risolvi(){
        collocaRegina(0);
    }

    private void collocaRegina(int row){
        for(int col=0; col< n; col++) { // tutte le possibili scelte
            if(assegnabile(row,col)){
                assegna(row, col);
                if(row == n-1) scriviSoluzione();
                else collocaRegina(row+1);
                // necessitá di fare il backtracking
                deassegna(row, col);
            }
        }
    }//collocaRegina

    private boolean assegnabile(int row, int col){
        /*   -----------------
        *    \    DA        /
        *     \ VERIFICARE /
        *      \[row,col]/
        * */

        //verifica NORD
        for(int r = row-1; r>=0; r--)
            if(board[r][col]) return false;
        //verifica NORD-EST
        for(int r = row-1, c=col+1; r>=0 && c<n; r--, c++)
            if(board[r][c]) return false;
        //verifica NORD-OVEST
        for(int r=row-1, c=col-1; r>=0 && c>=0; r--, c--)
            if(board[r][c]) return false;
        return true;
    }//assegnabile

    private void scriviSoluzione(){
        numSol++;
        System.out.print(numSol+": ");
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(board[i][j]){
                    System.out.print("<"+i+","+j+">");
                    break; // c'é solo un 'true' per riga
                }
        System.out.println();
    }

    private void assegna(int row, int col){
        board[row][col] = true;
    }

    private void deassegna(int row, int col){
        board[row][col] = false;
    }

    public static void main(String[] args) {
        new NRegine(4).risolvi();
    }
}
