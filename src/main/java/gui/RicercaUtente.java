package gui;

import controller.Controller;
import model.Nazionalita;
import model.Ruolo;
import model.Squadra;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Ricerca utente.
 */
public class RicercaUtente {
    private JPanel panel;
    private JPanel panelNome;
    private JPanel panelCognome;
    private JPanel panelSquadra;
    private JPanel panelNazionalita;
    private JPanel panelPiede;
    private JPanel panelEta;
    private JPanel panelRuolo;
    private JPanel panelGolFatti;
    private JPanel panelGolSubiti;
    private JPanel panelRicerca;
    private JCheckBox checkBoxNome;
    private JTextField textNome;
    private JLabel labelNome;
    private JCheckBox checkBoxCognome;
    private JLabel labelCognome;
    private JTextField textCognome;
    private JCheckBox checkBoxSquadra;
    private JCheckBox checkBoxNazionalita;
    private JCheckBox checkBoxPiede;
    private JCheckBox checkBoxEta;
    private JCheckBox checkBoxRuolo;
    private JCheckBox checkBoxGolFatti;
    private JCheckBox checkBoxGolSubiti;
    private JButton buttonRicerca;
    private JLabel labelSquadra;
    private JLabel labelNazionalita;
    private JLabel labelPiede;
    private JLabel labelEta;
    private JLabel labelRuolo;
    private JLabel labelGolFatti;
    private JLabel labelGolSubiti;
    private JComboBox comboBoxSquadra;
    private JComboBox comboBoxNazionalita;
    private JComboBox comboBoxPiede;
    private JComboBox comboBoxRuolo;
    private JTextField textEta;
    private JTextField textGolFatti;
    private JTextField textGolSubiti;
    private JButton buttonIndietro;
    private JPanel panelIndietro;
    private JLabel labelTesto;
    private JPanel panelTesto;
    private JCheckBox checkBoxSesso;
    private JRadioButton radioButtonMaschio;
    private JRadioButton radioButtonFemmina;
    private JPanel panelSesso;
    private JLabel labelSesso;
    private JCheckBox checkBoxDataRitiro;
    private JTextField textDataRitiro;
    private JRadioButton radioButtonRitirati;
    private JRadioButton radioButtonNonRitirati;
    private JRadioButton radioButtonDataRitiro;
    private ButtonGroup buttonGroupRitiro;
    private JPanel panelDataRitiro;
    private ButtonGroup buttonGroupSesso;
    private Controller controller;
    private Integer golFatti;
    private Integer golSubiti;
    private Integer eta;
    private LocalDate dataRitiro;
    private char sesso;

    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Ricerca utente.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public RicercaUtente(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Ricerca Utente");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(450, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        textNome.setEnabled(false);
        textCognome.setEnabled(false);
        comboBoxSquadra.setEnabled(false);
        comboBoxNazionalita.setEnabled(false);
        comboBoxPiede.setEnabled(false);
        textEta.setEnabled(false);
        comboBoxRuolo.setEnabled(false);
        textGolFatti.setEnabled(false);
        textGolSubiti.setEnabled(false);
        textDataRitiro.setEnabled(false);
        buttonGroupSesso = new ButtonGroup();
        buttonGroupSesso.add(radioButtonMaschio);
        buttonGroupSesso.add(radioButtonFemmina);
        radioButtonMaschio.setEnabled(false);
        radioButtonFemmina.setEnabled(false);
        buttonGroupRitiro = new ButtonGroup();
        buttonGroupRitiro.add(radioButtonRitirati);
        buttonGroupRitiro.add(radioButtonNonRitirati);
        buttonGroupRitiro.add(radioButtonDataRitiro);
        radioButtonRitirati.setEnabled(false);
        radioButtonNonRitirati.setEnabled(false);
        radioButtonDataRitiro.setEnabled(false);
        comboBoxRuolo.addItem("");
        comboBoxSquadra.addItem("");
        comboBoxNazionalita.addItem("");
        comboBoxPiede.addItem("");
        comboBoxPiede.addItem("Destro");
        comboBoxPiede.addItem("Sinistro");
        comboBoxPiede.addItem("Ambidestro");


        for (Ruolo ruolo : controller.getRuoli()) {
            comboBoxRuolo.addItem(ruolo.getPosizione());
        }
        for (Nazionalita nazionalita : controller.getNazionalita()) {
            comboBoxNazionalita.addItem(nazionalita.getNome());
        }
        for (Squadra squadra : controller.getSquadre()) {
            comboBoxSquadra.addItem(squadra.getNome());
        }

        checkBoxNome.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
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
        checkBoxCognome.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
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
        checkBoxSquadra.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxSquadra.isSelected()) {
                    comboBoxSquadra.setEnabled(true);
                } else {
                    comboBoxSquadra.setEnabled(false);
                    comboBoxSquadra.setSelectedIndex(0);
                }
            }
        });
        checkBoxNazionalita.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxNazionalita.isSelected()) {
                    comboBoxNazionalita.setEnabled(true);
                } else {
                    comboBoxNazionalita.setEnabled(false);
                    comboBoxNazionalita.setSelectedIndex(0);
                }
            }
        });
        checkBoxSesso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxSesso.isSelected()) {
                    radioButtonMaschio.setEnabled(true);
                    radioButtonFemmina.setEnabled(true);
                } else {
                    radioButtonMaschio.setEnabled(false);
                    radioButtonFemmina.setEnabled(false);
                    buttonGroupSesso.clearSelection();
                }
            }
        });
        checkBoxPiede.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxPiede.isSelected()) {
                    comboBoxPiede.setEnabled(true);
                } else {
                    comboBoxPiede.setEnabled(false);
                    comboBoxPiede.setSelectedIndex(0);
                }
            }
        });
        checkBoxEta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxEta.isSelected()) {
                    textEta.setEnabled(true);
                } else {
                    textEta.setEnabled(false);
                    textEta.setText("");
                }
            }
        });
        textEta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
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
        checkBoxGolFatti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxGolFatti.isSelected()) {
                    textGolFatti.setEnabled(true);
                } else {
                    textGolFatti.setEnabled(false);
                    textGolFatti.setText("");
                }
            }
        });
        textGolFatti.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        checkBoxGolSubiti.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBoxGolSubiti.isSelected()) {
                    textGolSubiti.setEnabled(true);
                } else {
                    textGolSubiti.setEnabled(false);
                    textGolSubiti.setText("");
                }
            }
        });
        textGolSubiti.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
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

        buttonRicerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textGolFatti.getText().isEmpty())
                    golFatti = null;
                else
                    golFatti = Integer.parseInt(textGolFatti.getText());
                if (textGolSubiti.getText().isEmpty())
                    golSubiti = null;
                else
                    golSubiti = Integer.parseInt(textGolSubiti.getText());
                if (textEta.getText().isEmpty())
                    eta = null;
                else
                    eta = Integer.parseInt(textEta.getText());
                if (textDataRitiro.getText().isEmpty() && radioButtonDataRitiro.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Inserire una data di ritiro valida",
                            "Errore", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(null, "Inserire una data di ritiro valida",
                                "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                if (radioButtonFemmina.isSelected())
                    sesso = 'F';
                else if (radioButtonMaschio.isSelected())
                    sesso = 'M';
                else
                    sesso = 'A';
                RisultatoUtente risultatoUtente = new RisultatoUtente(frame, controller, textNome.getText(),
                        textCognome.getText(), sesso, comboBoxSquadra.getSelectedItem().toString(),
                        comboBoxNazionalita.getSelectedItem().toString(), comboBoxPiede.getSelectedItem().toString(),
                        eta, comboBoxRuolo.getSelectedItem().toString(), golFatti, golSubiti, dataRitiro);
                risultatoUtente.frame.setVisible(true);
                frame.setVisible(false);
            }
        });

    }

}