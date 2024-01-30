package controller;

import dao.ImplementazioneDAO;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Controller.
 */
public class Controller {
    private Amministratore amministratore;
    private ImplementazioneDAO implementazionePostgresDAO;

    /**
     * Instantiates a new Controller.
     *
     * @param implementazionePostgresDAO the implementazione postgres dao
     */
    public Controller(ImplementazioneDAO implementazionePostgresDAO) {
        this.implementazionePostgresDAO = implementazionePostgresDAO;
    }

    /**
     * Sets schema.
     */
    public void setSchema() {
        implementazionePostgresDAO.setSchema();
    }

    /**
     * Registrazione amministratore.
     *
     * @param username the username
     * @param password the password
     * @return the amministratore
     * @throws PasswordCortaException the password corta exception
     * @throws UsernameCortoException the username corto exception
     * @throws AlreadyExistsExeption  the already exists exeption
     */
    public Amministratore registrazione(String username, String password) throws PasswordCortaException,
            UsernameCortoException, AlreadyExistsExeption{
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

    /**
     * Login.
     *
     * @param username the username
     * @param password the password
     * @throws DatiNonValidiExeption       the dati non validi exeption
     * @throws UtenteNonRegistratoExeption the utente non registrato exeption
     * @throws PasswordNonValidaExeption   the password non valida exeption
     */
    public void login(String username, String password) throws DatiNonValidiExeption, UtenteNonRegistratoExeption,
            PasswordNonValidaExeption {
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

    /**
     * Get ruoli array list.
     *
     * @return the array list
     */
    public ArrayList<Ruolo> getRuoli(){return implementazionePostgresDAO.getRuoli();}

    /**
     * Get nazionalità array list.
     *
     * @return the array list
     */
    public ArrayList<Nazionalita> getNazionalita(){return implementazionePostgresDAO.getNazionalita();}

    /**
     * Get squadre array list.
     *
     * @return the array list
     */
    public ArrayList<Squadra> getSquadre(){return implementazionePostgresDAO.getSquadre();}

    /**
     * Get calciatori default table model.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalita the nazionalita
     * @param piede       the piede
     * @param eta         the eta
     * @param ruolo       the ruolo
     * @param golFatti    the gol fatti
     * @param golSubiti   the gol subiti
     * @param dataRitiro  the data ritiro
     * @return the default table model
     */
    public DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalita,
                                           String piede, Integer eta, String ruolo, Integer golFatti, Integer golSubiti,
                                           LocalDate dataRitiro){
        return implementazionePostgresDAO.getCalciatori(nome, cognome, sesso, squadra, nazionalita, piede, eta, ruolo,
                golFatti, golSubiti, dataRitiro);
    }

    /**
     * Aggiungi calciatore.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalita the nazionalita
     * @param piede       the piede
     * @param dataNascita the data nascita
     * @param ruolo       the ruolo
     * @param dataRitiro  the data ritiro
     * @param dataInizio  the data inizio
     * @param dataFine    the data fine
     */
    public void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalita,
                                   String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro,
                                   LocalDate dataInizio, LocalDate dataFine) throws CategoriaNonCorrispondeException {

        piede = piede.toLowerCase();
        nome = nome.trim(); //Rimuove gli spazi all'inizio e alla fine
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase(); //Mette la prima lettera maiuscola e le altre minuscole
        cognome = cognome.trim();
        cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();
        try {
            implementazionePostgresDAO.aggiungiCalciatore(nome, cognome, sesso, squadra, nazionalita, piede, dataNascita,
                    ruolo, dataRitiro, dataInizio, dataFine);
        } catch (Exception e) {
            throw new CategoriaNonCorrispondeException();
        }
    }

    /**
     * Modifica calciatore.
     *
     * @param idCalciatore the id calciatore
     * @param idSquadra    the id squadra
     * @param nome         the nome
     * @param cognome      the cognome
     * @param piede        the piede
     * @param sesso        the sesso
     * @param dataNascita  the data nascita
     * @param dataRitiro   the data ritiro
     * @param golFatti     the gol fatti
     * @param golSubiti    the gol subiti
     * @param squadra      the squadra
     */
    public void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso,
                                   LocalDate dataNascita, LocalDate dataRitiro, int golFatti, Integer golSubiti,
                                   String squadra){
        implementazionePostgresDAO.modificaCalciatore(idCalciatore, idSquadra, nome, cognome, piede, sesso, dataNascita,
                dataRitiro, golFatti, golSubiti, squadra);
    }

    /**
     * Visualizza ruolo calciatore array list.
     *
     * @param idCalciatore the id calciatore
     * @return the array list
     */
    public ArrayList<Ha> visualizzaRuoloCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaRuoloCalciatore(idCalciatore);
    }

    /**
     * Inserisci ruolo.
     *
     * @param idCalciatore the id calciatore
     * @param ruolo        the ruolo
     * @throws RuoloGiàInseritoException the ruolo già inserito exception
     */
    public void inserisciRuolo (int idCalciatore, String ruolo) throws RuoloGiàInseritoException{
        try {
            implementazionePostgresDAO.inserisciRuolo(idCalciatore, ruolo);
        } catch (Exception e) {
            throw new RuoloGiàInseritoException();
        }
    }

    /**
     * Elimina ruolo.
     *
     * @param idCalciatore the id calciatore
     * @param ruolo        the ruolo
     */
    public void eliminaRuolo (int idCalciatore, ArrayList<String> ruolo){
        implementazionePostgresDAO.eliminaRuolo(idCalciatore, ruolo);
    }

    /**
     * Visualizza nazionalità calciatore array list.
     *
     * @param idCalciatore the id calciatore
     * @return the array list
     */
    public ArrayList<Appartiene> visualizzaNazionalitaCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaNazionalitaCalciatore(idCalciatore);
    }

    /**
     * Inserisci nazionalita.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalita  the nazionalita
     * @throws NazionalitàGiàInseritaException the nazionalita già inserita exception
     */
    public void inserisciNazionalita(int idCalciatore, String nazionalita) throws NazionalitàGiàInseritaException{
        try{
            implementazionePostgresDAO.inserisciNazionalita(idCalciatore, nazionalita);
        } catch (Exception e) {
            throw new NazionalitàGiàInseritaException();
        }
    }

    /**
     * Elimina nazionalita.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalita  the nazionalita
     */
    public void eliminaNazionalita(int idCalciatore, ArrayList<String> nazionalita){
        implementazionePostgresDAO.eliminaNazionalita(idCalciatore, nazionalita);
    }

    /**
     * Elimina calciatore.
     *
     * @param idCalciatore the id calciatore
     */
    public void eliminaCalciatore(ArrayList<Integer> idCalciatore){
        implementazionePostgresDAO.eliminaCalciatore(idCalciatore);
    }

}

