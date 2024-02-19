package progettom226a;

/*
*Autore:    Samuele Ambrosetti
*Progetto:  "Prato Fiorito"
*Modulo:    M226A
*/

public class Giocatore {
    
    //Attributi
    private String nome;
    private int punteggio;
    
    //Getter e Setter
    
    /**
     * Metodo getter per nome
     * @return nome
     */

    public String getNome() {
        return nome;
    }
    
    /**
     * Metodo setter per nome
     * @param nome 
     */

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Metodo getter per punteggio
     * @return punteggio
     */
    
    public int getPunteggio() {
        return punteggio;
    }
    
    /**
     * Metodo setter per punteggio
     * @param punteggio 
     */

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
    
    /**
     * Costruttore di giocatore
     * @param nome
     * @param punteggio 
     */
    
    public Giocatore(String nome, int punteggio){
    
        if(nome.isBlank() || nome.length() >= 10){
            
            this.nome = "Indefinito";
            
        }else{
        
            this.nome = nome;
            
        }
        
        this.punteggio = punteggio;
    
    }    
    
}
