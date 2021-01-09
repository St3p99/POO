package proj_polinomi.polinomi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class PolinomioGUI extends JFrame {
    String stringAbout = "Applicazione grafica per svolgere operazioni su polinomi.\n" +
            " É possibile caricare/salvare file di testo di polinomi,\n" +
            " aggiungere/rimuovere polinomi ed effettuare le seguenti\n" +
            " operazioni sui polinomi selezionati:\n" +
            " 1. Valuta P(x) in x0.\n" +
            " 2. Derivata prima di P(x).\n" +
            " 3. Somma tra due polinomi.\n" +
            " 4. Differenza tra due polinomi.\n" +
            "    Esempio: P1(x) - P2(x) oppure P2(x) - P1(x)\n" +
            " 5. Moltiplicazione tra due polinomi.\n";

    private File fileDiSalvataggio = null;
    private JMenuBar menuBar;
    private JMenu fileMenu, nuovo, commandMenu, operationMenu, differenzaInnerMenu, helpMenu;
    private JMenuItem AL, LL, Set, Map, Conc, Vec,
            apri, salva, salvaConNome, esci,  // fileMenu
            aggiungi, aggiungiDaFile, rimuovi, rimuoviTutti, trova, selezionaTutti, deselezionaTutti, //comandi
            valutaIn, derivata, somma, differenza1_2, differenza2_1, moltiplicazione, // operazioni
            about;  // help
    private JPanel checkBoxesPanel;
    private String titolo = "Polinomio GUI";
    private String tipo = "Map";
    private ArrayList<Polinomio> polinomi = new ArrayList<>();
    private HashSet<Integer> indiciPolSelezionati = new HashSet<>();
    private MenuItemListener MIListener =  new MenuItemListener();
    private CheckBoxListener CBListener = new CheckBoxListener();

    public PolinomioGUI(){
        setTitle(titolo);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(uscitaConfermata()) System.exit(0);
            }//windowClosing
        });

        //set menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        setFileMenu();
        setCommandMenu();
        setOperationMenu();

        //set helpMenu
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        about = new JMenuItem("About");
        about.addActionListener(MIListener);
        helpMenu.add(about);

        menuStart();

        setCheckBoxesPanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocation(screenSize.width/2 - 250, screenSize.height/2 - 300);
        setSize(500, 600);
    }//Costruttore di Defualt

    private void setFileMenu(){
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        nuovo = new JMenu("Nuovo");
        fileMenu.add(nuovo);

        AL = new JMenuItem("ArrayList");
        nuovo.add(AL);
        AL.addActionListener(MIListener);

        LL = new JMenuItem("LinkedList");
        nuovo.add(LL);
        LL.addActionListener(MIListener);

        Map = new JMenuItem("Map");
        nuovo.add(Map);
        Map.addActionListener(MIListener);

        Set = new JMenuItem("Set");
        nuovo.add(Set);
        Set.addActionListener(MIListener);

        Conc = new JMenuItem("Lista concatenata");
        nuovo.add(Conc);
        Conc.addActionListener(MIListener);

        Vec = new JMenuItem("Vector");
        nuovo.add(Vec);
        Vec.addActionListener(MIListener);

        apri = new JMenuItem("Apri");
        fileMenu.add(apri);
        apri.addActionListener(MIListener);

        salva = new JMenuItem("Salva");
        fileMenu.add(salva);
        salva.addActionListener(MIListener);

        salvaConNome = new JMenuItem("Salva con nome");
        fileMenu.add(salvaConNome);
        salvaConNome.addActionListener(MIListener);

        fileMenu.addSeparator();

        esci = new JMenuItem("Esci");
        fileMenu.add(esci);
        esci.addActionListener(MIListener);
    }//setFileMenu

    private void setCommandMenu(){
        commandMenu = new JMenu("Comandi");
        menuBar.add(commandMenu);

        aggiungi = new JMenuItem("Aggiungi polinomio");
        commandMenu.add(aggiungi);
        aggiungi.addActionListener(MIListener);

        aggiungiDaFile = new JMenuItem("Aggiungi polinomi da file");
        commandMenu.add(aggiungiDaFile);
        aggiungiDaFile.addActionListener(MIListener);

        rimuovi = new JMenuItem("Rimuovi polinomi selezionati");
        commandMenu.add(rimuovi);
        rimuovi.addActionListener(MIListener);

        rimuoviTutti = new JMenuItem("Rimuovi tutti i polinomi");
        commandMenu.add(rimuoviTutti);
        rimuoviTutti.addActionListener(MIListener);

        trova = new JMenuItem("Trova polinomio");
        commandMenu.add(trova);
        trova.addActionListener(MIListener);

        selezionaTutti = new JMenuItem("Seleziona tutti");
        commandMenu.add(selezionaTutti);
        selezionaTutti.addActionListener(MIListener);

        deselezionaTutti = new JMenuItem("Deseleziona tutti");
        commandMenu.add(deselezionaTutti);
        deselezionaTutti.addActionListener(MIListener);
    }//setCommandMenu

    private void setOperationMenu(){
        operationMenu = new JMenu("Operazioni");
        menuBar.add(operationMenu);

        valutaIn = new JMenuItem("Valuta in");
        operationMenu.add(valutaIn);
        valutaIn.addActionListener(MIListener);

        derivata = new JMenuItem("Derivata prima");
        operationMenu.add(derivata);
        derivata.addActionListener(MIListener);

        operationMenu.addSeparator();

        somma = new JMenuItem("Somma");
        operationMenu.add(somma);
        somma.addActionListener(MIListener);

        differenzaInnerMenu = new JMenu("Differenza");
        operationMenu.add(differenzaInnerMenu);
        differenza1_2 = new JMenuItem();  // testo da impostare dinamicamente
        differenzaInnerMenu.add(differenza1_2);
        differenza1_2.addActionListener(MIListener);
        differenza2_1 = new JMenuItem();  // testo da impostare dinamicamente
        differenzaInnerMenu.add(differenza2_1);
        differenza2_1.addActionListener(MIListener);

        moltiplicazione = new JMenuItem("Moltiplicazione");
        operationMenu.add(moltiplicazione);
        moltiplicazione.addActionListener(MIListener);
    }//setOperationMenu

    private void setCheckBoxesPanel(){
        checkBoxesPanel = new JPanel(new GridLayout(0,1,0,20));// righe = 0 => qualsiasi numero di righe
        checkBoxesPanel.setSize(500, 600);
        JScrollPane sp = new JScrollPane(checkBoxesPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(sp);
    }

    /* Listener */
    private class MenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            /* gestione fileMenuItem */
            if( e.getSource()==esci ){
                if(uscitaConfermata()) System.exit(0);
            }//esci
            else if( e.getSource()==AL){
                tipo = "AL"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==LL){
                tipo = "LL"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==Map){
                tipo = "Map"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==Set){
                tipo = "Set"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==Vec){
                tipo = "Vector"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==Conc){
                tipo = "Lista concatenata"; menuAvviato(); PolinomioGUI.this.setTitle(titolo+" - "+tipo);
            }
            else if( e.getSource()==apri ){
                JFileChooser chooser=new JFileChooser();
                boolean okLettura = true;
                File newFile = null;
                try{
                    if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                        if( !chooser.getSelectedFile().exists() )
                            JOptionPane.showMessageDialog(null, "File inesistente!");
                        else{
                            newFile = chooser.getSelectedFile();
                            try{
                                caricaFile( newFile.getAbsolutePath(), true);
                            }catch(IOException ioe){
                                okLettura = false;
                                JOptionPane.showMessageDialog(null, "Fallimento apertura. File malformato!");
                            }
                        }
                    }
                    else { JOptionPane.showMessageDialog(null, "Nessuna apertura!"); okLettura=false; }
                }catch( Exception exc ){
                    exc.printStackTrace(); okLettura = false;
                }
                if(okLettura){
                    fileDiSalvataggio = newFile;
                    PolinomioGUI.this.setTitle(titolo+" - "+tipo+" - "+fileDiSalvataggio.getName());
                }
            }//apri

            else if( e.getSource()==salva ){
                JFileChooser chooser= new JFileChooser();
                File newFile = null; // fileDiSalvataggio verrá sovrascritto solo se la lettura andrá a buon fine
                boolean salvaNewFile = false;
                try{
                    if( fileDiSalvataggio!=null ){
                        int ans = JOptionPane.showConfirmDialog(null, "Sovrascrivere "+fileDiSalvataggio.getAbsolutePath()+" ?");
                        if( ans==0 )  // Si
                            salva( fileDiSalvataggio.getAbsolutePath() );
                        else
                            JOptionPane.showMessageDialog(null, "Nessun salvataggio!");
                        return;
                    }
                    //fileDiSalvataggio == null
                    if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
                        newFile = chooser.getSelectedFile();
                    }
                    if( newFile !=null ){
                        salva( newFile.getAbsolutePath() ); salvaNewFile = true;
                    }
                    else JOptionPane.showMessageDialog(null, "Nessun Salvataggio!");
                }catch( Exception exc ){
                    exc.printStackTrace();
                }
                if(salvaNewFile){
                    fileDiSalvataggio = newFile;
                    PolinomioGUI.this.setTitle(titolo+" - "+tipo+" - "+fileDiSalvataggio.getName());
                }
            }//salva

            else if( e.getSource()==salvaConNome ){
                JFileChooser chooser=new JFileChooser();
                File newFile = null; // fileDiSalvataggio verrá sovrascritto solo se la lettura andrá a buon fine
                boolean salvato = true;
                try{
                    if( chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
                        newFile=chooser.getSelectedFile();
                    }
                    if( newFile!=null ){
                        salva( newFile.getAbsolutePath() );
                    }
                    else{ JOptionPane.showMessageDialog(null, "Nessun Salvataggio!"); salvato = false;}
                }catch( Exception exc ){
                    salvato = false;  exc.printStackTrace();
                }
                if(salvato){
                    fileDiSalvataggio = newFile;
                    PolinomioGUI.this.setTitle(titolo+" - "+tipo+" - "+fileDiSalvataggio.getName());
                }
            }//salvaConNome

            /* gestione commandMenuItem */
            else if(e.getSource()==aggiungi){
                Polinomio newPol;
                String inp = JOptionPane.showInputDialog(PolinomioGUI.this,"Inserisci polinomio: ", "Input", JOptionPane.PLAIN_MESSAGE);
                for(;;){
                    if(inp==null) return;
                    if(! inp.matches(ValutaPolinomio.POLINOMIO)){  // gestisco l'IllegalArgumentException del metodo interpretaPolinomio(.)
                        JOptionPane.showMessageDialog(PolinomioGUI.this,
                                "Input non valido!\nRiprova con polinomi del tipo: 3x^4-x^2+x+2");
                        // chiedi di nuovo input, valore input di default = input precedente
                        inp = JOptionPane.showInputDialog(PolinomioGUI.this, "Inserisci polinomio: ", inp);
                        continue;
                    }
                    try{
                        newPol = creaPolinomio(ValutaPolinomio.interpretaPolinomio(inp));
                    }
                    catch (NumberFormatException exc){
                        JOptionPane.showMessageDialog(PolinomioGUI.this, "Valore intero troppo grande,\nnon esagerare!");
                        // chiedi di nuovo input, valore input di default = input precedente
                        inp = JOptionPane.showInputDialog(PolinomioGUI.this, "Inserisci polinomio: ", inp);
                        continue;
                    }
                    break;
                }
                aggiungiPolinomio(newPol);
            }//aggiungi

            else if( e.getSource()==aggiungiDaFile ){
                JFileChooser chooser=new JFileChooser();
                try{
                    if( chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                        if( !chooser.getSelectedFile().exists() ){
                            JOptionPane.showMessageDialog(null, "File inesistente!");
                        }
                        else{
                            try{
                                caricaFile( chooser.getSelectedFile().getAbsolutePath(), false);
                            }catch(IOException ioe){
                                JOptionPane.showMessageDialog(null, "Fallimento apertura. File malformato!");
                            }
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Nessuna apertura!");
                }catch( Exception exc ){
                    exc.printStackTrace();
                }
            }//aggiungiDaFile

            else if(e.getSource()==rimuovi){
                int opt=JOptionPane.showConfirmDialog(
                        PolinomioGUI.this, " Sicuro di voler rimuovere i polinomi selezionati ? ", "",
                        JOptionPane.YES_NO_OPTION);
                if(opt==JOptionPane.NO_OPTION) return;
                Iterator<Polinomio> it = polinomi.iterator();
                int i = 1;
                while(it.hasNext()){
                    it.next();
                    if(indiciPolSelezionati.contains(i))
                        it.remove();
                    i++;
                }
                i = 1;
                checkBoxesPanel.removeAll();
                for(Polinomio p: polinomi){
                    JCheckBox cb = new JCheckBox("P"+i+"(x)="+p);
                    cb.addItemListener(CBListener);
                    checkBoxesPanel.add(cb);
                    i++;
                }
                checkBoxesPanel.revalidate();
                indiciPolSelezionati.clear();
                enablerMenu();
                if(polinomi.size()==0){
                    trova.setEnabled(false); rimuoviTutti.setEnabled(false); selezionaTutti.setEnabled(false);
                }
            }//rimuovi

            else if(e.getSource()==rimuoviTutti){
                int opt=JOptionPane.showConfirmDialog(
                        PolinomioGUI.this, " Sicuro di voler rimuovere tutti i polinomi ? ", "",
                        JOptionPane.YES_NO_OPTION);
                if(opt==JOptionPane.NO_OPTION) return;
                svuota();
            }//rimuoviTutti

            else if (e.getSource()==trova){
                String inp = JOptionPane.showInputDialog(PolinomioGUI.this,"Inserisci polinomio: ", "Input", JOptionPane.PLAIN_MESSAGE);
                for(;;){
                    if(inp==null) return;
                    if(inp.matches(ValutaPolinomio.POLINOMIO)) break;
                    JOptionPane.showMessageDialog(PolinomioGUI.this,
                            "Input non valido!\nRiprova con polinomi del tipo: 3x^4-x^2+x+2");
                    // chiedi di nuovo input, valore input di default = input precedente
                    inp = JOptionPane.showInputDialog(PolinomioGUI.this, "Inserisci polinomio: ", inp);
                }
                Polinomio p = ValutaPolinomio.interpretaPolinomio(inp);
                for(int i = 0; i<polinomi.size(); i++){
                    if(polinomi.get(i).equals(p)){
                        JOptionPane.showMessageDialog(PolinomioGUI.this,
                                "Il polinomio "+p+" cercato é il numero: "+(i+1));
                        return;
                    }
                }//for
                /* Polinomio non trovato */
                scriviPolinomiSimili(p);
            }//trova

            else if( e.getSource()==selezionaTutti ){
                for(int i = 1; i <= polinomi.size(); i++)
                    indiciPolSelezionati.add(i);
                Component[] components =  checkBoxesPanel.getComponents();
                int i =0;
                for(Component c: components){
                    if(c instanceof JCheckBox){
                        JCheckBox cb = (JCheckBox) c;
                        checkBoxesPanel.remove(i);
                        cb = new JCheckBox(cb.getText(), true);
                        cb.addItemListener(CBListener);
                        checkBoxesPanel.add(cb,i);
                    }
                    i++;
                }
                enablerMenu();
                checkBoxesPanel.revalidate();
            }//selezionaTutti

            else if( e.getSource()==deselezionaTutti ){
                for(int i = 1; i <= polinomi.size(); i++)
                    indiciPolSelezionati.remove(i);
                Component[] components =  checkBoxesPanel.getComponents();
                int i =0;
                for(Component c: components){
                    if(c instanceof JCheckBox){
                        JCheckBox cb = (JCheckBox) c;
                        checkBoxesPanel.remove(i);
                        cb = new JCheckBox(cb.getText(), false);
                        cb.addItemListener(CBListener);
                        checkBoxesPanel.add(cb,i);
                    }
                    i++;
                }
                enablerMenu();
                checkBoxesPanel.revalidate();
            }//deselezionaTutti

            else if(e.getSource()==valutaIn){
                Iterator<Integer> it = indiciPolSelezionati.iterator();
                int i= it.next(); // indiciPolSelezionati conterrá un solo indice
                Polinomio p = polinomi.get(i-1);
                double x;
                for(;;){
                    String inp = JOptionPane.showInputDialog(PolinomioGUI.this, "Valuta P"+i+"(x)="+p+" in x = ");
                    if( inp == null ) return;
                    try{
                        x = Double.parseDouble(inp);
                    }
                    catch (NumberFormatException exc){
                        JOptionPane.showMessageDialog(PolinomioGUI.this, "Formato errato!\nesempio: 2.3"); continue;
                    }
                    break;
                }//for_ever
                JOptionPane.showMessageDialog(PolinomioGUI.this, "P"+i+"("+x+") = "+p.valore(x), "Valuta in", JOptionPane.PLAIN_MESSAGE);
            }//valutaIn

            else if( e.getSource()==derivata ){
                Iterator<Integer> it = indiciPolSelezionati.iterator();
                int i= it.next(); // indiciPolSelezionati conterrá un solo indice
                Polinomio derivata = polinomi.get(i-1).derivata();
                String nuovoP = "";
                if(aggiungiPolinomio(derivata)) nuovoP = "P"+polinomi.size()+"(x) = ";
                JOptionPane.showMessageDialog(PolinomioGUI.this,
                        nuovoP+"P'"+i+"(x) = "+derivata,
                        "Derivata", JOptionPane.PLAIN_MESSAGE);
            }

            else if(e.getSource()==somma){
                Polinomio somma = creaPolinomio();
                StringBuilder sb = new StringBuilder(50);
                Iterator<Integer> it = indiciPolSelezionati.iterator();
                while(it.hasNext()){
                    int i = it.next();
                    somma = somma.add(polinomi.get(i-1));
                    if(it.hasNext()) sb.append("P"+i+"(x) + ");
                    else sb.append("P"+i+"(x)\n= "+somma);
                }
                String nuovoP = "";
                if(aggiungiPolinomio(somma)) nuovoP = "P"+polinomi.size()+"(x) = ";
                JOptionPane.showMessageDialog(PolinomioGUI.this, nuovoP+sb.toString(), "Somma",
                        JOptionPane.PLAIN_MESSAGE);
            }//somma

            else if(e.getSource()==differenza1_2 || e.getSource()==differenza2_1){
                Iterator<Integer> it = indiciPolSelezionati.iterator();
                int n1 = it.next(), n2 = it.next();
                Polinomio p1 = polinomi.get(n1-1), p2 = polinomi.get(n2-1);
                String nuovoP = "";
                if(e.getSource()==differenza1_2){
                    Polinomio differenza = p1.add(p2.mul(new Monomio(-1, 0)));
                    if(aggiungiPolinomio(differenza)) nuovoP = "P"+polinomi.size()+"(x) = ";
                    JOptionPane.showMessageDialog(PolinomioGUI.this, nuovoP+"P"+n1+"(x) - P"+n2+"(x) = "+differenza,
                            "Differenza", JOptionPane.PLAIN_MESSAGE);
                }//if
                else{
                    Polinomio differenza = p2.add(p1.mul(new Monomio(-1, 0)));
                    if(aggiungiPolinomio(differenza)) nuovoP = "P"+polinomi.size()+"(x) = ";
                    JOptionPane.showMessageDialog(PolinomioGUI.this, nuovoP+"P"+n2+"(x) - P"+n1+"(x) = "+differenza,
                            "Differenza", JOptionPane.PLAIN_MESSAGE);
                }//else
            }//differenza

            else if(e.getSource()==moltiplicazione){
                Polinomio prod = creaPolinomio(); prod.add(new Monomio(1,0));
                StringBuilder sb = new StringBuilder(50);
                Iterator<Integer> it = indiciPolSelezionati.iterator();
                while(it.hasNext()){
                    int i = it.next();
                    prod = prod.mul(polinomi.get(i-1));
                    if(it.hasNext()) sb.append("P"+i+"(x) * ");
                    else sb.append("P"+i+"(x)\n= "+prod);
                }
                String nuovoP = "";
                if(aggiungiPolinomio(prod)) nuovoP = "P"+polinomi.size()+"(x) = ";
                JOptionPane.showMessageDialog(PolinomioGUI.this, nuovoP+sb.toString(), "Moltiplicazione",
                        JOptionPane.PLAIN_MESSAGE);
            }//moltiplicazione

            else if(e.getSource()==about){
                JOptionPane.showMessageDialog(PolinomioGUI.this, stringAbout, "About", JOptionPane.PLAIN_MESSAGE);
            }//about
        }//actionPerformed
    }//MenuItemListener

    private class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox cb = (JCheckBox) e.getItem();
            int ind = estraiIndPolinomio(cb.getText());
            if(e.getStateChange() == ItemEvent.SELECTED)
                indiciPolSelezionati.add(ind);
            if(e.getStateChange() == ItemEvent.DESELECTED)
                indiciPolSelezionati.remove(ind);
            enablerMenu();
        }
    }//checkBoxListener

    /* Metodi di supporto */

    private boolean uscitaConfermata(){
        int opt=JOptionPane.showConfirmDialog(
                PolinomioGUI.this, "Sicuro di voler continuare?\nUscendo si perderanno tutti i dati !!! ", "Exit",
                JOptionPane.YES_NO_OPTION);
        return opt==JOptionPane.YES_OPTION;
    }//uscitaConfermata

    private int estraiIndPolinomio(String linea){
        /* testo checkBox del tipo Pn(x) = 3x^2-3x+4;
        *  il metodo estrae e restituisce n */
        int i = 1;  // linea.charAt(0) == 'P'
        while( i < linea.length()){
            if(linea.charAt(i) == '(') break;
            i++;
        }
        return Integer.parseInt(linea.substring(1,i));
    }//estraiIndPolinomio

    private boolean aggiungiPolinomio(Polinomio p){
        if(p.size() ==0 ) return false;
        if(!polinomi.contains(p)){
            polinomi.add(p);
            JCheckBox newCB = new JCheckBox("P"+polinomi.size()+"(x) = "+p);
            newCB.addItemListener(CBListener);
            checkBoxesPanel.add(newCB);
            checkBoxesPanel.revalidate();
            trova.setEnabled(true);
            rimuoviTutti.setEnabled(true);
            selezionaTutti.setEnabled(true);
            return true;
        }
        return false;
    }//aggiungiPolinomio

    private void scriviPolinomiSimili(Polinomio p){
        // Polinomio simile se contiene tutti i monomi di p
        StringBuilder sb = new StringBuilder(100); int i = 1;
        for(Polinomio pol: polinomi){
            boolean simile = true;
            for(Monomio m: p)
                if(! pol.contains(m)) simile = false;
            if(simile) sb.append("P"+i+"(x) = "+pol+"\n");
            i++;
        }
        if(sb.length()!=0) JOptionPane.showMessageDialog(PolinomioGUI.this, "Polinomio non trovato.\nForse intendevi:\n"+sb.toString());
        else JOptionPane.showMessageDialog(PolinomioGUI.this, "Polinomio non trovato.");
    }

    private void svuota(){
        polinomi.clear();
        indiciPolSelezionati.clear();
        checkBoxesPanel.removeAll();
        checkBoxesPanel.revalidate();
        trova.setEnabled(false);
        rimuoviTutti.setEnabled(false);
        selezionaTutti.setEnabled(false);
        enablerMenu();
    }//svuota

    private void menuStart(){
        nuovo.setEnabled(true);
        AL.setEnabled(true);
        Set.setEnabled(true);
        LL.setEnabled(true);
        Map.setEnabled(true);
        Conc.setEnabled(true);
        Vec.setEnabled(true);
        apri.setEnabled(false);
        salva.setEnabled(false);
        salvaConNome.setEnabled(false);
        esci.setEnabled(true);
        aggiungi.setEnabled(false);
        aggiungiDaFile.setEnabled(false);
        rimuovi.setEnabled(false);
        rimuoviTutti.setEnabled(false);
        trova.setEnabled(false);
        selezionaTutti.setEnabled(false);
        deselezionaTutti.setEnabled(false);
        valutaIn.setEnabled(false);
        derivata.setEnabled(false);
        somma.setEnabled(false);
        differenzaInnerMenu.setEnabled(false);
        moltiplicazione.setEnabled(false);
    }//menuStart

    private void menuAvviato(){
        nuovo.setEnabled(false);
        apri.setEnabled(true);
        salva.setEnabled(true);
        salvaConNome.setEnabled(true);
        aggiungi.setEnabled(true);
        aggiungiDaFile.setEnabled(true);
    }

    private void enablerMenu(){
        if(indiciPolSelezionati.size()<1){
            valutaIn.setEnabled(false);
            derivata.setEnabled(false);
            somma.setEnabled(false);
            differenzaInnerMenu.setEnabled(false);
            moltiplicazione.setEnabled(false);
            rimuovi.setEnabled(false);
            deselezionaTutti.setEnabled(false);
        }
        else if(indiciPolSelezionati.size()==1){
            valutaIn.setEnabled(true);
            derivata.setEnabled(true);
            somma.setEnabled(false);
            differenzaInnerMenu.setEnabled(false);
            moltiplicazione.setEnabled(false);
            rimuovi.setEnabled(true);
            deselezionaTutti.setEnabled(true);
        }
        else if(indiciPolSelezionati.size() == 2 ){
            valutaIn.setEnabled(false);
            derivata.setEnabled(false);
            somma.setEnabled(true);
            setTestoDinamicoDifferenza();
            differenzaInnerMenu.setEnabled(true);
            moltiplicazione.setEnabled(true);
            rimuovi.setEnabled(true);
            deselezionaTutti.setEnabled(true);
        }
        else{  // indiciPolSelezionati.size()>2
            valutaIn.setEnabled(false);
            derivata.setEnabled(false);
            somma.setEnabled(false);
            differenzaInnerMenu.setEnabled(false);
            moltiplicazione.setEnabled(false);
            rimuovi.setEnabled(true);
            deselezionaTutti.setEnabled(true);
        }
    }//enablerMenu

    private void setTestoDinamicoDifferenza(){
        Iterator<Integer> it = indiciPolSelezionati.iterator();
        int n1 = it.next(), n2 = it.next();
        differenza1_2.setText("P"+n1+"(x) - P"+n2+"(x)");
        differenza2_1.setText("P"+n2+"(x) - P"+n1+"(x)");
    }//setTestoDinamicoDifferenza

    private void caricaFile(String nomeFile, boolean svuota) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(nomeFile));
        ArrayList<Polinomio> polinomiTmp = new ArrayList<>();
        boolean ok = true;
        for(;;){
            String linea = br.readLine();
            if(linea==null)
                break; //EOF
            try{
                Polinomio p = ValutaPolinomio.interpretaPolinomio(linea);
                polinomiTmp.add(creaPolinomio(p));
            }
            catch (Exception e){ ok = false; break;}
        }
        br.close();
        if(! ok) throw new IOException();
        else{
            if(svuota) svuota();
            for(Polinomio p : polinomiTmp)
                aggiungiPolinomio(p);
        }
    }//caricaFile

    private void salva(String nomeFile) throws IOException {
        PrintWriter pw = new PrintWriter( new FileWriter(nomeFile));
        for(Polinomio p: polinomi )
            pw.println(p);
        pw.close();
    }//salva
    
    private Polinomio creaPolinomio(){
        switch (tipo){
            case "AL": return new PolinomioAL();
            case "LL": return new PolinomioLL();
            case "Map": return new PolinomioMap();
            case "Set": return new PolinomioSet();
            case "Vector": return new PolinomioVector();
            default: return new PolinomioConcatenato();
        }
    }

    private Polinomio creaPolinomio(Polinomio p){
        switch (tipo){
            case "AL": return new PolinomioAL(p);
            case "LL": return new PolinomioLL(p);
            case "Map": return new PolinomioMap(p);
            case "Set": return new PolinomioSet(p);
            case "Vector": return new PolinomioVector(p);
            default: return new PolinomioConcatenato(p);
        }
    }

    public static void main(String[] args) {
        PolinomioGUI p = new PolinomioGUI();
        p.setVisible(true);
    }
}