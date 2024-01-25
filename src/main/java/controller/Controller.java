package controller;

import DAO.ImplementazioneDAO;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Amministratore amministratore;
    private ImplementazioneDAO implementazionePostgresDAO;

    public Controller(ImplementazioneDAO implementazionePostgresDAO) {
        this.implementazionePostgresDAO = implementazionePostgresDAO;
    }

    public void setSchema() {
        implementazionePostgresDAO.setSchema();
    }

    public Amministratore registrazione(String username, String password) throws PasswordCortaException, UsernameCortoException, AlreadyExistsExeption{
         if (password.length() < 8)
            throw new PasswordCortaException();
        else if (username.length() < 4)
            throw new UsernameCortoException();
        amministratore = new Amministratore(username, password);

        try{
            implementazionePostgresDAO.registrazione(username, password);
        } catch (Exception e) {
            throw new AlreadyExistsExeption();
        }
        return amministratore;
    }

    public void login(String username, String password) throws DatiNonValidiExeption, UtenteNonRegistratoExeption, PasswordNonValidaExeption {
        if(username.length() < 4 || password.length() < 8)
            throw new DatiNonValidiExeption();
        try {
            amministratore = implementazionePostgresDAO.login(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(amministratore == null)
            throw new UtenteNonRegistratoExeption();
        else if(!amministratore.getPassword().equals(password))
            throw new PasswordNonValidaExeption();
    }

    public ArrayList<Ruolo> getRuoli(){return implementazionePostgresDAO.getRuoli();}
    public ArrayList<Nazionalità> getNazionalità(){return implementazionePostgresDAO.getNazionalità();}
    public ArrayList<Squadra> getSquadre(){return implementazionePostgresDAO.getSquadre();}
    public DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                           String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro){
        return implementazionePostgresDAO.getCalciatori(nome, cognome, sesso, squadra, nazionalità, piede, età, ruolo, golFatti, golSubiti, dataRitiro);
    }
    public void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                                   String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                                   LocalDate dataFine){
        implementazionePostgresDAO.aggiungiCalciatore(nome, cognome, sesso, squadra, nazionalità, piede, dataNascita, ruolo, dataRitiro, dataInizio, dataFine);
    }
    public void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro,
                                   int golFatti, Integer golSubiti, String squadra){
        implementazionePostgresDAO.modificaCalciatore(idCalciatore, idSquadra, nome, cognome, piede, sesso, dataNascita, dataRitiro, golFatti, golSubiti, squadra);
    }
    public ArrayList<Ha> visualizzaRuoloCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaRuoloCalciatore(idCalciatore);
    }
    public void inserisciRuolo (int idCalciatore, String ruolo){
        implementazionePostgresDAO.inserisciRuolo(idCalciatore, ruolo);
    }
    public void eliminaRuolo (int idCalciatore, ArrayList<String> ruolo){
        implementazionePostgresDAO.eliminaRuolo(idCalciatore, ruolo);
    }
    public ArrayList<Appartiene> visualizzaNazionalitàCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaNazionalitàCalciatore(idCalciatore);
    }
    public void inserisciNazionalità (int idCalciatore, String nazionalità){
        implementazionePostgresDAO.inserisciNazionalità(idCalciatore, nazionalità);
    }
    public void eliminaNazionalità (int idCalciatore, ArrayList<String> nazionalità){
        implementazionePostgresDAO.eliminaNazionalità(idCalciatore, nazionalità);
    }

}

