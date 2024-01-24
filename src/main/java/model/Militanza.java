package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Militanza {
    LocalDate dataInizio;
    LocalDate dataFine;
    int partiteGiocate;
    int golSegnati;
    int golSubiti;
    Calciatore calciatore;
    Squadra squadra;

    public Militanza(LocalDate dataInizio, LocalDate dataFine, int partiteGiocate, int golSegnati, Integer golSubiti, Calciatore calciatore, Squadra squadra) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.partiteGiocate = partiteGiocate;
        this.golSegnati = golSegnati;
        this.golSubiti = golSubiti;
        this.calciatore = calciatore;
        this.squadra = squadra;
    }

    public Militanza(Calciatore calciatore, Squadra squadra, LocalDate dataInizio, LocalDate dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.calciatore = calciatore;
        this.squadra = squadra;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }
    public LocalDate getDataFine() {
        return dataFine;
    }
    public int getPartiteGiocate() {
        return partiteGiocate;
    }
    public int getGolSegnati() {
        return golSegnati;
    }
    public int getGolSubiti() {
        return golSubiti;
    }
    public Calciatore getCalciatore() {
        return calciatore;
    }
    public Squadra getSquadra() {
        return squadra;
    }

    public void setPartiteGiocate(int partiteGiocate) {this.partiteGiocate = partiteGiocate;}
    public void setGolSegnati(int golSegnati) {this.golSegnati = golSegnati;}
    public void setGolSubiti(Integer golSubiti) {this.golSubiti = golSubiti;}
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}
    public void setSquadra(Squadra squadra) {this.squadra = squadra;}
    public void setDataInizio(LocalDate dataInizio) {this.dataInizio = dataInizio;}
    public void setDataFine(LocalDate dataFine) {this.dataFine = dataFine;}
}
