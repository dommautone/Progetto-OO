package DAO;

import model.*;

import java.util.ArrayList;

public interface ImplementazioneDAO {

     void registrazione(String username, String password) throws Exception;
     void setCalciatore(String nome, String cognome, String piede, char sesso, String DataNascita, String DataRitiro) throws Exception;
     void setRuolo(Calciatore calciatore, Ruolo ruolo) throws Exception;
     void setSquadra(Calciatore calciatore, Squadra squadra, String dataInizio, String dataFine) throws Exception;
     void setNazionalita(Calciatore calciatore, Nazionalità nazionalita) throws Exception;
     void setFeature(Calciatore calciatore, Feature feature) throws Exception;
     void setSchema();
     //boolean UtenteEsistente();

     ArrayList<Ruolo> getRuoli();
     ArrayList<Feature> getFeature();
    // ArrayList<Squadra> getSquadra();
     ArrayList<Nazionalità> getNazionalita();
     ArrayList<Calciatore> getCalciatoreNome();
}
