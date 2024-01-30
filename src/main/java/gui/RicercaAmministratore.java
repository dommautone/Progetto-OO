package gui;

import controller.Controller;
import model.Nazionalita;
import model.Ruolo;
import model.Squadra;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static gui.PannelloAmministratore.Flag;

/**
 * The type Ricerca amministratore.
 */
public class RicercaAmministratore {
    private JPanel panelNome;
    private JCheckBox checkBoxNome;
    private JLabel labelNome;
    private JTextField textNome;
    private JPanel panelCognome;
    private JCheckBox checkBoxCognome;
    private JLabel labelCognome;
    private JTextField textCognome;
    private JPanel panelSquadra;
    private JCheckBox checkBoxSquadra;
    private JLabel labelSquadra;
    private JComboBox comboBoxSquadra;
    private JPanel panelNazionalità;
    private JCheckBox checkBoxNazionalità;
    private JLabel labelNazionalità;
    private JComboBox comboBoxNazionalità;
    private JPanel panelPiede;
    private JCheckBox checkBoxPiede;
    private JLabel labelPiede;
    private JComboBox comboBoxPiede;
    private JPanel panelRuolo;
    private JCheckBox checkBoxRuolo;
    private JLabel labelRuolo;
    private JComboBox comboBoxRuolo;
    private JPanel panelGolFatti;
    private JCheckBox checkBoxGolFatti;
    private JLabel labelGolFatti;
    private JTextField textFieldGolFatti;
    private JPanel panelGolSubiti;
    private JCheckBox checkBoxGolSubiti;
    private JLabel labelGolSubiti;
    private JTextField textGolSubiti;
    private JPanel panelGenere;
    private JLabel labelGenere;
    private JRadioButton radioButtonMaschio;
    private JRadioButton radioButtonFemmina;
    private JCheckBox checkBoxGenere;
    private JPanel panelEtà;
    private JCheckBox checkboxEtà;
    private JLabel labelEtà;
    private JTextField textEtà;
    private JPanel panel;
    private JButton buttonRicerca;
    private JPanel panelRicerca;
    private JPanel panelRicercaCalciatore;
    private JLabel labelRicercaCalciatore;
    private JPanel panelDataRitiro;
    private JCheckBox checkBoxDataRitiro;
    private JRadioButton radioButtonRitirati;
    private JRadioButton radioButtonNonRitirati;
    private JRadioButton radioButtonDataRitiro;
    private JTextField textDataRitiro;
    private ButtonGroup buttonGroupSesso;
    private ButtonGroup buttonGroupRitiro;
    private Controller controller;
    private Integer golFatti;
    private Integer golSubiti;
    private Integer età;
    private LocalDate dataRitiro;
    private char sesso;


    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Ricerca amministratore.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public RicercaAmministratore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Ricerca Amministratore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        buttonGroupSesso = new ButtonGroup();
        buttonGroupSesso.add(radioButtonMaschio);
        buttonGroupSesso.add(radioButtonFemmina);
        textNome.setEnabled(false);
        textCognome.setEnabled(false);
        comboBoxSquadra.setEnabled(false);
        comboBoxNazionalità.setEnabled(false);
        comboBoxPiede.setEnabled(false);
        comboBoxRuolo.setEnabled(false);
        textFieldGolFatti.setEnabled(false);
        textGolSubiti.setEnabled(false);
        textEtà.setEnabled(false);
        radioButtonMaschio.setEnabled(false);
        radioButtonFemmina.setEnabled(false);
        comboBoxPiede.addItem("");
        comboBoxPiede.addItem("Destro");
        comboBoxPiede.addItem("Sinistro");
        comboBoxPiede.addItem("Ambidestro");
        comboBoxRuolo.addItem("");
        comboBoxNazionalità.addItem("");
        comboBoxSquadra.addItem("");
        buttonGroupRitiro = new ButtonGroup();
        buttonGroupRitiro.add(radioButtonRitirati);
        buttonGroupRitiro.add(radioButtonNonRitirati);
        buttonGroupRitiro.add(radioButtonDataRitiro);
        radioButtonRitirati.setEnabled(false);
        radioButtonDataRitiro.setEnabled(false);
        radioButtonNonRitirati.setEnabled(false);

