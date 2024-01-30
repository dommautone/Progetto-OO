package PostgresDAO;

import controller.Controller;
import database.ConnessioneDatabase;
import DAO.ImplementazioneDAO;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * La classe Implementazione implementa i metodi per la gestione dei dati riguardanti il database.
 */
public class ImplementazionePostgresDAO implements ImplementazioneDAO {

    private Connection connection;
    private Controller controller;

    /**
     * Istanzia a new Implementazione postgres dao.
     */
    public ImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
            this.controller = new Controller(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * setSchema setta lo schema su cui andremo a lavorare.
     */
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
            if (rs.next()) {
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

    public ArrayList<Ruolo> getRuoli() {
        PreparedStatement setRuoli = null;
        ArrayList<Ruolo> ruolo = new ArrayList<>();
        try {
            String query = "SELECT * FROM Ruolo";
            setRuoli = connection.prepareStatement(query);
            ResultSet rs = setRuoli.executeQuery();
            while (rs.next()) {
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

    public ArrayList<Nazionalita> getNazionalità() {
        PreparedStatement setNazionalità = null;
        ArrayList<Nazionalita> nazionalita = new ArrayList<>();
        try {
            String query = "SELECT * FROM Nazionalità ORDER BY Nome";
            setNazionalità = connection.prepareStatement(query);
            ResultSet rs = setNazionalità.executeQuery();
            while (rs.next()) {
                nazionalita.add(new Nazionalita(rs.getString("Nome"), rs.getString("Continente")));
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
        return nazionalita;
    }

    public ArrayList<Squadra> getSquadre() {
        PreparedStatement setSquadre = null;
        ArrayList<Squadra> squadre = new ArrayList<>();
        try {
            String query = "SELECT * FROM Squadra JOIN Nazionalità ON Squadra.Nazionalità = Nazionalità.Nome ORDER BY Squadra.Nome";
            setSquadre = connection.prepareStatement(query);
            ResultSet rs = setSquadre.executeQuery();
            while (rs.next()) {
                squadre.add(new Squadra(rs.getInt("idSquadra"), rs.getString("Nome"), rs.getString("Categoria").charAt(0), rs.getInt("annoFondazione"), new Nazionalita(rs.getString("Nome"), rs.getString("Continente"))));
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


    public DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                           String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
        StringBuilder sceltaDati = new StringBuilder(
                "SELECT idCalciatore, idSquadra, nome_calciatore, cognome, sesso, nome_squadra, STRING_AGG(DISTINCT nazione_calciatore, '/') AS nazionalità_calciatore, " +
                        "piede, dataNascita, dataRitiro, STRING_AGG(DISTINCT ruolo, '/') AS ruolo, MAX(golSegnati) AS golSegnati, " +
                        "MAX(golSubiti) AS golSubiti " +
                        "FROM ( " +
                        "SELECT idCalciatore, idSquadra, calciatore.nome AS nome_calciatore, cognome, sesso, squadra.nome AS nome_squadra, " +
                        "Appartiene.nazionalità AS nazione_calciatore, piede, dataNascita,dataRitiro, Ha.ruolo, " +
                        "SUM(golSegnati) AS golSegnati, SUM(golSubiti) AS golSubiti " +
                        "FROM Calciatore LEFT JOIN Militanza ON Calciatore.idCalciatore = Militanza.Calciatore " +
                        "LEFT JOIN Appartiene ON Calciatore.idCalciatore = Appartiene.Calciatore " +
                        "LEFT JOIN Ha ON Calciatore.idCalciatore = Ha.Calciatore " +
                        "LEFT JOIN Squadra ON Militanza.Squadra = Squadra.idSquadra " +
                        "WHERE 1 = 1 ");
        PreparedStatement getGiocatori = null;
        ResultSet rs = null;
        DefaultTableModel ricercaCalciatoriUtente = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Nome",
                "Cognome", "Piede", "Sesso", "Data di nascita", "Data di ritiro", "idSquadra", "Squadra", "Nazionalita", "Ruolo",
                "Gol fatti", "Gol subiti"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        if (nome != null && !nome.equals("")) {
            nome = nome.trim();
            nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
            sceltaDati.append(" AND calciatore.nome = '" + nome + "'");
        }
        if (cognome != null && !cognome.equals("")) {
            cognome = cognome.trim();
            cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();
            sceltaDati.append(" AND cognome = '" + cognome + "'");
        }
        if (sesso != 'A')
            sceltaDati.append(" AND sesso = '" + sesso + "'");
        if (squadra != null && !squadra.equals(""))
            sceltaDati.append(" AND squadra.nome = '" + squadra + "'");
        if (piede != null && !piede.equals("")) {
            piede = piede.toLowerCase();
            sceltaDati.append(" AND piede = '" + piede + "'");
        }
        if (età != null) {
            LocalDate data = LocalDate.now();
            LocalDate dataNascita = data.minusYears(età);
            sceltaDati.append(" AND EXTRACT(YEAR FROM dataNascita) = '" + dataNascita.getYear() + "'");
        }
        if (dataRitiro != null && !dataRitiro.equals(LocalDate.of(1111, 1, 1)) && !dataRitiro.equals(LocalDate.of(2222, 2, 2)))
            sceltaDati.append(" AND dataRitiro = '" + dataRitiro + "'");
        else if (dataRitiro != null && dataRitiro.equals(LocalDate.of(2222, 2, 2)))
            sceltaDati.append(" AND dataRitiro IS NOT NULL");
        else if (dataRitiro == null)
            sceltaDati.append(" AND dataRitiro IS NULL");
        if (golFatti != null)
            sceltaDati.append(" AND golSegnati = " + golFatti);
        if (golSubiti != null)
            sceltaDati.append(" AND golSubiti = " + golSubiti);
        sceltaDati.append(" GROUP BY idCalciatore,idSquadra, calciatore.nome, cognome, sesso, squadra.nome, Appartiene.nazionalità, piede, dataNascita, " +
                "dataRitiro, Ha.ruolo) " +
                "GROUP BY idCalciatore, idSquadra, nome_calciatore, cognome, sesso, nome_squadra, piede, dataNascita, dataRitiro ");
        if(nazionalità != null && !nazionalità.equals("") && ruolo != null && !ruolo.equals(""))
            sceltaDati.append(" HAVING STRING_AGG(DISTINCT nazione_calciatore, '/') LIKE '%" + nazionalità + "%'" +
                    " AND STRING_AGG(DISTINCT ruolo, '/') LIKE '%" + ruolo + "%'");
        else if(nazionalità.equals("") && ruolo != null && !ruolo.equals(""))
            sceltaDati.append(" HAVING STRING_AGG(DISTINCT ruolo, '/') LIKE '%" + ruolo + "%'");
        else if(ruolo.equals("") && nazionalità != null && !nazionalità.equals(""))
            sceltaDati.append(" HAVING STRING_AGG(DISTINCT nazione_calciatore, '/') LIKE '%" + nazionalità + "%'");
        sceltaDati.append(" ORDER BY nome_calciatore, cognome, sesso, dataNascita, dataRitiro");

        try {
            getGiocatori = connection.prepareStatement(sceltaDati.toString());
            rs = getGiocatori.executeQuery();
            while (rs.next()) {
                ricercaCalciatoriUtente.addRow(new Object[]{
                        rs.getInt("idCalciatore"), rs.getString("nome_calciatore"),
                        rs.getString("cognome"), rs.getString("piede"), rs.getString("sesso"),
                        rs.getDate("dataNascita"), rs.getDate("dataRitiro"), rs.getInt("idSquadra"),
                        rs.getString("nome_squadra"), rs.getString("nazionalità_calciatore"),
                        rs.getString("ruolo"), rs.getInt("golSegnati"), rs.getInt("golSubiti")});
            }

        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (getGiocatori != null)
                    getGiocatori.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return ricercaCalciatoriUtente;
    }

    public void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                                   String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                                   LocalDate dataFine) throws Exception {

        PreparedStatement aggiungiCalciatore = null;
        PreparedStatement recuperoidSquadra = null;
        PreparedStatement aggiungiMilitanza = null;
        PreparedStatement aggiungiAppartiene = null;
        PreparedStatement aggiungiHa = null;
        ResultSet rs = null;
        int idSquadra;

        try {
            String queryidSquadra = "SELECT idSquadra FROM Squadra WHERE nome = ? AND categoria = ?";
            recuperoidSquadra = connection.prepareStatement(queryidSquadra);
            recuperoidSquadra.setString(1, squadra);
            //Per evitare problemi con squadre che hanno lo stesso identico nome ma che appartengono a categorie differenti
            recuperoidSquadra.setString(2, String.valueOf(sesso));
            rs = recuperoidSquadra.executeQuery();
            if(rs.next())
                idSquadra = rs.getInt("idSquadra");
            else
                throw new Exception();

            String query = "INSERT INTO Calciatore(nome, cognome, sesso, piede, dataNascita, dataRitiro) VALUES (?, ?, ?, ?, ?, ?)";
            aggiungiCalciatore = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            aggiungiCalciatore.setString(1, nome);
            aggiungiCalciatore.setString(2, cognome);
            aggiungiCalciatore.setString(3, String.valueOf(sesso));
            aggiungiCalciatore.setString(4, piede);
            aggiungiCalciatore.setDate(5, Date.valueOf(dataNascita));
            if (dataRitiro != null)
                aggiungiCalciatore.setDate(6, Date.valueOf(dataRitiro));
            else
                aggiungiCalciatore.setNull(6, Types.DATE);
            aggiungiCalciatore.executeUpdate();
            rs = aggiungiCalciatore.getGeneratedKeys();
            rs.next();
            int idCalciatore = rs.getInt(1);

            String queryMilitanza = "INSERT INTO Militanza (calciatore, squadra, dataInizio, dataFine) VALUES (?, ?, ?, ?)";
            aggiungiMilitanza = connection.prepareStatement(queryMilitanza);
            aggiungiMilitanza.setInt(1, idCalciatore);
            aggiungiMilitanza.setInt(2, idSquadra);
            aggiungiMilitanza.setDate(3, Date.valueOf(dataInizio));
            if (dataFine != null)
                aggiungiMilitanza.setDate(4, Date.valueOf(dataFine));
            else
                aggiungiMilitanza.setNull(4, Types.DATE);
            aggiungiMilitanza.executeUpdate();

            if (nazionalità != null) {
                for (String nazionalità1 : nazionalità) {
                    String queryAppartiene = "INSERT INTO Appartiene (calciatore, nazionalità) VALUES (?, ?)";
                    aggiungiAppartiene = connection.prepareStatement(queryAppartiene);
                    aggiungiAppartiene.setInt(1, idCalciatore);
                    aggiungiAppartiene.setString(2, nazionalità1);
                    aggiungiAppartiene.executeUpdate();
                }
            }

            if (ruolo != null) {
                for (String ruolo1 : ruolo) {
                    String queryHa = "INSERT INTO Ha (calciatore, ruolo) VALUES (?, ?)";
                    aggiungiHa = connection.prepareStatement(queryHa);
                    aggiungiHa.setInt(1, idCalciatore);
                    aggiungiHa.setString(2, ruolo1);
                    aggiungiHa.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (aggiungiCalciatore != null)
                    aggiungiCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (aggiungiMilitanza != null)
                    aggiungiMilitanza.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (aggiungiAppartiene != null)
                    aggiungiAppartiene.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (aggiungiHa != null)
                    aggiungiHa.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void modificaCalciatore(int idCalciatore, int idSquadra, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro,
                                   int golFatti, Integer golSubiti, String squadra){
        PreparedStatement modificaCalciatore = null;
        PreparedStatement modificaMilitanza = null;
        PreparedStatement getidSquadra = null;
        ResultSet rs = null;

        piede = piede.toLowerCase();
        nome = nome.trim(); //Rimuove gli spazi all'inizio e alla fine
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase(); //Mette la prima lettera maiuscola e le altre minuscole
        cognome = cognome.trim();
        cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();


        try{
            String query = "UPDATE Calciatore " +
                        "SET nome = ?, cognome = ?, sesso = ?, piede = ?, dataNascita = ?, dataRitiro = ?" +
                        " WHERE idCalciatore = ?";
            modificaCalciatore = connection.prepareStatement(query);
            modificaCalciatore.setString(1, nome);
            modificaCalciatore.setString(2, cognome);
            modificaCalciatore.setString(3, String.valueOf(sesso));
            modificaCalciatore.setString(4, piede);
            modificaCalciatore.setDate(5, Date.valueOf(dataNascita));
            if(dataRitiro != null)
                modificaCalciatore.setDate(6, Date.valueOf(dataRitiro));
            else
                modificaCalciatore.setNull(6, Types.DATE);
            modificaCalciatore.setInt(7, idCalciatore);
            modificaCalciatore.executeUpdate();

            String getidSquadraQuery = "SELECT idSquadra FROM Squadra WHERE nome = ? AND categoria = ?";
            getidSquadra = connection.prepareStatement(getidSquadraQuery);
            getidSquadra.setString(1, squadra);
            getidSquadra.setString(2, String.valueOf(sesso));
            rs = getidSquadra.executeQuery();
            rs.next();
            int idSquadra2 = rs.getInt("idSquadra");

            String queryMilitanza = "UPDATE Militanza " +
                        "SET golSegnati = ?, golSubiti = ?, squadra = ? " +
                        "WHERE calciatore = ? AND Squadra = ?";
            modificaMilitanza = connection.prepareStatement(queryMilitanza);
            modificaMilitanza.setInt(1, golFatti);
            if(golSubiti != null)
                modificaMilitanza.setInt(2, golSubiti);
            else
                modificaMilitanza.setNull(2, Types.INTEGER);
            modificaMilitanza.setInt(3, idSquadra2);
            modificaMilitanza.setInt(4, idCalciatore);
            modificaMilitanza.setInt(5, idSquadra);
            modificaMilitanza.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (modificaCalciatore != null)
                    modificaCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (modificaMilitanza != null)
                    modificaMilitanza.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (getidSquadra != null)
                    getidSquadra.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public ArrayList<Ha> visualizzaRuoloCalciatore(int idCalciatore){
        PreparedStatement visualizzaRuoloCalciatore = null;
        ResultSet rs = null;
        LocalDate dataRitiro;
        ArrayList<Ha> ha = new ArrayList<>();
        try{
            String query = "SELECT * FROM Ha JOIN Calciatore ON idCalciatore = Calciatore " +
                                "JOIN Ruolo ON Posizione = Ruolo " +
                                "WHERE Calciatore = ?";
            visualizzaRuoloCalciatore = connection.prepareStatement(query);
            visualizzaRuoloCalciatore.setInt(1, idCalciatore);
            rs = visualizzaRuoloCalciatore.executeQuery();
            while(rs.next()) {
                if (rs.getDate("dataRitiro") == null)
                    dataRitiro = null;
                else
                    dataRitiro = rs.getDate("dataRitiro").toLocalDate();

                ha.add(new Ha(new Calciatore(rs.getInt("idCalciatore"), rs.getString("nome"), rs.getString("cognome"), rs.getString("piede"),
                        rs.getString("sesso").charAt(0), rs.getDate("dataNascita").toLocalDate(), dataRitiro),
                        new Ruolo(rs.getString("Posizione"), rs.getString("Descrizione"))));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (visualizzaRuoloCalciatore != null)
                    visualizzaRuoloCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return ha;
    }

    public void inserisciRuolo(int idCalciatore, String ruolo) throws Exception{
        PreparedStatement inserisciRuolo = null;
        try{
            String query = "INSERT INTO Ha (Calciatore, Ruolo) " +
                    "VALUES (?, ?)";
            inserisciRuolo = connection.prepareStatement(query);
            inserisciRuolo.setInt(1, idCalciatore);
            inserisciRuolo.setString(2, ruolo);
            inserisciRuolo.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (inserisciRuolo != null)
                    inserisciRuolo.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void eliminaRuolo(int idCalciatore, ArrayList<String> ruolo){
        PreparedStatement eliminaRuolo = null;
        try{
            String query = "DELETE FROM Ha " +
                    "WHERE Calciatore = ? AND Ruolo = ?";
            eliminaRuolo = connection.prepareStatement(query);
            for(String ruolo1: ruolo) {
                eliminaRuolo.setInt(1, idCalciatore);
                eliminaRuolo.setString(2, ruolo1);
                eliminaRuolo.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (eliminaRuolo != null)
                    eliminaRuolo.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public ArrayList<Appartiene> visualizzaNazionalitàCalciatore(int idCalciatore){
        PreparedStatement visualizzaNazionalitàCalciatore = null;
        ResultSet rs = null;
        LocalDate dataRitiro;
        ArrayList<Appartiene> appartiene = new ArrayList<>();
        try{
            String query = "SELECT idCalciatore, calciatore.nome AS nome_calciatore, cognome, piede, sesso, dataNascita," +
                    "dataRitiro, nazionalità.nome AS nome_nazionalità, continente" +
                    " FROM Appartiene JOIN Calciatore ON idCalciatore = Calciatore" +
                    " JOIN Nazionalità ON Nazionalità.nome = Nazionalità" +
                    " WHERE Calciatore = ?";
            visualizzaNazionalitàCalciatore = connection.prepareStatement(query);
            visualizzaNazionalitàCalciatore.setInt(1, idCalciatore);
            rs = visualizzaNazionalitàCalciatore.executeQuery();
            while(rs.next()){
                if(rs.getDate("dataRitiro") == null)
                    dataRitiro = null;
                else
                    dataRitiro = rs.getDate("dataRitiro").toLocalDate();
                appartiene.add(new Appartiene(new Calciatore(rs.getInt("idCalciatore"),
                        rs.getString("nome_calciatore"), rs.getString("cognome"),
                        rs.getString("piede"), rs.getString("sesso").charAt(0),
                        rs.getDate("dataNascita").toLocalDate(), dataRitiro),
                        new Nazionalita(rs.getString("nome_nazionalità"), rs.getString("continente"))));
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (visualizzaNazionalitàCalciatore != null)
                    visualizzaNazionalitàCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return appartiene;
    }

    public void inserisciNazionalità(int idCalciatore, String nazionalità) throws Exception{
        PreparedStatement inserisciNazionalità = null;
        try{
            String query = "INSERT INTO Appartiene (Calciatore, Nazionalità) " +
                    "VALUES (?, ?)";
            inserisciNazionalità = connection.prepareStatement(query);
            inserisciNazionalità.setInt(1, idCalciatore);
            inserisciNazionalità.setString(2, nazionalità);
            inserisciNazionalità.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
            throw new Exception();
        } finally {
            try {
                if (inserisciNazionalità != null)
                    inserisciNazionalità.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void eliminaNazionalità(int idCalciatore, ArrayList<String> nazionalit){
        PreparedStatement eliminaNazionalità = null;
        try{
            String query = "DELETE FROM Appartiene " +
                    "WHERE Calciatore = ? AND Nazionalità = ?";
            eliminaNazionalità = connection.prepareStatement(query);
            for(String nazionalità: nazionalit) {
                eliminaNazionalità.setInt(1, idCalciatore);
                eliminaNazionalità.setString(2, nazionalità);
                eliminaNazionalità.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (eliminaNazionalità != null)
                    eliminaNazionalità.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    public void eliminaCalciatore(ArrayList<Integer> idCalciatore){
        PreparedStatement eliminaCalciatore = null;
        PreparedStatement eliminaMilitanza = null;
        PreparedStatement eliminaAppartiene = null;
        PreparedStatement eliminaHa = null;
        PreparedStatement eliminaVinceCalciatore = null;
        PreparedStatement eliminaPossiede = null;
        try{
            String queryMilitanza = "DELETE FROM Militanza " +
                    "WHERE Calciatore = ?";
            eliminaMilitanza = connection.prepareStatement(queryMilitanza);
            for(int id: idCalciatore) {
                eliminaMilitanza.setInt(1, id);
                eliminaMilitanza.executeUpdate();
            }

            String queryAppartiene = "DELETE FROM Appartiene " +
                    "WHERE Calciatore = ?";
            eliminaAppartiene = connection.prepareStatement(queryAppartiene);
            for(int id: idCalciatore) {
                eliminaAppartiene.setInt(1, id);
                eliminaAppartiene.executeUpdate();
            }

            String queryHa = "DELETE FROM Ha " +
                    "WHERE Calciatore = ?";
            eliminaHa = connection.prepareStatement(queryHa);
            for(int id: idCalciatore) {
                eliminaHa.setInt(1, id);
                eliminaHa.executeUpdate();
            }

            String queryVinceCalciatore = "DELETE FROM VinceCalciatore " +
                    "WHERE Calciatore = ?";
            eliminaVinceCalciatore = connection.prepareStatement(queryVinceCalciatore);
            for(int id: idCalciatore) {
                eliminaVinceCalciatore.setInt(1, id);
                eliminaVinceCalciatore.executeUpdate();
            }

            String queryPossiede = "DELETE FROM Possiede " +
                    "WHERE Calciatore = ?";
            eliminaPossiede = connection.prepareStatement(queryPossiede);
            for(int id: idCalciatore) {
                eliminaPossiede.setInt(1, id);
                eliminaPossiede.executeUpdate();
            }

            String query = "DELETE FROM Calciatore " +
                    "WHERE idCalciatore = ?";
            eliminaCalciatore = connection.prepareStatement(query);
            for(int id: idCalciatore) {
                eliminaCalciatore.setInt(1, id);
                eliminaCalciatore.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Errore nell'inserimento dei dati: " + e.getMessage());
        } finally {
            try {
                if (eliminaCalciatore != null)
                    eliminaCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (eliminaMilitanza != null)
                    eliminaMilitanza.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (eliminaAppartiene != null)
                    eliminaAppartiene.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (eliminaHa != null)
                    eliminaHa.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (eliminaVinceCalciatore != null)
                    eliminaVinceCalciatore.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            try {
                if (eliminaPossiede != null)
                    eliminaPossiede.close();
            } catch (SQLException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}

