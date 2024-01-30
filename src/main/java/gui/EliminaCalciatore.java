package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

/**
 * La gui EliminaCalciatore permette di eliminare un calciatore se si è eseguiti l'accesso come amministratore.
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
     * @param nazionalità    the nazionalità
     * @param piede          the piede
     * @param età            the età
     * @param ruolo          the ruolo
     * @param golFatti       the gol fatti
     * @param golSubiti      the gol subiti
     * @param dataRitiro     the data ritiro
     */
    public EliminaCalciatore(JFrame frameChiamante, Controller controller, String nome, String cognome, char sesso, String squadra, String nazionalità,
                             String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
        frame = new JFrame("Elimina calciatore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        tableGiocatori.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        DefaultTableModel tableModel = controller.getCalciatori(nome, cognome, sesso, squadra, nazionalità, piede, età, ruolo, golFatti, golSubiti, dataRitiro);
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
                tableGiocatori.setModel(controller.getCalciatori(nome, cognome, sesso, squadra, nazionalità, piede, età, ruolo, golFatti, golSubiti, dataRitiro));

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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-2104859));
        panelGiocatori = new JPanel();
        panelGiocatori.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelGiocatori.setBackground(new Color(-4859649));
        panel.add(panelGiocatori, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollpaneTabella = new JScrollPane();
        scrollpaneTabella.setBackground(new Color(-13947600));
        scrollpaneTabella.setEnabled(false);
        Font scrollpaneTabellaFont = this.$$$getFont$$$(null, -1, -1, scrollpaneTabella.getFont());
        if (scrollpaneTabellaFont != null) scrollpaneTabella.setFont(scrollpaneTabellaFont);
        panelGiocatori.add(scrollpaneTabella, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableGiocatori = new JTable();
        Font tableGiocatoriFont = this.$$$getFont$$$(null, -1, 16, tableGiocatori.getFont());
        if (tableGiocatoriFont != null) tableGiocatori.setFont(tableGiocatoriFont);
        scrollpaneTabella.setViewportView(tableGiocatori);
        panelElimina = new JPanel();
        panelElimina.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelElimina.setBackground(new Color(-4859649));
        panel.add(panelElimina, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonElimina = new JButton();
        buttonElimina.setHorizontalTextPosition(0);
        buttonElimina.setIcon(new ImageIcon(getClass().getResource("/icona cestino.png")));
        buttonElimina.setText("");
        panelElimina.add(buttonElimina, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelIndietro = new JPanel();
        panelIndietro.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, 5));
        panelIndietro.setBackground(new Color(-2104859));
        panel.add(panelIndietro, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonIndietro = new JButton();
        buttonIndietro.setText("Torna indietro");
        panelIndietro.add(buttonIndietro, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
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