package gui;

import controller.Controller;
import model.Nazionalita;
import model.Ruolo;
import model.Squadra;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static gui.PannelloAmministratore.flag;

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
    private JPanel panelNazionalita;
    private JCheckBox checkBoxNazionalita;
    private JLabel labelNazionalita;
    private JComboBox comboBoxNazionalita;
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
    private JPanel panelEta;
    private JCheckBox checkboxEta;
    private JLabel labelEta;
    private JTextField textEta;
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
    private Integer eta;
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
        comboBoxNazionalita.setEnabled(false);
        comboBoxPiede.setEnabled(false);
        comboBoxRuolo.setEnabled(false);
        textFieldGolFatti.setEnabled(false);
        textGolSubiti.setEnabled(false);
        textEta.setEnabled(false);
        radioButtonMaschio.setEnabled(false);
        radioButtonFemmina.setEnabled(false);
        comboBoxPiede.addItem("");
        comboBoxPiede.addItem("Destro");
        comboBoxPiede.addItem("Sinistro");
        comboBoxPiede.addItem("Ambidestro");
        comboBoxRuolo.addItem("");
        comboBoxNazionalita.addItem("");
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
        for (Nazionalita nazionalita : controller.getNazionalita()) {
            comboBoxNazionalita.addItem(nazionalita.getNome());
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
        checkBoxNazionalita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxNazionalita.isSelected()) {
                    comboBoxNazionalita.setEnabled(true);
                } else {
                    comboBoxNazionalita.setEnabled(false);
                    comboBoxNazionalita.setSelectedIndex(0);
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
        checkboxEta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkboxEta.isSelected()) {
                    textEta.setEnabled(true);
                } else {
                    textEta.setEnabled(false);
                    textEta.setText("");
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
                if (textEta.getText().isEmpty())
                    eta = null;
                else
                    eta = Integer.parseInt(textEta.getText());
                if (textDataRitiro.getText().isEmpty() && !checkBoxDataRitiro.isSelected())
                    dataRitiro = LocalDate.of(1111, 1, 1);
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
                if (flag == 1) {
                    RicercaModifica ricercaModifica = new RicercaModifica(frame, controller, textNome.getText(),
                            textCognome.getText(), sesso, comboBoxSquadra.getSelectedItem().toString(),
                            comboBoxNazionalita.getSelectedItem().toString(), comboBoxPiede.getSelectedItem().toString(),
                            eta, comboBoxRuolo.getSelectedItem().toString(), golFatti, golSubiti, dataRitiro);
                    ricercaModifica.frame.setVisible(true);
                    frame.dispose();
                } else {
                    EliminaCalciatore eliminaCalciatore = new EliminaCalciatore(frame, controller, textNome.getText(),
                            textCognome.getText(), sesso, comboBoxSquadra.getSelectedItem().toString(),
                            comboBoxNazionalita.getSelectedItem().toString(), comboBoxPiede.getSelectedItem().toString(),
                            eta, comboBoxRuolo.getSelectedItem().toString(), golFatti, golSubiti, dataRitiro);
                    eliminaCalciatore.frame.setVisible(true);
                    frame.dispose();
                }
            }
        });
    }


}




