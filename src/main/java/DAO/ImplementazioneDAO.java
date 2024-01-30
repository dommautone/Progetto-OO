package DAO;

import model.*;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The interface Implementazione dao.
 */
public interface ImplementazioneDAO {

    /**
     * Sets schema.
     */
    void setSchema();

    /**
     * Login amministratore.
     *
     * @param username the username
     * @return the amministratore
     * @throws Exception the exception
     */
    Amministratore login(String username) throws Exception;

    /**
     * Registrazione.
     *
     * @param username the username
     * @param password the password
     * @throws Exception the exception
     */
    void registrazione(String username, String password) throws Exception;

    /**
     * Gets ruoli.
     *
     * @return the ruoli
     */
    ArrayList<Ruolo> getRuoli();

    /**
     * Get nazionalità array list.
     *
     * @return the array list
     */
    ArrayList<Nazionalita> getNazionalità();

    /**
     * Gets squadre.
     *
     * @return the squadre
     */
    ArrayList<Squadra> getSquadre();

    /**
     * Gets calciatori.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalità the nazionalità
     * @param piede       the piede
     * @param età         the età
     * @param ruolo       the ruolo
     * @param golFatti    the gol fatti
     * @param golSubiti   the gol subiti
     * @param dataRitiro  the data ritiro
     * @return the calciatori
     */
    DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                    String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro);

    /**
     * Aggiungi calciatore.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalità the nazionalità
     * @param piede       the piede
     * @param dataNascita the data nascita
     * @param ruolo       the ruolo
     * @param dataRitiro  the data ritiro
     * @param dataInizio  the data inizio
     * @param dataFine    the data fine
     */
    void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                            String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                            LocalDate dataFine) throws Exception;

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
    void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro,
                            int golFatti, Integer golSubiti, String squadra);

    /**
     * Visualizza ruolo calciatore array list.
     *
     * @param idCalciatore the id calciatore
     * @return the array list
     */
    ArrayList<Ha> visualizzaRuoloCalciatore(int idCalciatore);

    /**
     * Inserisci ruolo.
     *
     * @param idCalciatore the id calciatore
     * @param ruolo        the ruolo
     * @throws Exception the exception
     */
    void inserisciRuolo (int idCalciatore, String ruolo) throws Exception;

    /**
     * Elimina ruolo.
     *
     * @param idCalciatore the id calciatore
     * @param ruolo        the ruolo
     */
    void eliminaRuolo (int idCalciatore, ArrayList<String> ruolo);

    /**
     * Visualizza nazionalità calciatore array list.
     *
     * @param idCalciatore the id calciatore
     * @return the array list
     */
    ArrayList<Appartiene> visualizzaNazionalitàCalciatore(int idCalciatore);

    /**
     * Inserisci nazionalità.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalità  the nazionalità
     * @throws Exception the exception
     */
    void inserisciNazionalità (int idCalciatore, String nazionalità) throws Exception;

    /**
     * Elimina nazionalità.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalità  the nazionalità
     */
    void eliminaNazionalità (int idCalciatore, ArrayList<String> nazionalità);

    /**
     * Elimina calciatore.
     *
     * @param idCalciatore the id calciatore
     */
    void eliminaCalciatore(ArrayList<Integer> idCalciatore);

}