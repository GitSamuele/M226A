package progettom226a;

/*
*Autore:    Samuele Ambrosetti
*Progetto:  "Prato Fiorito"
*Modulo:    M226A
*/

//importazione
import java.util.Random;

public class PratoFiorito {
    
    //colori
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String DRED = "\u001B[31;2m";
    public static final String DGREEN = "\033[32;2m";
    
    //Attributi e matrici
    private int dimensione;
    private int densita;
    //matrice per calcoli
    private boolean[][] campo;
    //Interfaccia dell'utente
    private boolean[][] interfaccia;
    
    //Getter e Setter
    
    
    /**
     * Metodo getter per dimensione
     * @return dimensione
     */
    
    public int getDimensione() {
        return dimensione;
    }
    
    /**
     * Metodo setter per dimensione
     * @param dimensione 
     */
    
    public void setDimensione(int dimensione) {
        
        //dimensione minima 2x2
        
        if(dimensione <= 1){
            this.dimensione = 2;
            System.out.println(RED + "Valore non valido, impostato su 2" + RESET);
        }else{
            this.dimensione = dimensione;
            System.out.println(GREEN + "Valore impostato correttamente" + RESET);
        }
        
    }
    
    
    /**
     * Metodo getter per densita
     * @return densita
     */
    
    public int getDensita() {
        return densita;
    }
    
    /**
     * Metodo setter per densita
     * @param densita 
     */
    
    public void setDensita(int densita) {
                
        //la quantità minima è di 1
        
        if(densita <= 0 || densita >= dimensione*dimensione){
            this.densita = 1;
            System.out.println(RED + "Valore non valido, impostato su 1" + RESET);
        }else{
            this.densita = densita;
            System.out.println(GREEN + "Valore impostato correttamente" + RESET);
        }
    }
    
    //matrici
    
    /**
     * Metodo getter per la matrice campo[][]
     * @return 
     */
    
    public boolean[][] getCampo() {
        return campo;
    }
    
    /**
     * Metodo getter per la matrice interfaccia[][]
     * @return 
     */
    
    public boolean[][] getInterfaccia() {
        return interfaccia;
    }
    
    /**
     * Costruttore gioco prato fiorito
     * @param dimensione
     * @param densita 
     */
    
    public PratoFiorito(int dimensione, int densita){
        
        //dimensione
        if(dimensione <= 1){
            this.dimensione = 2;
            System.out.println(RED + "Valore non valido, impostato su 2" + RESET);
        }else{
            this.dimensione = dimensione;
            System.out.println(GREEN + "Valore impostato correttamente" + RESET);
        }
        
        //quantità fiori
        if(densita <= 0 || densita >= dimensione*dimensione){
            this.densita = 1;
            System.out.println(RED + "Valore non valido, impostato su 1" + RESET);
        }else{
            this.densita = densita;
            System.out.println(GREEN + "Valore impostato correttamente" + RESET);
        }
        
        //creazione campi di gioco
        campo = new boolean[dimensione][dimensione];
        interfaccia = new boolean[dimensione][dimensione];
        
        creaCampo();
        creaInterfaccia();
        
    }    
    
    //Metodi
    
        
    /**
    * Questo metodo serve per la generazione del campo con spazi vuoti
    * (celle false)e fiori (celle true). 
    */
    
    private void creaCampo(){
        
        Random random1 = new Random();
        Random random2 = new Random();
        int fiori = 0;
        
        while(fiori < densita){
            
            int x = random1.nextInt(dimensione);
            int y = random2.nextInt(dimensione);
            
            //se è già occupata la cella semplicemente continua il ciclo fino al raggiungimento del valore deciso dall'utente
            if(!campo[x][y]){
                
                campo[x][y] = true;
                fiori++;
                
            }
            
        }
        
    }
    
    /**
    * Metodo che salva le scelte dell'utente, inizialmente popolato con celle
    * false, in seguito impostate su true le celle scoperte.
    */
    
    private void creaInterfaccia(){
        
        for(int i = 0; i < dimensione; i++){
            
            for(int j = 0; j < dimensione; j++){
                
                interfaccia[i][j] = false;
                
            }
            
        }
        
    }
    
    /**
     * Metodo che confronta la scelta dell'utente con le coordinate del campo,
     * se le coordinate corrispondono ad un fiore in campo[][] restituirà true,
     * ovvero la cella col fiore; altrimenti false se una cella vuota.
     * 
     * @param x
     * @param y
     * @return se la cella in campo[x][y] è 'true'
     */
    public boolean tentativo(int x, int y){
        
        if(campo[x][y]){
            
            return false;
            
        }else{
            
            interfaccia[x][y] = true;
            return true;
            
        }
        
    }
    
    /**
    * Questo metodo stampa l'interfaccia con la quale l'utente giocherà
    */
    
    public void stampaInterfaccia(){
        
        for(int i = 0; i < dimensione; i++){
            
            String riga = "";
            
            for(int j = 0; j < dimensione; j++){
                
                if(interfaccia[i][j] == false){
                    
                    riga += GREEN + "# " + RESET;   //se incognita
                    
                }else if(interfaccia[i][j] == true){                    //se è scoperto controllare se è un fiore
                    
                    if(campo[i][j] == true){            //se in "campo[x][y]" si trova una cella con "true" significa che si trova un fiore
                        
                        riga += DRED + "x " + RESET;       //se è un fiore
                        
                    }else{
                        
                        riga += BLUE + "o " + RESET;   //se la coordinata è pulita
                        
                    }
                                        
                }
                
            }
            
            System.out.println(riga);
            
        }
        
    }
    
    /**
    * Questo metodo mostra la soluzione (celle tutte scoperte) al momento 
    * della perdita
    */
    
    public void mostraSoluzione(){
        
        System.out.println();
        System.out.println(PURPLE + "Soluzione:" + RESET);
        System.out.println();
        
        for(int i = 0; i < dimensione; i++){
            
            String riga = "";
            
            for(int j = 0; j < dimensione; j++){
                
                if(campo[i][j] == false){
                    
                    riga += BLUE + "o " + RESET;
                    
                }else if(campo[i][j] == true){                    //se è scoperto controllare se è un fiore
                    
                    riga += DRED + "x " + RESET;
                                        
                }
                
            }
            
            System.out.println(riga);
            
        }
        
    }
    
    /**
     * Questo metodo si occupa di verificare la vittoria dell'utente
     * comparando le celle scoperte dall'utente (interfaccia[][]) 
     * con quelle del campo (campo[][]); la vittoria avviene quando il conteggio 
     * delle celle scoperte dall'utente è uguale a quello del campo generato.
     * 
     * @return se contaVuotiI = contaVuotiC
     */
    
    public boolean verificaVittoria() {
        
        int contaVuotiI = 0;
        int contaVuotiC = 0;
        
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                
                if(interfaccia[i][j]){
                    contaVuotiI++;
                }
                
                if(!campo[i][j]){
                    contaVuotiC++;
                }
                
            }
        }
        
        if(contaVuotiI == contaVuotiC){
            return true;
        }else{
            return false;
        }
    }
    
}
