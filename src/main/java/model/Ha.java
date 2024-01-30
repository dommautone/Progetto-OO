package model;

/**
 * La classe Ha contiene le informazioni di un calciatore e il suo ruolo
 */
public class Ha {
    /**
     * The Calciatore.
     */
    Calciatore calciatore;
    /**
     * The Ruolo.
     */
    Ruolo ruolo;

    /**
     * Istanzia un nuovo ha.
     *
     * @param calciatore il calciatore
     * @param ruolo      il ruolo
     */
    public Ha(Calciatore calciatore, Ruolo ruolo) {
        this.calciatore = calciatore;
        this.ruolo = ruolo;
    }

    /**
     * Gets calciatore.
     *
     * @return the calciatore
     */
    public Calciatore getCalciatore() {return calciatore;}

    /**
     * Gets ruolo.
     *
     * @return the ruolo
     */
    public Ruolo getRuolo() {return ruolo;}

    /**
     * Sets calciatore.
     *
     * @param calciatore the calciatore
     */
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}

    /**
     * Sets ruolo.
     *
     * @param ruolo the ruolo
     */
    public void setRuolo(Ruolo ruolo) {this.ruolo = ruolo;}
}
