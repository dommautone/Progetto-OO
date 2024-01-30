package controller;

import DAO.ImplementazioneDAO;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 La classe Controller rappresentea il controller del programma
 */
public class Controller {
    private Amministratore amministratore;
    private ImplementazioneDAO implementazionePostgresDAO;

    /**
     * Istanzia un nuovo Controller.
     *
     * @param implementazionePostgresDAO è il DAO che si occupa di interfacciarsi con il database
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
     * @param username l'username
     * @param password la password
     * @return the amministratore
     * @throws PasswordCortaException password corta exception
     * @throws UsernameCortoException username corto exception
     * @throws AlreadyExistsExeption  already exists exeption
     */
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

    /**
     * Login.
     *
     * @param username l' username
     * @param password la password
     * @throws DatiNonValidiExeption       dati non validi exeption
     * @throws UtenteNonRegistratoExeption utente non registrato exeption
     * @throws PasswordNonValidaExeption   password non valida exeption
     */
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
    public ArrayList<Nazionalita> getNazionalità(){return implementazionePostgresDAO.getNazionalità();}

    /**
     * Get squadre array list.
     *
     * @return the array list
     */
    public ArrayList<Squadra> getSquadre(){return implementazionePostgresDAO.getSquadre();}

    /**
     * Get calciatori default table model.
     *
     * @param nome        il nome
     * @param cognome     il cognome
     * @param sesso       il sesso
     * @param squadra     la squadra
     * @param nazionalità la nazionalità
     * @param piede       il piede
     * @param età         l' età
     * @param ruolo       il ruolo
     * @param golFatti    i gol fatti
     * @param golSubiti   i gol subiti
     * @param dataRitiro  la data ritiro
     * @return the default table model
     */
    public DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                           String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro){
        return implementazionePostgresDAO.getCalciatori(nome, cognome, sesso, squadra, nazionalità, piede, età, ruolo, golFatti, golSubiti, dataRitiro);
    }

    /**
     * Aggiungi calciatore.
     *
     * @param nome        il nome
     * @param cognome     il cognome
     * @param sesso       il sesso
     * @param squadra     la squadra
     * @param nazionalità la nazionalità
     * @param piede       il piede
     * @param dataNascita la data nascita
     * @param ruolo       il ruolo
     * @param dataRitiro  la data ritiro
     * @param dataInizio  la data inizio
     * @param dataFine    la data fine
     */
    public void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                                   String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                                   LocalDate dataFine) throws CategoriaNonCorrispondeException {

        piede = piede.toLowerCase();
        nome = nome.trim(); //Rimuove gli spazi all'inizio e alla fine
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase(); //Mette la prima lettera maiuscola e le altre minuscole
        cognome = cognome.trim();
        cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();
        try {
            implementazionePostgresDAO.aggiungiCalciatore(nome, cognome, sesso, squadra, nazionalità, piede, dataNascita, ruolo, dataRitiro, dataInizio, dataFine);
        } catch (Exception e) {
            throw new CategoriaNonCorrispondeException();
        }
    }

    /**
     * Modifica calciatore.
     *
     * @param idCalciatore l' id calciatore
     * @param idSquadra    l' id squadra
     * @param nome         il nome
     * @param cognome      il cognome
     * @param piede        il piede
     * @param sesso        il sesso
     * @param dataNascita  la data nascita
     * @param dataRitiro   la data ritiro
     * @param golFatti     i gol fatti
     * @param golSubiti    i gol subiti
     * @param squadra      la squadra
     */
    public void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro,
                                   int golFatti, Integer golSubiti, String squadra){
        implementazionePostgresDAO.modificaCalciatore(idCalciatore, idSquadra, nome, cognome, piede, sesso, dataNascita, dataRitiro, golFatti, golSubiti, squadra);
    }

    /**
     * Visualizza ruolo calciatore array list.
     *
     * @param idCalciatore l' id calciatore
     * @return the array list
     */
    public ArrayList<Ha> visualizzaRuoloCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaRuoloCalciatore(idCalciatore);
    }

    /**
     * Inserisci ruolo.
     *
     * @param idCalciatore l' id calciatore
     * @param ruolo        il ruolo
     * @throws RuoloGiàInseritoException il ruolo già inserito exception
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
     * @param idCalciatore l' id calciatore
     * @param ruolo        il ruolo
     */
    public void eliminaRuolo (int idCalciatore, ArrayList<String> ruolo){
        implementazionePostgresDAO.eliminaRuolo(idCalciatore, ruolo);
    }

    /**
     * Visualizza nazionalità calciatore array list.
     *
     * @param idCalciatore l' id calciatore
     * @return the array list
     */
    public ArrayList<Appartiene> visualizzaNazionalitàCalciatore(int idCalciatore){
        return implementazionePostgresDAO.visualizzaNazionalitàCalciatore(idCalciatore);
    }

    /**
     * Inserisci nazionalità.
     *
     * @param idCalciatore l' id calciatore
     * @param nazionalità  la nazionalità
     * @throws NazionalitàGiàInseritaException la nazionalità già inserita exception
     */
    public void inserisciNazionalità (int idCalciatore, String nazionalità) throws NazionalitàGiàInseritaException{
        try{
            implementazionePostgresDAO.inserisciNazionalità(idCalciatore, nazionalità);
        } catch (Exception e) {
            throw new NazionalitàGiàInseritaException();
        }
    }

    /**
     * Elimina nazionalità.
     *
     * @param idCalciatore l' id calciatore
     * @param nazionalità  la nazionalità
     */
    public void eliminaNazionalità (int idCalciatore, ArrayList<String> nazionalità){
        implementazionePostgresDAO.eliminaNazionalità(idCalciatore, nazionalità);
    }

    /**
     * Elimina calciatore.
     *
     * @param idCalciatore l' id calciatore
     */
    public void eliminaCalciatore(ArrayList<Integer> idCalciatore){
        implementazionePostgresDAO.eliminaCalciatore(idCalciatore);
    }

}

