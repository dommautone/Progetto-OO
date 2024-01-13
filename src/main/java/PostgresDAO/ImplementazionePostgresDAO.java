package PostgresDAO;

import controller.Controller;
import database.ConnessioneDatabase;
import model.*;
import DAO.ImplementazioneDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImplementazionePostgresDAO implements ImplementazioneDAO {

    private Connection connection;
    private Controller controller;

    public ImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            this.controller = new Controller(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSchema() {
        PreparedStatement setSchema = null;
        try {
            String query = "SET search_path TO progettobd";
            setSchema = connection.prepareStatement(query);
            setSchema.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (setSchema != null)
                    setSchema.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());

            }
        }
    }

    public void registrazione(String username, String password) throws Exception {
        PreparedStatement registrazione = null;
        try {
            String query = "INSERT INTO Amministratore (Username, Passowrd) VALUES (?, ?)";
            registrazione = connection.prepareStatement(query);
            registrazione.setString(1, username);
            registrazione.setString(2, password);
            registrazione.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (registrazione != null)
                    registrazione.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

    }

    public void setCalciatore(String nome, String cognome, String piede, char sesso, String DataNascita, String DataRitiro) throws Exception {
        PreparedStatement setCalciatore = null;
        try {
            String query = "INSERT INTO Calciatore (Nome, Cognome, Piede, Sesso, DataNascita, DataRitiro) VALUES (?, ?, ?, ?, ?, ?)";
            setCalciatore = connection.prepareStatement(query);
            setCalciatore.setString(1, nome);
            setCalciatore.setString(2, cognome);
            setCalciatore.setString(3, piede);
            setCalciatore.setString(4, String.valueOf(sesso));
            setCalciatore.setString(5, DataNascita);
            setCalciatore.setString(6, DataRitiro);
            setCalciatore.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (setCalciatore != null)
                    setCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void setRuolo(Calciatore calciatore, Ruolo ruolo) throws Exception {
        PreparedStatement setRuolo = null;
        try {
            String query = "INSERT INTO Ha (IdCalciatore, Ruolo) VALUES (?, ?)";
            setRuolo = connection.prepareStatement(query);
            setRuolo.setInt(1, calciatore.getIdCalciatore());
            setRuolo.setString(2, ruolo.getPosizione());
            setRuolo.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (setRuolo != null)
                    setRuolo.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void setSquadra(Calciatore calciatore, Squadra squadra, String dataInizio, String dataFine) throws Exception {
        PreparedStatement setSquadra = null;
        try {
            String query = "INSERT INTO Militanza (Calciatore, Squadra, dataInizio, dataFine) VALUES (?, ?, ?, ?)";
            setSquadra = connection.prepareStatement(query);
            setSquadra.setInt(1, calciatore.getIdCalciatore());
            setSquadra.setInt(2, squadra.getIdSquadra());
            setSquadra.setString(3, dataInizio);
            setSquadra.setString(4, dataFine);
            setSquadra.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (setSquadra != null)
                    setSquadra.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void setNazionalita(Calciatore calciatore, Nazionalità nazionalita) throws Exception {
        PreparedStatement setNazionalita = null;
        try {
            String query = "INSERT INTO Appartiene (IdCalciatore, Nazionalita) VALUES (?, ?)";
            setNazionalita = connection.prepareStatement(query);
            setNazionalita.setInt(1, calciatore.getIdCalciatore());
            setNazionalita.setString(2, nazionalita.getNome());
            setNazionalita.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (setNazionalita != null)
                    setNazionalita.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void setFeature(Calciatore calciatore, Feature feature) throws Exception {
        PreparedStatement setFeature = null;
        try {
            String query = "INSERT INTO Feature (IdCalciatore, tipoFeature) VALUES (?, ?)";
            setFeature = connection.prepareStatement(query);
            setFeature.setInt(1, calciatore.getIdCalciatore());
            setFeature.setString(2, feature.getTipoFeature());
            setFeature.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (setFeature != null)
                    setFeature.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public ArrayList<Ruolo> getRuoli() {
        PreparedStatement selectRuolo = null;
        ResultSet rs = null;
        ArrayList<Ruolo> ruoli = new ArrayList<>();
        try {
            String query = "SELECT* FROM Ruolo";
            selectRuolo = connection.prepareStatement(query);
            rs = selectRuolo.executeQuery();
            while (rs.next()) {
                Ruolo ruolo = new Ruolo(rs.getString("Posizione"), rs.getString("Descrizione"));
                ruoli.add(ruolo);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (selectRuolo != null)
                    selectRuolo.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return ruoli;
    }

    public ArrayList<Feature> getFeature(){
        PreparedStatement selectFeature = null;
        ResultSet rs = null;
        ArrayList<Feature> features = new ArrayList<>();
        try{
            String query = "SELECT * FROM Feature";
            selectFeature = connection.prepareStatement(query);
            rs = selectFeature.executeQuery();
            while(rs.next()){
                Feature feature = new Feature(rs.getString("TipoFeature"), rs.getString("Descrizione"));
                features.add(feature);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (selectFeature != null)
                    selectFeature.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return features;
    }

   /* public ArrayList<Squadra> getSquadra(){
        PreparedStatement selectSquadra = null;
        ResultSet rs = null;
        ArrayList<Squadra> squadre = new ArrayList<>();
        try{
            String query = "SELECT * FROM Squadra";
            selectSquadra = connection.prepareStatement(query);
            rs = selectSquadra.executeQuery();
            while(rs.next()){
                Squadra squadra = new Squadra(rs.getInt("idSquadra"),rs.getString("Nome"),rs.getInt("annoFondazione"), rs.getInt("Categoria"), rs.getString("Nazionalita"));
                squadre.add(squadra);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (selectSquadra != null)
                    selectSquadra.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return squadre;
    }*/

    public ArrayList<Nazionalità> getNazionalita(){
        PreparedStatement selectNazionalita = null;
        ResultSet rs = null;
        ArrayList<Nazionalità> nazionali = new ArrayList<>();
        try{
            String query = "SELECT * FROM Nazionalita";
            selectNazionalita = connection.prepareStatement(query);
            rs = selectNazionalita.executeQuery();
            while(rs.next()){
                Nazionalità nazionalita = new Nazionalità(rs.getString("Nome"), rs.getString("Descrizione"));
                nazionali.add(nazionalita);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (selectNazionalita != null)
                    selectNazionalita.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return nazionali;
    }
    public ArrayList<Calciatore> getCalciatoreNome(){
        PreparedStatement selectCalciatore = null;
        ResultSet rs = null;
        ArrayList<Calciatore> calciatori = new ArrayList<>();
        try{
            String query = "SELECT * FROM Calciatore";
            selectCalciatore = connection.prepareStatement(query);
            rs = selectCalciatore.executeQuery();
            while(rs.next()){
                Calciatore calciatore = new Calciatore(rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Piede"), rs.getString("Sesso").charAt(0), rs.getString("DataNascita"), rs.getString("DataRitiro"));
                calciatori.add(calciatore);
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        } finally {
            try {
                if (selectCalciatore != null)
                    selectCalciatore.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return calciatori;
    }

}
