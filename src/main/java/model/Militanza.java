package model;

import java.time.LocalDate;

/**
 * La classe Militanza indica il periodo di un determinato calciatore che ha fatto parte di una determinata squadra con le sue informazioni.
 */
public class Militanza {
    /**
     * The Data inizio.
     */
    LocalDate dataInizio;
    /**
     * The Data fine.
     */
    LocalDate dataFine;
    /**
     * The Partite giocate.
     */
    int partiteGiocate;
    /**
     * The Gol segnati.
     */
    int golSegnati;
    /**
     * The Gol subiti.
     */
    int golSubiti;
    /**
     * The Calciatore.
     */
    Calciatore calciatore;
    /**
     * The Squadra.
     */
    Squadra squadra;

    /**
     * Instantiates a new Militanza.
     *
     * @param dataInizio     the data inizio
     * @param dataFine       the data fine
     * @param partiteGiocate the partite giocate
     * @param golSegnati     the gol segnati
     * @param golSubiti      the gol subiti
     * @param calciatore     the calciatore
     * @param squadra        the squadra
     */
    public Militanza(LocalDate dataInizio, LocalDate dataFine, int partiteGiocate, int golSegnati, Integer golSubiti, Calciatore calciatore, Squadra squadra) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.partiteGiocate = partiteGiocate;
        this.golSegnati = golSegnati;
        this.golSubiti = golSubiti;
        this.calciatore = calciatore;
        this.squadra = squadra;
    }

    /**
     * Gets data inizio.
     *
     * @return the data inizio
     */
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    /**
     * Gets data fine.
     *
     * @return the data fine
     */
    public LocalDate getDataFine() {
        return dataFine;
    }

    /**
     * Gets partite giocate.
     *
     * @return the partite giocate
     */
    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    /**
     * Gets gol segnati.
     *
     * @return the gol segnati
     */
    public int getGolSegnati() {
        return golSegnati;
    }

    /**
     * Gets gol subiti.
     *
     * @return the gol subiti
     */
    public int getGolSubiti() {
        return golSubiti;
    }

    /**
     * Gets calciatore.
     *
     * @return the calciatore
     */
    public Calciatore getCalciatore() {
        return calciatore;
    }

    /**
     * Gets squadra.
     *
     * @return the squadra
     */
    public Squadra getSquadra() {
        return squadra;
    }

    /**
     * Sets partite giocate.
     *
     * @param partiteGiocate the partite giocate
     */
    public void setPartiteGiocate(int partiteGiocate) {this.partiteGiocate = partiteGiocate;}

    /**
     * Sets gol segnati.
     *
     * @param golSegnati the gol segnati
     */
    public void setGolSegnati(int golSegnati) {this.golSegnati = golSegnati;}

    /**
     * Sets gol subiti.
     *
     * @param golSubiti the gol subiti
     */
    public void setGolSubiti(Integer golSubiti) {this.golSubiti = golSubiti;}

    /**
     * Sets calciatore.
     *
     * @param calciatore the calciatore
     */
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}

    /**
     * Sets squadra.
     *
     * @param squadra the squadra
     */
    public void setSquadra(Squadra squadra) {this.squadra = squadra;}

    /**
     * Sets data inizio.
     *
     * @param dataInizio the data inizio
     */
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}

    /**
     * Sets data fine.
     *
     * @param dataFine the data fine
     */
    public void setDataFine(LocalDate dataFine) {this.dataFine = dataFine;}
}
