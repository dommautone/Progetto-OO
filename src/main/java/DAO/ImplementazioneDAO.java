package DAO;

import model.Amministratore;
import model.Nazionalità;
import model.Ruolo;
import model.Squadra;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ImplementazioneDAO {

    void setSchema();
    Amministratore login(String username) throws Exception;
    void registrazione(String username, String password) throws Exception;
    ArrayList<Ruolo> getRuoli();
    ArrayList<Nazionalità> getNazionalità();
    ArrayList<Squadra> getSquadre();

    //void getGiocatoriUtente();
    //void getCalciatoriAmministratore();
    //void getCalciatoriEliminare();
    //void getCalciatoriModificare();
    //void getCalciatoriInserire();




}
