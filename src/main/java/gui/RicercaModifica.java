package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

/**
 * The type Ricerca modifica.
 */
public class RicercaModifica {

    private JPanel panel;
    private JTable tableModifica;
    private JPanel panelModifica;
    private JScrollPane scrollPaneModifica;
    private JButton buttonSelezione;
    private JButton buttonIndietro;
    private JPanel panelSelezione;
    private JPanel panelIndietro;
    private Controller controller;

    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Ricerca modifica.
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
    public RicercaModifica(JFrame frameChiamante, Controller controller, String nome, String cognome, char sesso,
                           String squadra, String nazionalita, String piede, Integer eta, String ruolo, Integer golFatti,
                           Integer golSubiti, LocalDate dataRitiro) {
        frame = new JFrame("Ricerca e modifica");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        tableModifica.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableModifica.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = controller.getCalciatori(nome, cognome, sesso, squadra, nazionalita, piede, eta,
                ruolo, golFatti, golSubiti, dataRitiro);
        tableModifica.setModel(tableModel);
        tableModifica.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModifica.getColumnModel().getColumn(8).setPreferredWidth(350);
        tableModifica.getColumnModel().getColumn(6).setPreferredWidth(150);
        tableModifica.getColumnModel().getColumn(10).setPreferredWidth(350);
        tableModifica.getColumnModel().getColumn(8).setPreferredWidth(150);
        tableModifica.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModifica.getColumnModel().getColumn(0).setMinWidth(0);
        tableModifica.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableModifica.getColumnModel().getColumn(7).setMaxWidth(0);
        tableModifica.getColumnModel().getColumn(7).setMinWidth(0);
        tableModifica.getColumnModel().getColumn(7).setPreferredWidth(0);


        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PannelloAmministratore pannelloAmministratore = new PannelloAmministratore(frame, controller);
                pannelloAmministratore.frame.setVisible(true);
                frame.dispose();
            }
        });
        buttonSelezione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModifica.getSelectedRow() == -1)
                    JOptionPane.showMessageDialog(null, "Seleziona un calciatore", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                else {
                    int riga = tableModifica.getSelectedRow();
                    int idCalciatore = (Integer) tableModifica.getValueAt(riga, 0);
                    String nome = (String) tableModifica.getValueAt(riga, 1);
                    String cognome = (String) tableModifica.getValueAt(riga, 2);
                    String piede = (String) tableModifica.getValueAt(riga, 3);
                    String sessoS = (String) tableModifica.getValueAt(riga, 4);

                    char sesso = sessoS.charAt(0);
                    Date dataNascitaD = (Date) tableModifica.getValueAt(riga, 5);
                    LocalDate dataNascita = dataNascitaD.toLocalDate();
                    Date dataRitiroD = (Date) tableModifica.getValueAt(riga, 6);
                    LocalDate dataRitiro;
                    if (dataRitiroD == null)
                        dataRitiro = null;
                    else
                        dataRitiro = dataRitiroD.toLocalDate();
                    int idSquadra = (Integer) tableModifica.getValueAt(riga, 7);
                    String squadra = (String) tableModifica.getValueAt(riga, 8);
                    String nazionalita = (String) tableModifica.getValueAt(riga, 9);
                    String ruolo = (String) tableModifica.getValueAt(riga, 10);
                    Integer golFatti = (Integer) tableModifica.getValueAt(riga, 11);
                    Integer golSubiti = (Integer) tableModifica.getValueAt(riga, 12);
                    Modifica modifica = new Modifica(frame, controller, idCalciatore, idSquadra, nome, cognome, piede,
                            sesso, dataNascita, dataRitiro,
                            squadra, golFatti, golSubiti, ruolo, nazionalita);
                    modifica.frame.setVisible(true);
                    frame.setVisible(false);
                }
            }
        });
    }

}
