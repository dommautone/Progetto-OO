package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VinceSquadra {
    Squadra squadra;
    TrofeoCompetizione trofeoCompetizione;
    LocalDate stagione;

    public VinceSquadra(Squadra squadra, TrofeoCompetizione trofeoCompetizione, String stagione) {
        this.squadra = squadra;
        this.trofeoCompetizione = trofeoCompetizione;
        setStagione(stagione);
    }

    public Squadra getSquadra() {return squadra;}
    public TrofeoCompetizione getTrofeoCompetizione() {return trofeoCompetizione;}
    public LocalDate getStagione() {return stagione;}

    public void setSquadra(Squadra squadra) {this.squadra = squadra;}
    public void setTrofeoCompetizione(TrofeoCompetizione trofeoCompetizione) {this.trofeoCompetizione = trofeoCompetizione;}
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

