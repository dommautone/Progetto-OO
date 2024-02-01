package gui;

import controller.Controller;
import model.Militanza;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModificaSquadra {
    private JPanel panel;
    private JTable tableModificaSquadra;
    private JButton buttonIndietro;
    private JButton buttonAggiungiSquadra;
    private JButton buttonEliminaSquadra;
    private JPanel panelIndietro;
    private JPanel panelEliminaSquadra;
    private JPanel panelAggiungiSquadra;
    private JScrollPane scrollPaneModificaSquadra;
    private JPanel panelModificaSquadra;

    private Controller controller;

    public static JFrame frame;
    /**
     * La gui Modifica Squadra permette di modificare la squadra di un calciatore se si è amministratori.
     */
    public ModificaSquadra(JFrame frameChiamante, Controller controller, int idCalciatore, char sesso) {
        frame = new JFrame("Modifica squadra");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tableModificaSquadra.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableModificaSquadra.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore",
                "idSquadra", "Squadra", "Data inizio", "Data fine", "Partite giocate", "Gol segnati", "Gol subiti"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModificaSquadra.setModel(tableModel);
        tableModificaSquadra.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(0).setMinWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setMaxWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setMinWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setPreferredWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(3).setPreferredWidth(150);

        for (Militanza militanza : controller.visualizzaSquadreCalciatore(idCalciatore)) {
            tableModel.addRow(new Object[]{idCalciatore, militanza.getCalciatore().getNome()+ " " +
                    militanza.getCalciatore().getCognome(), militanza.getSquadra().getIdSquadra(),
                    militanza.getSquadra().getNome(), militanza.getDataInizio(), militanza.getDataFine(),
                    militanza.getPartiteGiocate(), militanza.getGolSegnati(), militanza.getGolSubiti()});
        }

        buttonAggiungiSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiSquadra aggiungiSquadra = new AggiungiSquadra(frame, controller, idCalciatore, sesso);
                aggiungiSquadra.frame.setVisible(true);
                frame.dispose();
            }
        });

        buttonEliminaSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModificaSquadra.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Seleziona una riga");
                    return;
                }
                int idCalciatore = (int) tableModificaSquadra.getValueAt(tableModificaSquadra.getSelectedRow(), 0);
                ArrayList<Integer> eliminaMilitanza = new ArrayList<>();
                for (int indice : tableModificaSquadra.getSelectedRows())
                    eliminaMilitanza.add((Integer) tableModificaSquadra.getValueAt(indice, 2));
                controller.eliminaSquadra(idCalciatore, eliminaMilitanza);
                JOptionPane.showMessageDialog(null, "Squadra eliminata con successo");
                tableModel.setRowCount(0);
                for (Militanza militanza : controller.visualizzaSquadreCalciatore(idCalciatore)) {
                    tableModel.addRow(new Object[]{idCalciatore, militanza.getCalciatore().getNome() + " " +
                            militanza.getCalciatore().getCognome(), militanza.getSquadra().getIdSquadra(),
                            militanza.getSquadra().getNome(),militanza.getDataInizio(), militanza.getDataFine(),
                            militanza.getPartiteGiocate(), militanza.getGolSegnati(), militanza.getGolSubiti() });
                }
            }});
        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modifica.frame.setVisible(true);
                frame.dispose();
            }
        });
    }
}


