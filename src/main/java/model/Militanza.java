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

    public Militanza(String dataInizio, String dataFine, int partiteGiocate, int golSegnati, int golSubiti, Calciatore calciatore, Squadra squadra) {
        setDataInizio(dataInizio);
        setDataFine(dataFine);
        this.partiteGiocate = partiteGiocate;
        this.golSegnati = golSegnati;
        this.golSubiti = golSubiti;
        this.calciatore = calciatore;
        this.squadra = squadra;
    }

    public Militanza(Calciatore calciatore, Squadra squadra, String dataInizio, String dataFine) {
        setDataInizio(dataInizio);
        setDataFine(dataFine);
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

    public LocalDate controlloData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(data, formatter);

            int giorno = parsedDate.getDayOfMonth();
            int mese = parsedDate.getMonthValue();

            if ((mese == 4 || mese == 6 || mese == 9 || mese == 11) && giorno > 30) {
                throw new IllegalArgumentException("Il giorno non può superare 30 per questo mese.");
            } else if (mese == 2 && giorno > 29) {
                throw new IllegalArgumentException("Il giorno non può superare 29 per Febbraio.");
            } else if (giorno > 31) {
                throw new IllegalArgumentException("Il giorno non può superare 31 per questo mese.");
            }

            return parsedDate;

        } catch (DateTimeException | IllegalArgumentException e) {
            System.out.println("Errore nella data di nascita: " + e.getMessage());
            return null;
        }
    }

    public void setDataInizio(String dataInizio) {this.dataInizio = controlloData(dataInizio);}
    public void setDataFine(String dataFine) {this.dataFine = controlloData(dataFine);}
    public void setPartiteGiocate(int partiteGiocate) {this.partiteGiocate = partiteGiocate;}
    public void setGolSegnati(int golSegnati) {this.golSegnati = golSegnati;}
    public void setGolSubiti(int golSubiti) {
    }
}
