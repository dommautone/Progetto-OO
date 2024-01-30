package model;

/**
 * La classe Ruolo contiene le informazioni di un ruolo
 */
public class Ruolo {
    private String posizione;
    private String descrizione;

    /**
     * Istanzia un nuovo ruolo.
     *
     * @param posizione   la posizione
     * @param descrizione la descrizione
     */
    public Ruolo(String posizione, String descrizione){
        this.posizione=posizione;
        this.descrizione=descrizione;
    }

    /**
     * Get posizione string.
     *
     * @return the string
     */
    public String getPosizione(){return posizione;}

    /**
     * Get descrizione string.
     *
     * @return the string
     */
    public String getDescrizione(){return descrizione;}

    /**
     * Set posizione.
     *
     * @param posizione the posizione
     */
    public void setPosizione(String posizione){this.posizione=posizione;}

    /**
     * Set descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
