package proj_sudoku.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
    private final int SIZE = 9;
    private int[][] m = new int[SIZE][SIZE];  // all '0' by default
    private boolean[][] imp = new boolean[SIZE][SIZE];
    private ArrayList<int[][]> sol = new ArrayList<>();
    final int LIMITE_SOLUZIONI = 1000;

    public Sudoku() {}
    public Sudoku(int[][] imp) {
        if (imp.length > SIZE * SIZE || imp[0].length != 3) throw new IllegalArgumentException();
        for (int i = 0; i < imp.length; i++) imposta(imp[i][0], imp[i][1], imp[i][2]);
    }

    public ArrayList<int[][]> getSol() { return new ArrayList<>(sol); }

    public void risolvi() { colloca(0, 0); }

    private void colloca(int row, int col) {
        if (sol.size() == LIMITE_SOLUZIONI) return;

        while(imp[row][col]) { // salta celle preimpostate
            if (col == SIZE - 1) {
                row++; col = 0;
                if(row==SIZE){
                    scriviSoluzione();
                    return;
                }
            } else col++;
        }

        for (int v = 1; v <= 9; v++) {
            if (assegnabile(row, col, v)) {
                assegna(row, col, v);
                if (row == SIZE - 1 && col == SIZE - 1) scriviSoluzione();
                else{
                    int newCol = (col+1)%SIZE;
                    int newRow = (newCol==0) ? row+1: row;
                    colloca(newRow, newCol);
                }
                deassegna(row, col);
            }
        }
    }

    private void assegna(int i, int j, int v) {
        m[i][j] = v;
    }

    private void deassegna(int i, int j) {
        m[i][j] = 0;
    }

    private void scriviSoluzione() {
        int[][] nuovaSol = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                nuovaSol[i][j] = m[i][j];
        sol.add(nuovaSol);
    }

    public void imposta(int i, int j, int v) throws RuntimeException {
        if (assegnabile(i, j, v)) {
            m[i][j] = v;
            imp[i][j] = true;
        } else {
            for(int[] row: m)
            	System.out.println(java.util.Arrays.toString(row));
            throw new RuntimeException("valore non impostabile m["+(i+1)+"]["+(i+1)+"]="+v);
        }
    }

    public boolean assegnabile(int i, int j, int v) {
        if (i < 0 || i > SIZE - 1 || j < 0 || j > SIZE - 1 || v < 1 || v > 9) throw new IllegalArgumentException();
        if (m[i][j] != 0) return false;

        /* check v sulla stessa riga o stessa colonna */
        for (int n = 0; n < SIZE; n++)
            if (m[n][j] == v || m[i][n] == v) return false;

        /* check v nello stesso box 3x3 */
        int row = i - i % 3;
        int col = j - j % 3;
        for (int r = row; r < row + 3; r++)
            for (int c = col; c < col + 3; c++)
                if (m[r][c] == v) return false;
        return true;
    }

    public boolean isSolution(int[][] sol) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                /* stesso valore sulla stessa riga o stessa colonna */
                for (int n = 0; n < SIZE; n++) {
                    if (n != i && sol[n][j] == sol[i][j]) return false;
                    if (n != j && sol[i][n] == sol[i][j]) return false;
                }
                /* stesso val nello stesso box */
                int row = i - i % 3;
                int col = j - j % 3;
                for (int r = row; r < row + 3; r++)
                    for (int c = col; c < col + 3; c++) {
                        if (r == i && c == j) continue;
                        if (sol[r][c] == sol[i][j]) return false;
                    }
            }//for_j
        }//for_i
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        for (int i = 0; i < SIZE; i++)
            sb.append(Arrays.toString(m[i]) + "\n");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        int[][] imp = {{0,0,1},{0,8,8},{1,1,2},
                        {1,7,7},{2,2,3},{2,6,9},
                        {3,3,4},{4,0,9},{4,1,8},
                        {4,2,7}, {4,4,5},{4,6,3},
                        {4,7,2},{4,8,1},{5,5,6},
                        {6,2,2}, {6,6,7},
                        {7,1,3},{7,7,8},{8,0,4},
                        {8,8,9}};
        Sudoku s = new Sudoku(imp);

        System.out.println("iniziale:\n"+s);
        s.risolvi();
        if(s.getSol().size()==0) System.out.println("No solution exist");
        int i = 1;
        for(int[][] sol: s.getSol()){
            System.out.println(i+"=");
            for(int[] row: sol)
            	System.out.println(java.util.Arrays.toString(row));
            System.out.println("\n");
            i++;
        }

    }//mainSudoku
}//Sudoku

