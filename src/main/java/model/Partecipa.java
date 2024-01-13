package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Partecipa {
    Competizione competizione;
    Squadra squadra;
    LocalDate stagione;

    public Partecipa(Competizione competizione, Squadra squadra, String stagione) {
        this.competizione = competizione;
        this.squadra = squadra;
        setStagione(stagione);
    }

    public Competizione getCompetizione() {return competizione;}
    public Squadra getSquadra() {return squadra;}
    public LocalDate getStagione() {return stagione;}

    public void setCompetizione(Competizione competizione) {this.competizione = competizione;}
    public void setSquadra(Squadra squadra) {this.squadra = squadra;}

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

    public void setStagione(String stagione) {this.stagione = controlloData(stagione);}

}
