package model;

/**
 * La classe Appartiene contiene le informazioni di un calciatore e la sua nazionalità
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
     * Istanzia un nuovo appartiene.
     *
     * @param calciatore  il calciatore
     * @param nazionalita la nazionalità
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
