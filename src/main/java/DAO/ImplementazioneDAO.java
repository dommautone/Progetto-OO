package DAO;

import model.*;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ImplementazioneDAO {

    void setSchema();
    Amministratore login(String username) throws Exception;
    void registrazione(String username, String password) throws Exception;
    ArrayList<Ruolo> getRuoli();
    ArrayList<Nazionalità> getNazionalità();
    ArrayList<Squadra> getSquadre();
    //ArrayList<Object> getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                        // String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro);
    DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                    String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro);
    void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                            String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                            LocalDate dataFine);

}