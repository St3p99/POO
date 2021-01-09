package proj_sudoku.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

public class SudokuGUI extends JFrame {
    private File saveFile = null;
    private final int SIZE = 9;
    private Sudoku sudoku;
    private String title = " Sudoku Solver ";
    private ArrayList<int[][]> solution;
    private int solNumber;
    private JTextField[][] board;
    private boolean[][] constraints = new boolean[SIZE][SIZE];
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem exit, open, save, saveWName;
    private JPanel boardPanel, solutionButtonPanel, commandButtonPanel;
    private JLabel nSolutionLabel;
    private JButton edit, set, clear, solve, next, previous;
    private ActionListenerCustom AListner = new ActionListenerCustom();

    public SudokuGUI(){
        setTitle(title);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(exit()) System.exit(0);
            }//windowClosing
        });

        /* creating file menu*/
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        file = new JMenu("File");
        menuBar.add(file);

        open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(AListner);

        save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(AListner);

        saveWName = new JMenuItem("Save with name");
        file.add(saveWName);
        saveWName.addActionListener(AListner);

        exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(AListner);

        /* creating button */
        solutionButtonPanel = new JPanel();
        nSolutionLabel = new JLabel("");
        previous = new JButton("Previous solution");
        previous.addActionListener(AListner);
        next = new JButton("Next solution");
        solutionButtonPanel.add(nSolutionLabel);
        next.addActionListener(AListner);
        solutionButtonPanel.add(previous); solutionButtonPanel.add(next);
        solutionButtonPanel.setVisible(true);
        this.add(BorderLayout.SOUTH, solutionButtonPanel);

        commandButtonPanel = new JPanel();
        edit = new JButton("Edit");
        edit.addActionListener(AListner);
        set = new JButton("Set");
        set.addActionListener(AListner);
        clear = new JButton("Clear");
        clear.addActionListener(AListner);
        solve = new JButton("Solve");
        solve.addActionListener(AListner);
        commandButtonPanel.add(edit); commandButtonPanel.add(set); commandButtonPanel.add(clear);
        commandButtonPanel.add(solve); commandButtonPanel.setVisible(true);
        this.add(BorderLayout.NORTH, commandButtonPanel);

        /* creating board */
        boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        board = new JTextField[SIZE][SIZE];
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++){
                JTextField txt = new JTextField("");
                txt.setFont(new Font("Courier", Font.PLAIN, 15));
                txt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                if((i == 3 && j < 9) || (i == 6 && j < 9)) {
                    txt.setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.DARK_GRAY));
                }
                if((j == 3 && i < 9) || (j == 6 && i < 9)) {
                    txt.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.DARK_GRAY));
                }
                if((i == 3 && j == 3) || (i == 3 && j == 6) || (i == 6 && j == 3) || (i == 6 && j == 6)) {
                    txt.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.DARK_GRAY));
                }
                txt.setHorizontalAlignment(JTextField.CENTER);
                boardPanel.add(txt);
                board[i][j] = txt;
            }
        lockBoard();
        boardPanel.setVisible(true);
        this.add(BorderLayout.CENTER, boardPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocation(screenSize.width/2 - 250, screenSize.height/2 - 300);
        setSize(500, 500);
        start();
    }

    private void start(){
        open.setEnabled(true);
        exit.setEnabled(true);
        save.setEnabled(false);
        saveWName.setEnabled(false);
        edit.setEnabled(true);
        set.setEnabled(false);
        clear.setEnabled(false);
        solve.setEnabled(false);
        next.setEnabled(false);
        previous.setEnabled(false);
    }

    private boolean exit(){
        int opt=JOptionPane.showConfirmDialog(
                null, "Continue?", "Exit",
                JOptionPane.YES_NO_OPTION);
        return opt==JOptionPane.YES_OPTION;
    }//exit

    public static void main(String[] args) {
        SudokuGUI sg = new SudokuGUI();
        sg.setVisible(true);
    }

    private class ActionListenerCustom implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if( e.getSource()==exit ){
                if(exit()) System.exit(0);
            }//exit
            else if( e.getSource()==open ){
                JFileChooser chooser=new JFileChooser();
                boolean ok = true; File newFile = null;
                try{
                    if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                        if( !chooser.getSelectedFile().exists() )
                            JOptionPane.showMessageDialog(null, "File does not exist!");
                        else{
                            newFile = chooser.getSelectedFile();
                            try{
                                loadFile( newFile.getAbsolutePath());
                            }catch(IOException ioe){
                                ok = false;
                                JOptionPane.showMessageDialog(null, "File cannot be loaded!");
                            }
                        }
                    }
                    else {
                        ok=false; JOptionPane.showMessageDialog(null, "No opening");
                    }
                }catch( Exception exc ){
                    exc.printStackTrace(); ok = false;
                }
                if(ok){
                    saveFile = newFile;
                    SudokuGUI.this.setTitle(title+" - "+saveFile.getName());
                    /* clear */
                    nSolutionLabel.setText("");
                    unlockBoard();
                    setDefaultTxtField();
                    save.setEnabled(false); saveWName.setEnabled(false);
                    edit.setEnabled(false); set.setEnabled(true); solve.setEnabled(false);
                    next.setEnabled(false); previous.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Click 'SET' to activate the new board");
                }
            }//open
            else if( e.getSource()==save ){
                JFileChooser chooser= new JFileChooser();
                File newFile = null;
                boolean saveNewFile = false;
                try{
                    if( saveFile!=null ){
                        int ans = JOptionPane.showConfirmDialog(null, "Overwrite "+saveFile.getAbsolutePath()+" ?");
                        if( ans==0 )  // YES_OPTION
                            save( saveFile.getAbsolutePath() );
                        else
                            JOptionPane.showMessageDialog(null, "No saving!");
                        return;
                    }
                    // saveFile == null
                    if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
                        newFile = chooser.getSelectedFile();
                    }
                    if( newFile !=null ){
                        saveNewFile = true;
                        save( newFile.getAbsolutePath() );
                    }
                    else JOptionPane.showMessageDialog(null, "No saving!");
                }catch( Exception exc ){
                    exc.printStackTrace(); saveNewFile =false;
                }
                if(saveNewFile){
                    saveFile = newFile;
                    SudokuGUI.this.setTitle(title+" - "+saveFile.getName());
                }
            }//save
            else if( e.getSource()==saveWName ){
                JFileChooser chooser=new JFileChooser();
                File newFile = null;
                boolean save = true;
                try{
                    if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
                        newFile=chooser.getSelectedFile();
                    }
                    if( newFile!=null ){
                        save( newFile.getAbsolutePath() );
                    }
                    else{ JOptionPane.showMessageDialog(null, "No saving!"); save = false;}
                }catch( Exception exc ){
                    exc.printStackTrace(); save = false;
                }
                if(save){
                    saveFile = newFile;
                    SudokuGUI.this.setTitle(title+" - "+saveFile.getName());
                }
            }//saveWName

            else if(e.getSource()==edit){
                unlockBoard();
                setDefaultTxtField();
                String[][] m = getBoard();
                /* only constraints is visible in edit mode */
                for(int i=0; i<SIZE;i++)
                    for(int j=0; j<SIZE; j++)
                        if(! constraints[i][j]) m[i][j] = "";
                setBoard(m);
                /* save is unavailable in edit mode */
                nSolutionLabel.setText("");
                save.setEnabled(false); saveWName.setEnabled(false);
                set.setEnabled(true); edit.setEnabled(false); solve.setEnabled(false);
                next.setEnabled(false); previous.setEnabled(false);
            }//edit

            else if(e.getSource()==set){
                lockBoard();
                int[][] m;
                /* reading constraints*/
                try{
                    m = boardToInteger(getBoard());
                }catch (NumberFormatException exp){
                    JOptionPane.showMessageDialog(null, "Enter only integer numbers");
                    unlockBoard(); solve.setEnabled(false); return;
                }
                /* creating sudoku */
                sudoku = new Sudoku();
                boolean ok = true;
                for(int i=0; i<SIZE;i++)
                    for(int j=0; j<SIZE; j++){
                        if(m[i][j]!=0){//isConstraint
                            if(m[i][j]>0 && m[i][j] <=9 && sudoku.assegnabile(i,j,m[i][j])){
                                sudoku.imposta(i,j,m[i][j]);
                                board[i][j].setForeground(new Color(0, 0, 0));  //BLACK
                            }
                            else{//invalid constraint
                                ok = false;
                                board[i][j].setForeground(new Color(255, 13, 0)); // RED
                            }
                            board[i][j].setFont(new Font("Courier", Font.BOLD, 15));
                            constraints[i][j]=true;
                        }
                        else{//isNotCostraint
                        	board[i][j].setText("");
                            board[i][j].setForeground(new Color(49, 105, 221)); // BLUE
                            board[i][j].setFont(new Font("Courier", Font.PLAIN, 15));
                            constraints[i][j]=false;
                        }
                    }//for_j
                if(!ok){
                    JOptionPane.showMessageDialog(null, "Invalid constraints!");
                    unlockBoard(); solve.setEnabled(false); return;
                }
                /* After 'Set', save is available */
                nSolutionLabel.setText("");
                save.setEnabled(true); saveWName.setEnabled(true);
                edit.setEnabled(true); set.setEnabled(false);
                clear.setEnabled(true); solve.setEnabled(true);
            }//set
            else if(e.getSource()==clear){
                lockBoard();
                constraints = new boolean[SIZE][SIZE];
                String[][] m = new String[SIZE][SIZE]; setBoard(m);
                nSolutionLabel.setText("");
                solution = new ArrayList<>();
                edit.setEnabled(true); set.setEnabled(false); solve.setEnabled(false);
                next.setEnabled(false); previous.setEnabled(false);
            }//clear
            else if(e.getSource()==solve){
                sudoku.risolvi();
                solution = sudoku.getSol();
                if(solution.size()==0) JOptionPane.showMessageDialog(null, " There is no solution for this sudoku ");
                else{
                    setBoard(boardToString(solution.get(0))); solNumber = 1;
                    if(solution.size()==sudoku.LIMITE_SOLUZIONI)
                        nSolutionLabel.setText(solNumber+" of "+solution.size()+"+");
                    else nSolutionLabel.setText(solNumber+" of "+solution.size());
                    if(solution.size()>1) next.setEnabled(true);
                }
            }//solve
            else if(e.getSource()==next){
                if(solNumber==solution.size()-1) next.setEnabled(false);
                solNumber++;
                setBoard(boardToString(solution.get(solNumber-1)));
                if(solution.size()==sudoku.LIMITE_SOLUZIONI)
                    nSolutionLabel.setText(solNumber+" of "+solution.size()+"+");
                else nSolutionLabel.setText(solNumber+" of "+solution.size());
                previous.setEnabled(true);
            }//next
            else if(e.getSource()==previous){
                if(solNumber==2) previous.setEnabled(false);
                solNumber--;
                setBoard(boardToString(solution.get(solNumber-1)));
                if(solution.size()==sudoku.LIMITE_SOLUZIONI)
                    nSolutionLabel.setText(solNumber+" of "+solution.size()+"+");
                else nSolutionLabel.setText(solNumber+" of "+solution.size());
                next.setEnabled(true);
            }//previous
        }
    }

    private void save(String nameFile) throws IOException{
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(nameFile));
        int[][] m = boardToInteger(getBoard());
        for(int i=0; i<SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if(constraints[i][j]) dos.writeInt(m[i][j]);
                else{ dos.writeInt(0);}
        dos.close();
    }//save

    private void loadFile(String nameFile) throws IOException{
        DataInputStream dis = new DataInputStream(new FileInputStream(nameFile));
        boolean ok = true;
        int[][] m = new int[SIZE][SIZE];
        boolean[][] constraints = new boolean[SIZE][SIZE];
        int i =0, j=0;
        for(;;){
            try {
                int x = dis.readInt(); // throws IOException if is not an integer
                if(x<0 || x>9){ok = false; break;}
                m[i][j] = x;
                if(x!=0) constraints[i][j] = true;
                if(j==8){j=0; i++;}
                else j++;
            }catch (EOFException e){
                break;
            }
        }
        dis.close();
        if(!ok) throw new IOException();
        /* set new value */
        this.constraints = constraints;
        setBoard(boardToString(m));
    }//loadFile

    private void setDefaultTxtField(){
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE ; j++){
                board[i][j].setForeground(new Color(0,0,0));  // BLACK
                board[i][j].setFont(new Font("Courier", Font.PLAIN, 15));
            }
    }

    private void lockBoard(){
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                board[i][j].setEditable(false);;
    }

    private void unlockBoard(){
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                board[i][j].setEditable(true);
    }

    private int[][] boardToInteger(String[][] m){
        int nRighe=m.length;
        int nColonne=m[0].length;
        int[][] ret = new int[nRighe][nColonne];
        for(int i=0;i<nRighe;i++)
            for(int j=0;j<nColonne;j++)
                if(m[i][j].equals(""))
                    ret[i][j]=0;
                else
                    ret[i][j]=Integer.parseInt(m[i][j]);
        return ret;
    }

    private String[][] getBoard(){
        String[][] ret = new String[SIZE][SIZE];
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                ret[i][j] = board[i][j].getText();
        return ret;
    }

    private void setBoard(String[][] A){
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                board[i][j].setText(A[i][j]);
    }

    private String[][] boardToString(int[][] m){
        int nRighe=m.length;
        int nColonne=m[0].length;
        String[][] ret = new String[nRighe][nColonne];
        for(int i=0;i<nRighe;i++)
            for(int j=0;j<nColonne;j++)
                if(m[i][j]==0)
                    ret[i][j]= "";
                else
                    ret[i][j]=""+m[i][j];
        return ret;
    }
}