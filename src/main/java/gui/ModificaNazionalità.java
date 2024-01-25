package gui;

import controller.Controller;
import controller.NazionalitàGiàInseritaException;
import model.Appartiene;
import model.Ha;
import model.Nazionalità;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModificaNazionalità {
    private JPanel panel;
    private JTable tableModificaNazionalità;
    private JButton buttonAggiungiNazionalità;
    private JButton buttonRimuoviNazionalità;
    private JButton buttonIndietro;
    private JPanel panelModificaNazionalità;
    private JScrollPane scrollPaneModificaNazionalità;
    private JPanel panelAggiungiNazionalità;
    private JPanel panelRimuoviNazionalità;
    private JPanel panelIndietro;
    Controller controller;

    public static JFrame frame;

    public ModificaNazionalità(JFrame frameChiamante, Controller controller, int idCalciatore) {
        frame = new JFrame("Modifica nazionalità");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        tableModificaNazionalità.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));

        tableModificaNazionalità.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore", "Nazionalità"});
        tableModificaNazionalità.setModel(tableModel);
        tableModificaNazionalità.setModel(tableModel);
        tableModificaNazionalità.getColumnModel().getColumn(0).setMaxWidth(0);
        tableModificaNazionalità.getColumnModel().getColumn(0).setMinWidth(0);
        tableModificaNazionalità.getColumnModel().getColumn(0).setPreferredWidth(0);

        for (Appartiene appartiene : controller.visualizzaNazionalitàCalciatore(idCalciatore)) {
            tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                    appartiene.getCalciatore().getCognome(), appartiene.getNazionalità().getNome()});
        }

        buttonAggiungiNazionalità.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBoxNazionalità = new JComboBox();
                for (Nazionalità nazionalità : controller.getNazionalità()) {
                    comboBoxNazionalità.addItem(nazionalità.getNome());
                }
                JOptionPane.showMessageDialog(null, comboBoxNazionalità, "Inserisci la nazionalità", JOptionPane.QUESTION_MESSAGE);
                String nazionalità = (String) comboBoxNazionalità.getSelectedItem();
                try {
                    controller.inserisciNazionalità(idCalciatore, nazionalità);
                    tableModel.setRowCount(0);
                    for (Appartiene appartiene : controller.visualizzaNazionalitàCalciatore(idCalciatore)) {
                        tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                                appartiene.getCalciatore().getCognome(), appartiene.getNazionalità().getNome()});
                    }
                    JOptionPane.showMessageDialog(null, "Nazionalità inserita con successo");
                } catch (NazionalitàGiàInseritaException ex) {
                    JOptionPane.showMessageDialog(null, "Nazionalità già inserita", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRimuoviNazionalità.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModificaNazionalità.getSelectedRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Seleziona una riga");
                    return;
                }
                int idCalciatore = (Integer) tableModificaNazionalità.getValueAt(tableModificaNazionalità.getSelectedRow(), 0);
                ArrayList<String> nazionalità = new ArrayList<>();
                for (int indice : tableModificaNazionalità.getSelectedRows())
                    nazionalità.add((String) tableModificaNazionalità.getValueAt(indice, 2));
                controller.eliminaNazionalità(idCalciatore, nazionalità);
                JOptionPane.showMessageDialog(null, "Nazionalità eliminata con successo");
                tableModel.setRowCount(0);
                for (Appartiene appartiene : controller.visualizzaNazionalitàCalciatore(idCalciatore)) {
                    tableModel.addRow(new Object[]{idCalciatore, appartiene.getCalciatore().getNome() + " " +
                            appartiene.getCalciatore().getCognome(), appartiene.getNazionalità().getNome()});
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelModificaNazionalità = new JPanel();
        panelModificaNazionalità.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelModificaNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPaneModificaNazionalità = new JScrollPane();
        panelModificaNazionalità.add(scrollPaneModificaNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableModificaNazionalità = new JTable();
        scrollPaneModificaNazionalità.setViewportView(tableModificaNazionalità);
        panelAggiungiNazionalità = new JPanel();
        panelAggiungiNazionalità.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelAggiungiNazionalità, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonAggiungiNazionalità = new JButton();
        buttonAggiungiNazionalità.setText("Aggiungi nazionalità");
        panelAggiungiNazionalità.add(buttonAggiungiNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelRimuoviNazionalità = new JPanel();
        panelRimuoviNazionalità.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelRimuoviNazionalità, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonRimuoviNazionalità = new JButton();
        buttonRimuoviNazionalità.setText("Rimuovi nazionalità");
        panelRimuoviNazionalità.add(buttonRimuoviNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelIndietro = new JPanel();
        panelIndietro.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelIndietro, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonIndietro = new JButton();
        buttonIndietro.setText("Torna indietro");
        panelIndietro.add(buttonIndietro, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
