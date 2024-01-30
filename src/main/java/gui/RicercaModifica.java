package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

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
     * @param nazionalità    the nazionalità
     * @param piede          the piede
     * @param età            the età
     * @param ruolo          the ruolo
     * @param golFatti       the gol fatti
     * @param golSubiti      the gol subiti
     * @param dataRitiro     the data ritiro
     */
    public RicercaModifica(JFrame frameChiamante, Controller controller, String nome, String cognome, char sesso, String squadra, String nazionalità,
                           String piede, Integer età, String ruolo, Integer golFatti, Integer golSubiti, LocalDate dataRitiro) {
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
        DefaultTableModel tableModel = controller.getCalciatori(nome, cognome, sesso, squadra, nazionalità, piede, età, ruolo, golFatti, golSubiti, dataRitiro);
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
                    JOptionPane.showMessageDialog(null, "Seleziona un calciatore", "Errore", JOptionPane.ERROR_MESSAGE);
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
                    String nazionalità = (String) tableModifica.getValueAt(riga, 9);
                    String ruolo = (String) tableModifica.getValueAt(riga, 10);
                    Integer golFatti = (Integer) tableModifica.getValueAt(riga, 11);
                    Integer golSubiti = (Integer) tableModifica.getValueAt(riga, 12);
                    Modifica modifica = new Modifica(frame, controller, idCalciatore, idSquadra, nome, cognome, piede, sesso, dataNascita, dataRitiro,
                            squadra, golFatti, golSubiti, ruolo, nazionalità);
                    modifica.frame.setVisible(true);
                    frame.setVisible(false);
                }
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelModifica = new JPanel();
        panelModifica.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelModifica, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPaneModifica = new JScrollPane();
        panelModifica.add(scrollPaneModifica, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableModifica = new JTable();
        Font tableModificaFont = this.$$$getFont$$$(null, -1, 16, tableModifica.getFont());
        if (tableModificaFont != null) tableModifica.setFont(tableModificaFont);
        scrollPaneModifica.setViewportView(tableModifica);
        panelSelezione = new JPanel();
        panelSelezione.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelSelezione, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonSelezione = new JButton();
        buttonSelezione.setText("Seleziona");
        panelSelezione.add(buttonSelezione, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelIndietro = new JPanel();
        panelIndietro.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panelIndietro, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
