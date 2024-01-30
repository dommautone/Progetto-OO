package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Partecipa.
 */
public class Partecipa {
    /**
     * The Competizione.
     */
    Competizione competizione;
    /**
     * The Squadra.
     */
    Squadra squadra;
    /**
     * The Stagione.
     */
    int stagione;

    /**
     * Instantiates a new Partecipa.
     *
     * @param competizione the competizione
     * @param squadra      the squadra
     * @param stagione     the stagione
     */
    public Partecipa(Competizione competizione, Squadra squadra, int stagione) {
        this.competizione = competizione;
        this.squadra = squadra;
        this.stagione = stagione;
    }

    /**
     * Gets competizione.
     *
     * @return the competizione
     */
    public Competizione getCompetizione() {return competizione;}

    /**
     * Gets squadra.
     *
     * @return the squadra
     */
    public Squadra getSquadra() {return squadra;}

    /**
     * Gets stagione.
     *
     * @return the stagione
     */
    public int getStagione() {return stagione;}

    /**
     * Sets competizione.
     *
     * @param competizione the competizione
     */
    public void setCompetizione(Competizione competizione) {this.competizione = competizione;}

    /**
     * Sets squadra.
     *
     * @param squadra the squadra
     */
    public void setSquadra(Squadra squadra) {this.squadra = squadra;}

    /**
     * Sets stagione.
     *
     * @param stagione the stagione
     */
    public void setStagione(int stagione) {this.stagione = stagione;}

}
