package progettom226a;

/*
*Autore:    Samuele Ambrosetti
*Progetto:  "Prato Fiorito"
*Modulo:    M226A
*/

//importazione
import java.util.Scanner;

public class ProgettoM226A {
    
    //colori
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String DRED = "\u001B[31;2m";
    public static final String DGREEN = "\033[32;2m";

    public static void main(String[] args) {
        
        //variabile che determina la fase del gioco (menu o partita)
        int faseGioco = 0;
        
        //variabili e matrici
        String nome = "";
        int punti = 0;
        int punteggio = 0;
        
        //audio
        Audio.play("music.wav");
        
        
        //mostra 100 giocatori e i punteggi
        Giocatore[] giocatori = new Giocatore[100];
        
        giocatori[0] = new Giocatore("Mario", 15);
        giocatori[1] = new Giocatore("Luigi", 32);
        giocatori[2] = new Giocatore("Yoshi", 26);
        
        int indiceGiocatori = 3;
        
        int densita = 1;
        int dimensione = 2;
        
        //variabile Running impostato
        boolean Running = true;
        
        //inizio gioco
        while(Running) { 
            
            // Creazione scanner per input
            Scanner scanner = new Scanner(System.in);
            
            if(faseGioco == 0){         //menu gioco
                                
                System.out.println(PURPLE + "Prato fiorito" + RESET);
                System.out.println("");
                System.out.println(YELLOW + "1 - Inizia" + RESET);
                System.out.println(YELLOW + "2 - Leaderboard" + RESET);
                System.out.println(YELLOW + "3 - Esci " + RESET);
                System.out.println("");
                
                int scelta = Integer.parseInt(scanner.nextLine());
                                
                if(scelta == 1){    //scelta 1
                    cls();
                    
                    //cambio fase gioco
                    faseGioco = 1;
                    
                }else if(scelta == 2){   //scelta 2
                    
                    cls();
                    
                    System.out.println(DGREEN + "Leaderboard:" + RESET);
                    System.out.println("");
                    
                    for(int i = 0; i < indiceGiocatori; i++) {
                        
                        System.out.println("Giocatore: " + BLUE + giocatori[i].getNome() + RESET + ", Punteggio: " + YELLOW + giocatori[i].getPunteggio() + RESET);
                        
                    }
                    
                    System.out.println("");
                    
                    attesa();
                    cls();
                    
                }else if(scelta == 3){  //scelta 3
                    
                    System.exit(0);
                    
                }else{
                    
                    cls();
                    System.out.println(RED + "Scelta non valida, inserire un numero da 1 a 4" + RESET);
                    
                }
                System.out.println("");
                
            }else if(faseGioco == 1){       //gioco
                
                //caratteristiche campo
                
                try{
                    
                    System.out.println("Inserire la dimensione del prato");
                    dimensione = Integer.parseInt(scanner.nextLine());
                    
                }catch(NumberFormatException e){
                    
                    System.out.println("");
                    System.out.println(RED + "Valore non valido, impostato" + RESET + " 2");
                    dimensione = 2;
                    attesa();
                    
                }                
                
                
                try{
                    
                    System.out.println("Inserire la quantita' di fiori");
                    densita = Integer.parseInt(scanner.nextLine());
                    
                }catch(NumberFormatException e){
                    
                    System.out.println("");
                    System.out.println(RED + "Valore non valido, impostato" + RESET + " 1");
                    densita = 1;
                    attesa();
                    
                }
                
                //azzeramento variabili per ogni partita
                punti = 0;
                punteggio = 0;
                int x = 0;
                int y = 0;
                
                boolean[][] controllato = new boolean[dimensione][dimensione];
                
                //popolazione
                
                for(int i = 0; i < dimensione; i++){
                        
                        for(int j = 0; j < dimensione; j++){
                            
                            controllato[i][j] = false;
                            
                        }
                        
                    }
                
                PratoFiorito partita = new PratoFiorito(dimensione, densita);
                
                boolean ciclo = true;
                
                //massimo punti
                
                cls();
                
                //ciclo gioco
                while(ciclo){
                    
                    partita.stampaInterfaccia();
                    
                    try{

                        System.out.println("Inserire riga");
                        x = Integer.parseInt(scanner.nextLine()) - 1;
                        
                        System.out.println("Inserire colonna");
                        y = Integer.parseInt(scanner.nextLine()) - 1;

                    }catch(NumberFormatException e){

                        System.out.println("");
                        System.out.println(RED + "Valore non valido, riprovare" + RESET);
                        attesa();
                        
                        x = -1;
                        y = -1;

                    }
                    
                    if(x < 0 || y < 0){
                        
                        //non fa niente se si ha un errore
                        
                    }else{
                        
                        cls();
                    
                        //verifica se già inserito e attribuzione punteggio
                        if(controllato[x][y] == false){

                            controllato[x][y] = true;

                            //tentativo
                            if(ciclo = partita.tentativo(x, y)){

                                ciclo = partita.tentativo(x, y);
                                punti++;
                                
                                //verifica vittoria
                                if (partita.verificaVittoria() == true) {
                                    faseGioco = 3;
                                    ciclo = false;
                                }

                            }else{
                                //perdita
                                partita.mostraSoluzione();
                                faseGioco = 2; 
                                ciclo = false;

                            }
                        }else{
                            cls();
                            System.out.println("Coordinate gia' inserite");
                            attesa();
                            
                        }
                    }
                }           
                
                
                
            }else if(faseGioco == 2){       //game over
                
                System.out.println("");
                System.out.println(RED + "Game over" + RESET);
                System.out.println("");
                
                faseGioco = 4;
                
            }else if(faseGioco == 3){       //vittoria
                
                System.out.println("");
                System.out.println(GREEN + "Vittoria" + RESET);
                System.out.println("");
                
                faseGioco = 4;
                
            }else if(faseGioco == 4){       //registrazione giocatore
                
                //calcolo punteggio
                punteggio = punti * densita;
                        
                //nome del giocatore
                System.out.println("Inserire il nome del giocatore");
                
                nome = scanner.nextLine();
                
                if(nome == ""){
                    nome = "Indefinito";
                }
                
                //creazione giocatore
                Giocatore g = new Giocatore(nome, punteggio);
                
                cls();
                
                //punteggio/i
                if(punteggio == 1){
                    
                    System.out.println("Il giocatore " + YELLOW + nome + RESET + " ha concluso la partita totalizzando " + YELLOW + punteggio + RESET + " punto");
                    
                }else{
                
                    System.out.println("Il giocatore " + YELLOW + nome + RESET + " ha concluso la partita totalizzando " + YELLOW + punteggio + RESET + " punti");
                
                }
                
                System.out.println(" ");
                
                attesa();
                
                giocatori[indiceGiocatori] = new Giocatore(nome, punteggio);
                        
                indiceGiocatori++;
                
                faseGioco = 0;
                cls();
                
            }
            
            
            
        }
        
    }
    
    /**
     * Questo metodo genera una attesa interrompibile dall'utente con enter
     */
    
    public static void attesa() {
        System.out.println(CYAN + "Premi INVIO per continuare..." + RESET);
        Scanner attesa = new Scanner(System.in);
        attesa.nextLine();
    }
    
    /**
     * Questo metodo simula la pulizia del terminale stampando righe vuote
     */
    
    public static void cls(){
        
        //simula una pulizia del terminale
        for(int i = 0; i < 20; i++){
            
            System.out.println(" ");
            
        }
        
    }
    
}