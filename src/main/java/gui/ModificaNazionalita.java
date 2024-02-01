package gui;

import controller.Controller;
import controller.NazionalitàGiàInseritaException;
import model.Appartiene;
import model.Nazionalita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * La gui Modifica Nazionalità permette di modificare le nazionalità di un calciatore se si è amministratori.
 */
public class ModificaNazionalita {
    private JPanel panel;
    private JTable tableModificaNazionalita;
    private JButton buttonAggiungiNazionalita;
    private JButton buttonRimuoviNazionalita;
    private JButton buttonIndietro;
    private JPanel panelModificaNazionalita;
    private JScrollPane scrollPaneModificaNazionalita;
    private JPanel panelAggiungiNazionalita;
    private JPanel panelRimuoviNazionalita;
    private JPanel panelIndietro;
    /**
     * The Controller.
     */
    Controller controller;

    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Modifica nazionalità.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     * @param idCalciatore   the id calciatore
     */
    public ModificaNazionalita(JFrame frameChiamante, Controller controller, int idCalciatore) {
        frame = new JFrame("Modifica nazionalità");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        tableModificaNazionalita.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));

        tableModificaNazionalita.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore",
                "Nazionalita"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModificaNazionalita.setModel(tableModel);
        tableModificaNazionalita.setModel(tableModel);
        tableModificaNazionalita.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModificaNazionalita.getColumnModel().getColumn(0).setMinWidth(0);
        tableModificaNazionalita.getColumnModel().getColumn(0).setPreferredWidth(0);

        for (Appartiene appartiene : controller.visualizzaNazionalitaCalciatore(idCalciatore)) {
            tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                    appartiene.getCalciatore().getCognome(), appartiene.getNazionalita().getNome()});
        }

        buttonAggiungiNazionalita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBoxNazionalita = new JComboBox();
                for (Nazionalita nazionalita : controller.getNazionalita()) {
                    comboBoxNazionalita.addItem(nazionalita.getNome());
                }
                JOptionPane.showMessageDialog(null, comboBoxNazionalita, "Inserisci la nazionalità",
                        JOptionPane.QUESTION_MESSAGE);
                String nazionalita = (String) comboBoxNazionalita.getSelectedItem();
                try {
                    controller.inserisciNazionalita(idCalciatore, nazionalita);
                    tableModel.setRowCount(0);
                    for (Appartiene appartiene : controller.visualizzaNazionalitaCalciatore(idCalciatore)) {
                        tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                                appartiene.getCalciatore().getCognome(), appartiene.getNazionalita().getNome()});
                    }
                    JOptionPane.showMessageDialog(null, "Nazionalita inserita con successo");
                } catch (NazionalitàGiàInseritaException ex) {
                    JOptionPane.showMessageDialog(null, "Nazionalita già inserita", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRimuoviNazionalita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModificaNazionalita.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Seleziona una riga");
                    return;
                }
                int idCalciatore = (Integer) tableModificaNazionalita.getValueAt(tableModificaNazionalita.getSelectedRow(),
                        0);
                ArrayList<String> nazionalita = new ArrayList<>();
                for (int indice : tableModificaNazionalita.getSelectedRows())
                    nazionalita.add((String) tableModificaNazionalita.getValueAt(indice, 2));
                controller.eliminaNazionalita(idCalciatore, nazionalita);
                JOptionPane.showMessageDialog(null, "Nazionalita eliminata con successo");
                tableModel.setRowCount(0);
                for (Appartiene appartiene : controller.visualizzaNazionalitaCalciatore(idCalciatore)) {
                    tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                            appartiene.getCalciatore().getCognome(), appartiene.getNazionalita().getNome()});
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
