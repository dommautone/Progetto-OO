package model;

/**
 * The type Ruolo.
 */
public class Ruolo {
    private String posizione;
    private String descrizione;

    /**
     * Instantiates a new Ruolo.
     *
     * @param posizione   the posizione
     * @param descrizione the descrizione
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
