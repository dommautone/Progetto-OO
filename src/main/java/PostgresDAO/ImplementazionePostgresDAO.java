package PostgresDAO;

import controller.Controller;
import database.ConnessioneDatabase;
import DAO.ImplementazioneDAO;
import model.Amministratore;
import model.Nazionalità;
import model.Ruolo;
import model.Squadra;

import java.sql.*;
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

    public Amministratore login(String username) throws Exception {
        PreparedStatement login = null;
        ResultSet rs = null;
        Amministratore amministratore = null;
        try {
            String query = "SELECT * FROM Amministratore WHERE username = ?";
            login = connection.prepareStatement(query);
            login.setString(1, username);
            rs = login.executeQuery();
            if(rs.next()) {
                amministratore = new Amministratore(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (login != null)
                    login.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return amministratore;
    }

    public void registrazione(String username, String password) throws Exception {
        PreparedStatement registrazione = null;
        try {
            String query = "INSERT INTO Amministratore VALUES (?, ?)";
            registrazione = connection.prepareStatement(query);
            registrazione.setString(1, username);
            registrazione.setString(2, password);
            registrazione.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
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

    public ArrayList<Ruolo> getRuoli(){
        PreparedStatement setRuoli = null;
        ArrayList<Ruolo> ruolo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Ruolo";
            setRuoli = connection.prepareStatement(query);
            ResultSet rs = setRuoli.executeQuery();
            while(rs.next()) {
                ruolo.add(new Ruolo(rs.getString("Posizione"), rs.getString("Descrizione")));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (setRuoli != null)
                    setRuoli.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return ruolo;
    }

    public ArrayList<Nazionalità> getNazionalità(){
        PreparedStatement setNazionalità = null;
        ArrayList<Nazionalità> nazionalità = new ArrayList<>();
        try {
            String query = "SELECT * FROM Nazionalità ORDER BY Nome";
            setNazionalità = connection.prepareStatement(query);
            ResultSet rs = setNazionalità.executeQuery();
            while(rs.next()) {
                nazionalità.add(new Nazionalità(rs.getString("Nome"), rs.getString("Continente")));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (setNazionalità != null)
                    setNazionalità.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return nazionalità;
    }

    public ArrayList<Squadra> getSquadre(){
        PreparedStatement setSquadre = null;
        ArrayList<Squadra> squadre = new ArrayList<>();
        try {
            String query = "SELECT * FROM Squadra JOIN Nazionalità ON Squadra.Nazionalità = Nazionalità.Nome ORDER BY Squadra.Nome";
            setSquadre = connection.prepareStatement(query);
            ResultSet rs = setSquadre.executeQuery();
            while(rs.next()) {
                squadre.add(new Squadra(rs.getInt("idSquadra"), rs.getString("Nome"), rs.getString("Categoria").charAt(0), rs.getInt("annoFondazione"), new Nazionalità(rs.getString("Nome"), rs.getString("Continente"))));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (setSquadre != null)
                    setSquadre.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return squadre;
    }
}
