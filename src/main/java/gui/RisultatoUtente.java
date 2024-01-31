package gui;

import controller.CalciatoriNonTrovatiException;
import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * La gui Risultato Utente permette di visualizzare i risultati della ricerca effettuata da un utente.
 */
public class RisultatoUtente {
    private JPanel panel;
    private JTable tableRicercaUtente;
    private JButton buttonNuovaRicerca;
    private JPanel panelRicercaUtente;
    private JScrollPane scrollPaneRicercaUtente;
    private JPanel panelNuovaRicerca;
    private Controller controller;

    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Risultato utente.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     * @param nome           the nome
     * @param cognome        the cognome
     * @param sesso          the sesso
     * @param squadra        the squadra
     * @param nazionalita    the nazionalità
     * @param piede          the piede
     * @param eta            the età
     * @param ruolo          the ruolo
     * @param golFatti       the gol fatti
     * @param golSubiti      the gol subiti
     * @param dataRitiro     the data ritiro
     */
    public RisultatoUtente(JFrame frameChiamante, Controller controller, String nome, String cognome, char sesso,
                           String squadra, String nazionalita, String piede, Integer eta, String ruolo, Integer golFatti,
                           Integer golSubiti, LocalDate dataRitiro) {
        frame = new JFrame("Risultato ricerca");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        tableRicercaUtente.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableRicercaUtente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore",
                "Nome", "Cognome", "Piede", "Sesso", "Data di nascita", "Data di ritiro", "idSquadra", "Squadra",
                "Nazionalita", "Ruolo", "Gol fatti", "Gol subiti"});
        try {
            tableModel = controller.getCalciatori(nome, cognome, sesso, squadra, nazionalita, piede, eta,
                    ruolo, golFatti, golSubiti, dataRitiro);
        } catch (CalciatoriNonTrovatiException e) {
            JOptionPane.showMessageDialog(null, "Calciatori non trovati");
        }
        tableRicercaUtente.setModel(tableModel);

        tableRicercaUtente.getColumnModel().getColumn(10).setPreferredWidth(350);
        tableRicercaUtente.getColumnModel().getColumn(8).setPreferredWidth(150);
        tableRicercaUtente.getColumnModel().getColumn(0).setMaxWidth(0);
        tableRicercaUtente.getColumnModel().getColumn(0).setMinWidth(0);
        tableRicercaUtente.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableRicercaUtente.getColumnModel().getColumn(7).setMaxWidth(0);
        tableRicercaUtente.getColumnModel().getColumn(7).setMinWidth(0);
        tableRicercaUtente.getColumnModel().getColumn(7).setPreferredWidth(0);


        buttonNuovaRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }

}
