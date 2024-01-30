package model;

/**
 * La classe Vince squadra rappresenta la classe che descrive quando una squadra vince un trofeo.
 */
public class VinceSquadra {
    /**
     * The Squadra.
     */
    Squadra squadra;
    /**
     * The Trofeo competizione.
     */
    TrofeoCompetizione trofeoCompetizione;
    /**
     * The Stagione.
     */
    int stagione;

    /**
     * Instantiates a new Vince squadra.
     *
     * @param squadra            the squadra
     * @param trofeoCompetizione the trofeo competizione
     * @param stagione           the stagione
     */
    public VinceSquadra(Squadra squadra, TrofeoCompetizione trofeoCompetizione, int stagione) {
        this.squadra = squadra;
        this.trofeoCompetizione = trofeoCompetizione;
        this.stagione = stagione;
    }

    /**
     * Gets squadra.
     *
     * @return the squadra
     */
    public Squadra getSquadra() {return squadra;}

    /**
     * Gets trofeo competizione.
     *
     * @return the trofeo competizione
     */
    public TrofeoCompetizione getTrofeoCompetizione() {return trofeoCompetizione;}

    /**
     * Gets stagione.
     *
     * @return the stagione
     */
    public int getStagione() {return stagione;}

    /**
     * Sets squadra.
     *
     * @param squadra the squadra
     */
    public void setSquadra(Squadra squadra) {this.squadra = squadra;}

    /**
     * Sets trofeo competizione.
     *
     * @param trofeoCompetizione the trofeo competizione
     */
    public void setTrofeoCompetizione(TrofeoCompetizione trofeoCompetizione) {this.trofeoCompetizione = trofeoCompetizione;}

    /**
     * Sets stagione.
     *
     * @param stagione the stagione
     */
    public void setStagione(int stagione) {this.stagione = stagione;}

}

