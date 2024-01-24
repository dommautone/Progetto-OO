package PostgresDAO;

import controller.Controller;
import database.ConnessioneDatabase;
import DAO.ImplementazioneDAO;
import model.*;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public ArrayList<Nazionalità> getNazionalità() {
        PreparedStatement setNazionalità = null;
        ArrayList<Nazionalità> nazionalità = new ArrayList<>();
        try {
            String query = "SELECT * FROM Nazionalità ORDER BY Nome";
            setNazionalità = connection.prepareStatement(query);
            ResultSet rs = setNazionalità.executeQuery();
            while (rs.next()) {
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

    public ArrayList<Squadra> getSquadre() {
        PreparedStatement setSquadre = null;
        ArrayList<Squadra> squadre = new ArrayList<>();
        try {
            String query = "SELECT * FROM Squadra JOIN Nazionalità ON Squadra.Nazionalità = Nazionalità.Nome ORDER BY Squadra.Nome";
            setSquadre = connection.prepareStatement(query);
            ResultSet rs = setSquadre.executeQuery();
            while (rs.next()) {
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

    /* public ArrayList<Object> getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                            String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
         StringBuilder sceltaDati = new StringBuilder(
                 "SELECT nome_calciatore, cognome, sesso, nome_squadra, STRING_AGG(DISTINCT nazionalità_calciatore, '/') AS nazionalità_calciatore, " +
                         "piede, dataNascita, dataRitiro, STRING_AGG(DISTINCT ruolo, '/') AS ruolo, MAX(golSegnati) AS golSegnati, " +
                         "MAX(golSubiti) AS golSubiti " +
                         "FROM ( " +
                         "SELECT idCalciatore, idSquadra, continente, ruolo.descrizione, annoFondazione, categoria, partiteGiocate, " +
                         "calciatore.nome AS nome_calciatore, cognome, sesso, squadra.nome AS nome_squadra, " +
                         "Appartiene.nazionalità AS nazionalità_calciatore, piede, dataNascita,dataRitiro, Ha.ruolo, " +
                         "SUM(golSegnati) AS golSegnati, SUM(golSubiti) AS golSubiti " +
                         "FROM Calciatore LEFT JOIN Militanza ON Calciatore.idCalciatore = Militanza.Calciatore " +
                         "LEFT JOIN Appartiene ON Calciatore.idCalciatore = Appartiene.Calciatore " +
                         "LEFT JOIN Ha ON Calciatore.idCalciatore = Ha.Calciatore " +
                         "LEFT JOIN Squadra ON Militanza.Squadra = Squadra.idSquadra " +
                         "WHERE 1 = 1 ");
         PreparedStatement getGiocatori = null;
         ResultSet rs = null;
         ArrayList<Object> calciatori = new ArrayList<>();

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
         if (nazionalità != null && !nazionalità.equals(""))
             sceltaDati.append(" AND appartiene.nazionalità = '" + nazionalità + "'");
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
         if (ruolo != null && !ruolo.equals(""))
             sceltaDati.append(" AND ruolo = '" + ruolo + "'");
         if (golFatti != null)
             sceltaDati.append(" AND golSegnati = " + golFatti);
         if (golSubiti != null)
             sceltaDati.append(" AND golSubiti = " + golSubiti);
         sceltaDati.append(" GROUP BY calciatore.nome, cognome, sesso, squadra.nome, Appartiene.nazionalità, piede, dataNascita, " +
                 "dataRitiro, Ha.ruolo) " +
                 "GROUP BY nome_calciatore, cognome, sesso, nome_squadra, piede, dataNascita, dataRitiro " +
                 "ORDER BY nome_calciatore, cognome");
         try {
             getGiocatori = connection.prepareStatement(sceltaDati.toString());
             rs = getGiocatori.executeQuery();
             while (rs.next()) {
                 Calciatore calciatore = new Calciatore(rs.getInt("idCalciatore"),rs.getString("nome_calciatore"),
                         rs.getString("cognome"), rs.getString("piede"),
                         rs.getString("sesso").charAt(0), rs.getDate("dataNascita").toLocalDate(),
                         rs.getDate("dataRitiro") != null ? rs.getDate("dataRitiro").toLocalDate() : null);
                 Nazionalità nazionalità1 = new Nazionalità(rs.getString("nazionalità_calciatore"), rs.getString("continente"));
                 Squadra squadra1 = new Squadra(rs.getInt("idSquadra"), rs.getString("nome_squadra"),
                         rs.getString("categoria").charAt(0), rs.getInt("annoFondazione"),
                         new Nazionalità(rs.getString("squadra.nazionalità"), rs.getString("continente")));
                 Militanza militanza = new Militanza(rs.getDate("dataInizio").toLocalDate(),
                         rs.getDate("dataFine") != null ? rs.getDate("dataFine").toLocalDate() : null,
                         rs.getInt("partiteGiocate"), rs.getInt("golSegnati"), rs.getInt("golSubiti"),
                         calciatore, squadra1);
                 Ruolo ruolo1 = new Ruolo(rs.getString("posizione"), rs.getString("descrizione"));
                 Ha ha = new Ha(calciatore, ruolo1);
                 Appartiene appartiene = new Appartiene(calciatore, nazionalità1);
                 calciatori.add(new Object[]{calciatore, nazionalità1, squadra1, militanza, ha, appartiene});
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
         return calciatori;
     }
 */
    public void aggiungiCalciatore(String nome, String cognome, char sesso, String squadra, ArrayList<String> nazionalità,
                                   String piede, LocalDate dataNascita, ArrayList<String> ruolo, LocalDate dataRitiro, LocalDate dataInizio,
                                   LocalDate dataFine) {

        PreparedStatement aggiungiCalciatore = null;
        PreparedStatement recuperoidSquadra = null;
        PreparedStatement aggiungiMilitanza = null;
        PreparedStatement aggiungiAppartiene = null;
        PreparedStatement aggiungiHa = null;
        ResultSet rs = null;
        piede = piede.toLowerCase();
        nome = nome.trim(); //Rimuove gli spazi all'inizio e alla fine
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase(); //Mette la prima lettera maiuscola e le altre minuscole
        cognome = cognome.trim();
        cognome = cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();


        try {
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
            String queryidSquadra = "SELECT idSquadra FROM Squadra WHERE nome = ?";
            recuperoidSquadra = connection.prepareStatement(queryidSquadra);
            recuperoidSquadra.setString(1, squadra);
            rs = recuperoidSquadra.executeQuery();
            rs.next();
            int idSquadra = rs.getInt("idSquadra");
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

    public DefaultTableModel getCalciatori(String nome, String cognome, char sesso, String squadra, String nazionalità,
                                           String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
        StringBuilder sceltaDati = new StringBuilder(
                "SELECT nome_calciatore, cognome, sesso, nome_squadra, STRING_AGG(DISTINCT nazione_calciatore, '/') AS nazionalità_calciatore, " +
                        "piede, dataNascita, dataRitiro, STRING_AGG(DISTINCT ruolo, '/') AS ruolo, MAX(golSegnati) AS golSegnati, " +
                        "MAX(golSubiti) AS golSubiti " +
                        "FROM ( " +
                        "SELECT calciatore.nome AS nome_calciatore, cognome, sesso, squadra.nome AS nome_squadra, " +
                        "Appartiene.nazionalità AS nazione_calciatore, piede, dataNascita,dataRitiro, Ha.ruolo, " +
                        "SUM(golSegnati) AS golSegnati, SUM(golSubiti) AS golSubiti " +
                        "FROM Calciatore LEFT JOIN Militanza ON Calciatore.idCalciatore = Militanza.Calciatore " +
                        "LEFT JOIN Appartiene ON Calciatore.idCalciatore = Appartiene.Calciatore " +
                        "LEFT JOIN Ha ON Calciatore.idCalciatore = Ha.Calciatore " +
                        "LEFT JOIN Squadra ON Militanza.Squadra = Squadra.idSquadra " +
                        "WHERE 1 = 1 ");
        PreparedStatement getGiocatori = null;
        ResultSet rs = null;

        DefaultTableModel ricercaCalciatoriUtente = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Cognome", "Piede",
                "Sesso", "Data di nascita", "Data di ritiro", "Squadra", "Nazionalità", "Ruolo", "Gol fatti", "Gol subiti"}) {
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
        if (nazionalità != null && !nazionalità.equals(""))
            sceltaDati.append(" AND appartiene.nazionalità = '" + nazionalità + "'");
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
        if (ruolo != null && !ruolo.equals(""))
            sceltaDati.append(" AND ruolo = '" + ruolo + "'");
        if (golFatti != null)
            sceltaDati.append(" AND golSegnati = " + golFatti);
        if (golSubiti != null)
            sceltaDati.append(" AND golSubiti = " + golSubiti);
        sceltaDati.append(" GROUP BY calciatore.nome, cognome, sesso, squadra.nome, Appartiene.nazionalità, piede, dataNascita, " +
                "dataRitiro, Ha.ruolo) " +
                "GROUP BY nome_calciatore, cognome, sesso, nome_squadra, piede, dataNascita, dataRitiro " +
                "ORDER BY nome_calciatore, cognome");
        try {
            getGiocatori = connection.prepareStatement(sceltaDati.toString());
            rs = getGiocatori.executeQuery();
            while (rs.next()) {
                ricercaCalciatoriUtente.addRow(new Object[]{
                        rs.getString("nome_calciatore"), rs.getString("cognome"),
                        rs.getString("piede"), rs.getString("sesso"),
                        rs.getDate("dataNascita"), rs.getDate("dataRitiro"),
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
}

