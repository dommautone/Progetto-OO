package controller;

import DAO.ImplementazioneDAO;
import model.*;

import java.util.ArrayList;

public class Controller {
    private Amministratore amministratore;
    private Calciatore calciatore;
    private ImplementazioneDAO implementazionePostgresDAO;

    public Controller(ImplementazioneDAO implementazionePostgresDAO) {
        this.implementazionePostgresDAO = implementazionePostgresDAO;
    }

    public Amministratore registrazione(String username, String password) throws AlreadyExistsExeption {
        amministratore = new Amministratore(username, password);
        try {
            implementazionePostgresDAO.registrazione(username, password);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return amministratore;
    }

    public void setSchema() {implementazionePostgresDAO.setSchema();}

    public Calciatore setCalciatore(String nome, String cognome, String piede, char sesso, String DataNascita, String DataRitiro) throws AlreadyExistsExeption {
        calciatore = new Calciatore(nome, cognome, piede, sesso, DataNascita, DataRitiro);
        try{
            implementazionePostgresDAO.setCalciatore(nome, cognome, piede, sesso, DataNascita, DataRitiro);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return calciatore;
    }

    public Ha setRuolo(Calciatore calciatore, Ruolo ruolo) throws AlreadyExistsExeption {
        Ha ha = new Ha(calciatore, ruolo);
        try{
            implementazionePostgresDAO.setRuolo(calciatore, ruolo);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return ha;
    }

    public Militanza setSquadra(Calciatore calciatore, Squadra squadra, String dataInizio, String dataFine) throws AlreadyExistsExeption {
        Militanza militanza = new Militanza(calciatore, squadra, dataInizio, dataFine);
        try{
            implementazionePostgresDAO.setSquadra(calciatore, squadra, dataInizio, dataFine);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return militanza;
    }

    public Appartiene setNazionalita(Calciatore calciatore, Nazionalità nazionalita) throws AlreadyExistsExeption {
        Appartiene appartiene = new Appartiene(calciatore, nazionalita);
        try{
            implementazionePostgresDAO.setNazionalita(calciatore, nazionalita);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return appartiene;
    }

    public Possiede setFeature(Calciatore calciatore, Feature feature) throws AlreadyExistsExeption {
        Possiede possiede = new Possiede(calciatore, feature);
        try{
            implementazionePostgresDAO.setFeature(calciatore, feature);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return possiede;
    }

    public ArrayList<Ruolo> getRuoli() {
        return implementazionePostgresDAO.getRuoli();
    }
}
