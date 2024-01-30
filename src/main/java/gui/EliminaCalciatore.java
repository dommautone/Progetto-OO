package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The type Elimina calciatore.
 */
public class EliminaCalciatore {
    private JPanel panel;
    private JTable tableGiocatori;
    private JPanel panelGiocatori;
    private JButton buttonElimina;
    private JPanel panelElimina;
    private JScrollPane scrollpaneTabella;
    private JPanel panelIndietro;
    private JButton buttonIndietro;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private Controller controller;

    /**
     * Instantiates a new Elimina calciatore.
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
    public EliminaCalciatore(JFrame frameChiamante, Controller controller, String nome, String cognome, char sesso,
                             String squadra, String nazionalita, String piede, Integer eta, String ruolo,
                             Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
        frame = new JFrame("Elimina calciatore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        tableGiocatori.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        DefaultTableModel tableModel = controller.getCalciatori(nome, cognome, sesso, squadra, nazionalita, piede, eta,
                ruolo, golFatti, golSubiti, dataRitiro);
        tableGiocatori.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableGiocatori.setModel(tableModel);

        tableGiocatori.getColumnModel().getColumn(10).setPreferredWidth(350);
        tableGiocatori.getColumnModel().getColumn(8).setPreferredWidth(150);
        tableGiocatori.getColumnModel().getColumn(0).setMaxWidth(0);
        tableGiocatori.getColumnModel().getColumn(0).setMinWidth(0);
        tableGiocatori.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableGiocatori.getColumnModel().getColumn(7).setMaxWidth(0);
        tableGiocatori.getColumnModel().getColumn(7).setMinWidth(0);
        tableGiocatori.getColumnModel().getColumn(7).setPreferredWidth(0);

        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PannelloAmministratore pannelloAmministratore = new PannelloAmministratore(frame, controller);
                pannelloAmministratore.frame.setVisible(true);
                frame.dispose();
            }
        });
        buttonElimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableGiocatori.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Seleziona una riga");
                    return;
                }
                ArrayList<Integer> idCalciatore = new ArrayList<>();
                for (int indice : tableGiocatori.getSelectedRows()) {
                    idCalciatore.add((Integer) tableGiocatori.getValueAt(indice, 0));
                }
                controller.eliminaCalciatore(idCalciatore);
                JOptionPane.showMessageDialog(null, "Calciatore eliminato con successo");
                int indice[] = tableGiocatori.getSelectedRows();
                for (int riga : indice) {
                    tableModel.removeRow(riga);
                }
                tableGiocatori.setModel(controller.getCalciatori(nome, cognome, sesso, squadra, nazionalita, piede, eta,
                        ruolo, golFatti, golSubiti, dataRitiro));

                tableGiocatori.getColumnModel().getColumn(10).setPreferredWidth(350);
                tableGiocatori.getColumnModel().getColumn(8).setPreferredWidth(150);
                tableGiocatori.getColumnModel().getColumn(0).setMaxWidth(0);
                tableGiocatori.getColumnModel().getColumn(0).setMinWidth(0);
                tableGiocatori.getColumnModel().getColumn(0).setPreferredWidth(0);
                tableGiocatori.getColumnModel().getColumn(7).setMaxWidth(0);
                tableGiocatori.getColumnModel().getColumn(7).setMinWidth(0);
                tableGiocatori.getColumnModel().getColumn(7).setPreferredWidth(0);
            }
        });
    }

}