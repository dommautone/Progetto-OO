package model;

/**
 * The type Appartiene.
 */
public class Appartiene {
    /**
     * The Calciatore.
     */
    Calciatore calciatore;
    /**
     * The Nazionalita.
     */
    Nazionalita nazionalita;

    /**
     * Instantiates a new Appartiene.
     *
     * @param calciatore  the calciatore
     * @param nazionalita the nazionalità
     */
    public Appartiene(Calciatore calciatore, Nazionalita nazionalita) {
        this.calciatore = calciatore;
        this.nazionalita = nazionalita;
    }

    /**
     * Gets calciatore.
     *
     * @return the calciatore
     */
    public Calciatore getCalciatore() {return calciatore;}

    /**
     * Get nazionalità nazionalità.
     *
     * @return the nazionalità
     */
    public Nazionalita getNazionalita() {return nazionalita;}

    /**
     * Sets calciatore.
     *
     * @param calciatore the calciatore
     */
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}

    /**
     * Set nazionalità.
     *
     * @param nazionalita the nazionalità
     */
    public void setNazionalita(Nazionalita nazionalita) {this.nazionalita = nazionalita;}
}
