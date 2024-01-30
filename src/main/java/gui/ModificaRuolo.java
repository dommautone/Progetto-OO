package gui;

import controller.Controller;
import controller.RuoloGiàInseritoException;
import model.Appartiene;
import model.Ha;
import model.Ruolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * La gui Modifica ruolo permette di modificare il ruolo.
 */
public class ModificaRuolo {
    private JPanel panel;
    private JTable tableModificaRuolo;
    private JButton buttonAggiungiRuolo;
    private JButton buttonEliminaRuolo;
    private JButton buttonIndietro;
    private JPanel panelModificaRUolo;
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
    public ModificaRuolo(JFrame frameChiamante, Controller controller, int idCalciatore, String nome, String cognome, String ruolo) {
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
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"idCalciatore", "Calciatore", "Ruolo"});
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
                JOptionPane.showMessageDialog(null, comboBoxRuolo, "Inserisci il ruolo", JOptionPane.QUESTION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Ruolo già inserito", "Errore", JOptionPane.ERROR_MESSAGE);
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
        panelModificaRUolo = new JPanel();
        panelModificaRUolo.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelModificaRUolo, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPaneModificaRuolo = new JScrollPane();
        panelModificaRUolo.add(scrollPaneModificaRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableModificaRuolo = new JTable();
        scrollPaneModificaRuolo.setViewportView(tableModificaRuolo);
        panelAggiungiRuolo = new JPanel();
        panelAggiungiRuolo.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelAggiungiRuolo, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonAggiungiRuolo = new JButton();
        buttonAggiungiRuolo.setText("Aggiungi ruolo");
        panelAggiungiRuolo.add(buttonAggiungiRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelEliminaRuolo = new JPanel();
        panelEliminaRuolo.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelEliminaRuolo, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonEliminaRuolo = new JButton();
        buttonEliminaRuolo.setText("Elimina ruolo");
        panelEliminaRuolo.add(buttonEliminaRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelIndietro = new JPanel();
        panelIndietro.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelIndietro, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonIndietro = new JButton();
        buttonIndietro.setText("Torna indietro");
        panelIndietro.add(buttonIndietro, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * $$$ get root component $$$ j component.
     *
     * @return the j component
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
