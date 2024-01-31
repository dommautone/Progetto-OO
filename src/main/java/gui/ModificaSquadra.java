package gui;

import controller.Controller;
import model.Ha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModificaSquadra {
    private JPanel panelModificaSquadra;
    private JScrollPane scrollPaneModificaSquadra;
    private JTable tableModificaSquadra;
    private JPanel panelAggiungiSquadra;
    private JButton buttonAggiungiSquadra;
    private JPanel panelEliminaSquadra;
    private JButton buttonEliminaSquadra;
    private JPanel panelIndietro;
    private JButton buttonIndietro;

    public static JFrame frame;
    /**
     * La gui Modifica Squadra permette di modificare la squadra di un calciatore se si è amministratori.
     */
    public ModificaSquadra(JFrame frameChiamante, Controller controller, int idCalciatore, int idSquadra, String nomeSquadra, String nome, String cognome) {
        frame = new JFrame("Modifica squadra");
        frame.setContentPane(panelModificaSquadra);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        tableModificaSquadra.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        tableModificaSquadra.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore",
                "idSquadra", "Squadra"});
        tableModificaSquadra.setModel(tableModel);
        tableModificaSquadra.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(0).setMinWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setMaxWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setMinWidth(0);
        tableModificaSquadra.getColumnModel().getColumn(2).setPreferredWidth(0);


        buttonAggiungiSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                ArrayList<String> ruolo = new ArrayList<>();
                for (int indice : tableModificaSquadra.getSelectedRows())
                    ruolo.add((String) tableModificaSquadra.getValueAt(indice, 2));
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


