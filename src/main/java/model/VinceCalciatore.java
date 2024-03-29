package model;

/**
 * La classe VinceCalciatore contiene le informazioni di un calciatore che vince un trofeo
 */
public class VinceCalciatore {
    /**
     * The Calciatore.
     */
    Calciatore calciatore;
    /**
     * The Trofeo calciatore.
     */
    TrofeoCalciatore trofeoCalciatore;
    /**
     * The Stagione.
     */
    int stagione;

    /**
     * Istanzia un nuovo vince calciatore.
     *
     * @param calciatore       il calciatore
     * @param trofeoCalciatore il trofeo calciatore
     * @param stagione         la stagione
     */
    public VinceCalciatore(Calciatore calciatore, TrofeoCalciatore trofeoCalciatore, int stagione) {
        this.calciatore = calciatore;
        this.trofeoCalciatore = trofeoCalciatore;
        this.stagione = stagione;
    }

    /**
     * Gets calciatore.
     *
     * @return the calciatore
     */
    public Calciatore getCalciatore() {return calciatore;}

    /**
     * Gets trofeo calciatore.
     *
     * @return the trofeo calciatore
     */
    public TrofeoCalciatore getTrofeoCalciatore() {return trofeoCalciatore;}

    /**
     * Gets stagione.
     *
     * @return the stagione
     */
    public int getStagione() {return stagione;}

    /**
     * Sets calciatore.
     *
     * @param calciatore the calciatore
     */
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}

    /**
     * Sets trofeo calciatore.
     *
     * @param trofeoCalciatore the trofeo calciatore
     */
    public void setTrofeoCalciatore(TrofeoCalciatore trofeoCalciatore) {this.trofeoCalciatore = trofeoCalciatore;}

    /**
     * Sets stagione.
     *
     * @param stagione the stagione
     */
    public void setStagione(int stagione) {this.stagione = stagione;}

}