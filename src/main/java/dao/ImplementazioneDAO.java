package dao;

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
    ArrayList<Nazionalita> getNazionalita();

    /**
     * Gets squadre.
     *
     * @return the squadre
     */
    ArrayList<Squadra> getSquadre();

    ArrayList<Squadra> getSquadreCategoria(char categoria);
    int getIdSquadra(String nomeSquadra, char categoria);

    /**
     * Gets calciatori.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalita the nazionalità
     * @param piede       the piede
     * @param eta         the età
     * @param ruolo       the ruolo
     * @param golFatti    the gol fatti
     * @param golSubiti   the gol subiti
     * @param dataRitiro  the data ritiro
     * @return the calciatori
     */
    DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalita,
                                    String piede, Integer eta, String ruolo, Integer golFatti, Integer golSubiti,
                                    LocalDate dataRitiro) throws Exception;

    /**
     * Aggiungi calciatore.
     *
     * @param nome        the nome
     * @param cognome     the cognome
     * @param sesso       the sesso
     * @param squadra     the squadra
     * @param nazionalita the nazionalità
     * @param piede       the piede
     * @param dataNascita the data nascita
     * @param ruolo       the ruolo
     * @param dataRitiro  the data ritiro
     * @param dataInizio  the data inizio
     * @param dataFine    the data fine
     */
    void aggiungiCalciatore(String nome, String cognome, char sesso, int idSquadra, ArrayList<String> nazionalita,
                            String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro,
                            LocalDate dataInizio, LocalDate dataFine) throws Exception;

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
    void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso,
                            LocalDate dataNascita, LocalDate dataRitiro, int partiteGiocate, int golFatti,
                            Integer golSubiti, String squadra);

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
    ArrayList<Appartiene> visualizzaNazionalitaCalciatore(int idCalciatore);

    /**
     * Inserisci nazionalità.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalita  the nazionalità
     * @throws Exception the exception
     */
    void inserisciNazionalita(int idCalciatore, String nazionalita) throws Exception;

    /**
     * Elimina nazionalità.
     *
     * @param idCalciatore the id calciatore
     * @param nazionalita  the nazionalità
     */
    void eliminaNazionalita(int idCalciatore, ArrayList<String> nazionalita);

    /**
     * Elimina calciatore.
     *
     * @param idCalciatore the id calciatore
     */
    void eliminaCalciatore(ArrayList<Integer> idCalciatore);

    ArrayList<Militanza> visualizzaSquadreCalciatore(int idCalciatore);
    void inserisciSquadra(int idCalciatore, int idSquadra, LocalDate dataInizio, LocalDate dataFine, int partiteGiocate,
                          int golFatti, Integer golSubiti) throws Exception;
    void eliminaSquadra(int idCalciatore, ArrayList<Integer> squadra);

}