        for (Ruolo ruolo : controller.getRuoli()) {
            comboBoxRuolo.addItem(ruolo.getPosizione());
        }
        for (Nazionalita nazionalita : controller.getNazionalità()) {
            comboBoxNazionalità.addItem(nazionalita.getNome());
        }
        for (Squadra squadra : controller.getSquadre()) {
            comboBoxSquadra.addItem(squadra.getNome());
        }

        checkBoxNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxNome.isSelected()) {
                    textNome.setEnabled(true);
                } else {
                    textNome.setEnabled(false);
                    textNome.setText("");
                }
            }
        });
        textNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        checkBoxCognome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxCognome.isSelected()) {
                    textCognome.setEnabled(true);
                } else {
                    textCognome.setEnabled(false);
                    textCognome.setText("");
                }
            }
        });
        textCognome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }
        });
        checkBoxSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxSquadra.isSelected()) {
                    comboBoxSquadra.setEnabled(true);
                } else {
                    comboBoxSquadra.setEnabled(false);
                    comboBoxSquadra.setSelectedIndex(0);
                }
            }
        });
        checkBoxNazionalità.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxNazionalità.isSelected()) {
                    comboBoxNazionalità.setEnabled(true);
                } else {
                    comboBoxNazionalità.setEnabled(false);
                    comboBoxNazionalità.setSelectedIndex(0);
                }
            }
        });
        checkBoxGenere.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxGenere.isSelected()) {
                    radioButtonMaschio.setEnabled(true);
                    radioButtonFemmina.setEnabled(true);
                } else {
                    radioButtonMaschio.setEnabled(false);
                    radioButtonFemmina.setEnabled(false);
                    buttonGroupSesso.clearSelection();
                }
            }
        });
        checkBoxPiede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxPiede.isSelected()) {
                    comboBoxPiede.setEnabled(true);
                } else {
                    comboBoxPiede.setEnabled(false);
                    comboBoxPiede.setSelectedIndex(0);
                }
            }
        });
        checkBoxRuolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxRuolo.isSelected()) {
                    comboBoxRuolo.setEnabled(true);
                } else {
                    comboBoxRuolo.setEnabled(false);
                    comboBoxRuolo.setSelectedIndex(0);
                }
            }
        });
        checkBoxGolFatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxGolFatti.isSelected()) {
                    textFieldGolFatti.setEnabled(true);
                } else {
                    textFieldGolFatti.setEnabled(false);
                    textFieldGolFatti.setText("");
                }
            }
        });
        checkBoxGolSubiti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxGolSubiti.isSelected()) {
                    textGolSubiti.setEnabled(true);
                } else {
                    textGolSubiti.setEnabled(false);
                    textGolSubiti.setText("");
                }
            }
        });
        checkboxEtà.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkboxEtà.isSelected()) {
                    textEtà.setEnabled(true);
                } else {
                    textEtà.setEnabled(false);
                    textEtà.setText("");
                }
            }
        });
        checkBoxDataRitiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxDataRitiro.isSelected()) {
                    radioButtonRitirati.setEnabled(true);
                    radioButtonNonRitirati.setEnabled(true);
                    radioButtonDataRitiro.setEnabled(true);
                } else {
                    radioButtonRitirati.setEnabled(false);
                    radioButtonNonRitirati.setEnabled(false);
                    radioButtonDataRitiro.setEnabled(false);
                    buttonGroupRitiro.clearSelection();
                    textDataRitiro.setEnabled(false);
                    textDataRitiro.setText("");
                }
            }
        });
        radioButtonDataRitiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonDataRitiro.isSelected()) {
                    textDataRitiro.setEnabled(true);
                } else {
                    textDataRitiro.setEnabled(false);
                    textDataRitiro.setText("");
                }
            }
        });
        radioButtonNonRitirati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonNonRitirati.isSelected()) {
                    textDataRitiro.setEnabled(false);
                    textDataRitiro.setText("");
                }
            }
        });
        radioButtonRitirati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonRitirati.isSelected()) {
                    textDataRitiro.setEnabled(false);
                    textDataRitiro.setText("");
                }
            }
        });
        checkBoxRuolo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxRuolo.isSelected()) {
                    comboBoxRuolo.setEnabled(true);
                } else {
                    comboBoxRuolo.setEnabled(false);
                    comboBoxRuolo.setSelectedIndex(0);
                }
            }
        });

        checkBoxGenere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxGenere.isSelected()) {
                    radioButtonMaschio.setEnabled(true);
                    radioButtonFemmina.setEnabled(true);
                } else {
                    radioButtonMaschio.setEnabled(false);
                    radioButtonFemmina.setEnabled(false);
                }
            }
        });


        buttonRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldGolFatti.getText().isEmpty())
                    golFatti = null;
                else
                    golFatti = Integer.parseInt(textFieldGolFatti.getText());
                if (textGolSubiti.getText().isEmpty())
                    golSubiti = null;
                else
                    golSubiti = Integer.parseInt(textGolSubiti.getText());
                if (textEtà.getText().isEmpty())
                    età = null;
                else
                    età = Integer.parseInt(textEtà.getText());
                if (textDataRitiro.getText().isEmpty() && !checkBoxDataRitiro.isSelected())
                    dataRitiro = LocalDate.of(1111, 1, 1);
                if (textDataRitiro.getText().isEmpty() && radioButtonDataRitiro.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Inserire una data di ritiro valida", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (radioButtonRitirati.isSelected())
                    dataRitiro = LocalDate.of(2222, 2, 2);
                else if (radioButtonNonRitirati.isSelected())
                    dataRitiro = null;
                else if (textDataRitiro.getText().isEmpty())
                    dataRitiro = LocalDate.of(1111, 1, 1);
                else
                    try {
                        dataRitiro = LocalDate.parse(textDataRitiro.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Inserire una data di ritiro valida", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                if (radioButtonFemmina.isSelected())
                    sesso = 'F';
                else if (radioButtonMaschio.isSelected())
                    sesso = 'M';
                else
                    sesso = 'A';
                if (Flag == 1) {
                    RicercaModifica ricercaModifica = new RicercaModifica(frame, controller, textNome.getText(), textCognome.getText(), sesso, comboBoxSquadra.getSelectedItem().toString(), comboBoxNazionalità.getSelectedItem().toString(),
                            comboBoxPiede.getSelectedItem().toString(), età, comboBoxRuolo.getSelectedItem().toString(), golFatti, golSubiti, dataRitiro);
                    ricercaModifica.frame.setVisible(true);
                    frame.dispose();
                } else {
                    EliminaCalciatore eliminaCalciatore = new EliminaCalciatore(frame, controller, textNome.getText(), textCognome.getText(), sesso, comboBoxSquadra.getSelectedItem().toString(), comboBoxNazionalità.getSelectedItem().toString(),
                            comboBoxPiede.getSelectedItem().toString(), età, comboBoxRuolo.getSelectedItem().toString(), golFatti, golSubiti, dataRitiro);
                    eliminaCalciatore.frame.setVisible(true);
                    frame.dispose();
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(13, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-4859649));
        panelNome = new JPanel();
        panelNome.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelNome.setBackground(new Color(-4859649));
        panel.add(panelNome, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxNome = new JCheckBox();
        checkBoxNome.setBackground(new Color(-4859649));
        checkBoxNome.setHorizontalTextPosition(0);
        checkBoxNome.setText("");
        panelNome.add(checkBoxNome, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelNome = new JLabel();
        labelNome.setText("Nome");
        panelNome.add(labelNome, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textNome = new JTextField();
        textNome.setText("");
        panelNome.add(textNome, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelCognome = new JPanel();
        panelCognome.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelCognome.setBackground(new Color(-4859649));
        panel.add(panelCognome, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxCognome = new JCheckBox();
        checkBoxCognome.setBackground(new Color(-4859649));
        checkBoxCognome.setText("");
        panelCognome.add(checkBoxCognome, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelCognome = new JLabel();
        labelCognome.setText("Cognome");
        panelCognome.add(labelCognome, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textCognome = new JTextField();
        panelCognome.add(textCognome, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        panelSquadra = new JPanel();
        panelSquadra.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelSquadra.setBackground(new Color(-4859649));
        panel.add(panelSquadra, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxSquadra = new JCheckBox();
        checkBoxSquadra.setBackground(new Color(-4859649));
        checkBoxSquadra.setText("");
        panelSquadra.add(checkBoxSquadra, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelSquadra = new JLabel();
        labelSquadra.setText("Squadra");
        panelSquadra.add(labelSquadra, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBoxSquadra = new JComboBox();
        panelSquadra.add(comboBoxSquadra, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelNazionalità = new JPanel();
        panelNazionalità.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelNazionalità.setBackground(new Color(-4859649));
        panel.add(panelNazionalità, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxNazionalità = new JCheckBox();
        checkBoxNazionalità.setBackground(new Color(-4859649));
        checkBoxNazionalità.setText("");
        panelNazionalità.add(checkBoxNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelNazionalità = new JLabel();
        labelNazionalità.setText("Nazionalità");
        panelNazionalità.add(labelNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBoxNazionalità = new JComboBox();
        panelNazionalità.add(comboBoxNazionalità, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelPiede = new JPanel();
        panelPiede.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelPiede.setBackground(new Color(-4859649));
        panel.add(panelPiede, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxPiede = new JCheckBox();
        checkBoxPiede.setBackground(new Color(-4859649));
        checkBoxPiede.setText("");
        panelPiede.add(checkBoxPiede, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelPiede = new JLabel();
        labelPiede.setText("Piede");
        panelPiede.add(labelPiede, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBoxPiede = new JComboBox();
        panelPiede.add(comboBoxPiede, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelRuolo = new JPanel();
        panelRuolo.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelRuolo.setBackground(new Color(-4859649));
        panel.add(panelRuolo, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxRuolo = new JCheckBox();
        checkBoxRuolo.setBackground(new Color(-4859649));
        checkBoxRuolo.setText("");
        panelRuolo.add(checkBoxRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelRuolo = new JLabel();
        labelRuolo.setText("Ruolo");
        panelRuolo.add(labelRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        comboBoxRuolo = new JComboBox();
        panelRuolo.add(comboBoxRuolo, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelGolFatti = new JPanel();
        panelGolFatti.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelGolFatti.setBackground(new Color(-4859649));
        panel.add(panelGolFatti, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxGolFatti = new JCheckBox();
        checkBoxGolFatti.setBackground(new Color(-4859649));
        checkBoxGolFatti.setText("");
        panelGolFatti.add(checkBoxGolFatti, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelGolFatti = new JLabel();
        labelGolFatti.setText("Gol Fatti");
        panelGolFatti.add(labelGolFatti, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textFieldGolFatti = new JTextField();
        panelGolFatti.add(textFieldGolFatti, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        panelGolSubiti = new JPanel();
        panelGolSubiti.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelGolSubiti.setBackground(new Color(-4859649));
        panel.add(panelGolSubiti, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxGolSubiti = new JCheckBox();
        checkBoxGolSubiti.setBackground(new Color(-4859649));
        checkBoxGolSubiti.setText("");
        panelGolSubiti.add(checkBoxGolSubiti, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelGolSubiti = new JLabel();
        labelGolSubiti.setText("Gol Subiti");
        panelGolSubiti.add(labelGolSubiti, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textGolSubiti = new JTextField();
        panelGolSubiti.add(textGolSubiti, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, -1), null, 0, false));
        panelGenere = new JPanel();
        panelGenere.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panelGenere.setBackground(new Color(-4859649));
        panel.add(panelGenere, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelGenere = new JLabel();
        labelGenere.setText("Genere");
        panelGenere.add(labelGenere, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonMaschio = new JRadioButton();
        radioButtonMaschio.setBackground(new Color(-4859649));
        radioButtonMaschio.setText("Maschio");
        panelGenere.add(radioButtonMaschio, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonFemmina = new JRadioButton();
        radioButtonFemmina.setBackground(new Color(-4859649));
        radioButtonFemmina.setText("Femmina");
        panelGenere.add(radioButtonFemmina, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxGenere = new JCheckBox();
        checkBoxGenere.setBackground(new Color(-4859649));
        checkBoxGenere.setText("");
        panelGenere.add(checkBoxGenere, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelEtà = new JPanel();
        panelEtà.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelEtà.setBackground(new Color(-4859649));
        panel.add(panelEtà, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkboxEtà = new JCheckBox();
        checkboxEtà.setBackground(new Color(-4859649));
        checkboxEtà.setHorizontalTextPosition(0);
        checkboxEtà.setText("");
        panelEtà.add(checkboxEtà, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelEtà = new JLabel();
        labelEtà.setText("Età");
        panelEtà.add(labelEtà, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textEtà = new JTextField();
        textEtà.setText("");
        panelEtà.add(textEtà, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panelRicerca = new JPanel();
        panelRicerca.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelRicerca.setBackground(new Color(-4859649));
        panel.add(panelRicerca, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonRicerca = new JButton();
        buttonRicerca.setText("Invio");
        panelRicerca.add(buttonRicerca, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelRicercaCalciatore = new JPanel();
        panelRicercaCalciatore.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelRicercaCalciatore.setBackground(new Color(-4859649));
        panel.add(panelRicercaCalciatore, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelRicercaCalciatore = new JLabel();
        Font labelRicercaCalciatoreFont = this.$$$getFont$$$("Calibri", Font.BOLD, 20, labelRicercaCalciatore.getFont());
        if (labelRicercaCalciatoreFont != null) labelRicercaCalciatore.setFont(labelRicercaCalciatoreFont);
        labelRicercaCalciatore.setText("Ricerca calciatore");
        panelRicercaCalciatore.add(labelRicercaCalciatore, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelDataRitiro = new JPanel();
        panelDataRitiro.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelDataRitiro.setBackground(new Color(-4859649));
        panel.add(panelDataRitiro, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        checkBoxDataRitiro = new JCheckBox();
        checkBoxDataRitiro.setBackground(new Color(-4859649));
        checkBoxDataRitiro.setText("Ritiro");
        panelDataRitiro.add(checkBoxDataRitiro, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonRitirati = new JRadioButton();
        radioButtonRitirati.setBackground(new Color(-4859649));
        radioButtonRitirati.setText("Visualizza tutti i calciatori ritirati");
        panelDataRitiro.add(radioButtonRitirati, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonNonRitirati = new JRadioButton();
        radioButtonNonRitirati.setBackground(new Color(-4859649));
        radioButtonNonRitirati.setText("Visualizza tutti i calciatori non ritirati");
        panelDataRitiro.add(radioButtonNonRitirati, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonDataRitiro = new JRadioButton();
        radioButtonDataRitiro.setBackground(new Color(-4859649));
        radioButtonDataRitiro.setText("Calciatori ritirati in data:");
        panelDataRitiro.add(radioButtonDataRitiro, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textDataRitiro = new JTextField();
        panelDataRitiro.add(textDataRitiro, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}




