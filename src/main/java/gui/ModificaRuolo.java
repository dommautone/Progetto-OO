package gui;

import controller.Controller;
import controller.RuoloGiàInseritoException;
import model.Ha;
import model.Ruolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * La gui Modifica Ruolo permette di modificare i ruoli di un calciatore se si è amministratori.
 */
public class ModificaRuolo {
    private JPanel panel;
    private JTable tableModificaRuolo;
    private JButton buttonAggiungiRuolo;
    private JButton buttonEliminaRuolo;
    private JButton buttonIndietro;
    private JPanel panelModificaRuolo;
    private JScrollPane scrollPaneModificaRuolo;
    private JPanel panelAggiungiRuolo;
    private JPanel panelEliminaRuolo;
    private JPanel panelIndietro;
    private Controller controller;

    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Modifica ruolo.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     * @param idCalciatore   the id calciatore
     * @param nome           the nome
     * @param cognome        the cognome
     * @param ruolo          the ruolo
     */
    public ModificaRuolo(JFrame frameChiamante, Controller controller, int idCalciatore, String nome, String cognome,
                         String ruolo) {
        frame = new JFrame("Modifica ruolo");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        tableModificaRuolo.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableModificaRuolo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore",
                "Ruolo"});
        tableModificaRuolo.setModel(tableModel);
        tableModificaRuolo.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModificaRuolo.getColumnModel().getColumn(0).setMinWidth(0);
        tableModificaRuolo.getColumnModel().getColumn(0).setPreferredWidth(0);

        String calciatore = nome + " " + cognome;
        for (String ruolo1 : ruolo.split("/"))
            tableModel.addRow(new Object[]{idCalciatore, calciatore, ruolo1});

        buttonAggiungiRuolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBoxRuolo = new JComboBox();
                for (Ruolo ruolo : controller.getRuoli()) {
                    comboBoxRuolo.addItem(ruolo.getPosizione());
                }
                JOptionPane.showMessageDialog(null, comboBoxRuolo, "Inserisci il ruolo",
                        JOptionPane.QUESTION_MESSAGE);
                String ruolo = (String) comboBoxRuolo.getSelectedItem();
                try {
                    controller.inserisciRuolo(idCalciatore, ruolo);
                    tableModel.setRowCount(0);
                    for (Ha ha : controller.visualizzaRuoloCalciatore(idCalciatore)) {
                        tableModel.addRow(new Object[]{idCalciatore, ha.getCalciatore().getNome() + " " +
                                ha.getCalciatore().getCognome(), ha.getRuolo().getPosizione()});
                    }
                    JOptionPane.showMessageDialog(null, "Ruolo inserito con successo");
                } catch (RuoloGiàInseritoException ex) {
                    JOptionPane.showMessageDialog(null, "Ruolo già inserito", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonEliminaRuolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModificaRuolo.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Seleziona una riga");
                    return;
                }
                int idCalciatore = (int) tableModificaRuolo.getValueAt(tableModificaRuolo.getSelectedRow(), 0);
                ArrayList<String> ruolo = new ArrayList<>();
                for (int indice : tableModificaRuolo.getSelectedRows())
                    ruolo.add((String) tableModificaRuolo.getValueAt(indice, 2));
                controller.eliminaRuolo(idCalciatore, ruolo);
                JOptionPane.showMessageDialog(null, "Ruolo eliminato con successo");
                tableModel.setRowCount(0);
                for (Ha ha : controller.visualizzaRuoloCalciatore(idCalciatore)) {
                    tableModel.addRow(new Object[]{idCalciatore, ha.getCalciatore().getNome() + " " +
                            ha.getCalciatore().getCognome(), ha.getRuolo().getPosizione()});
                }
            }
        });

        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }

}
