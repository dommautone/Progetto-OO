package gui;

import controller.Controller;
import model.Squadra;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * La gui Modifica permette di modificare un calciatore se si è amministratori.
 */
public class Modifica {

    private ButtonGroup buttonGroupSesso;
    private JPanel panel;
    private JTextField textNome;
    private JButton buttonModificaNazionalita;
    private JButton buttonModificaRuolo;
    private JButton buttonInvio;
    private JButton buttonIndietro;
    private JTextField textCognome;
    private JTextField textDataNascita;
    private JTextField textDataRitiro;
    private JTextField textGolFatti;
    private JTextField textGolSubiti;
    private JCheckBox checkBoxNome;
    private JCheckBox checkBoxCognome;
    private JCheckBox checkBoxPiede;
    private JCheckBox checkBoxSesso;
    private JCheckBox checkDataNascita;
    private JCheckBox checkDataRitiro;
    private JCheckBox checkBoxSquadra;
    private JCheckBox checkBoxGolFatti;
    private JCheckBox checkBoxGolSubiti;
    private JPanel panelModificaCalciatore;
    private JLabel labelModificaCalciatore;
    private JPanel panelNome;
    private JLabel labelNome;
    private JPanel panelCognome;
    private JLabel labelCognome;
    private JPanel panelDataNascita;
    private JLabel labelDataNascita;
    private JPanel panelDataRitiro;
    private JLabel labelDataRitiro;
    private JPanel panelGolFatti;
    private JLabel labelGolFatti;
    private JPanel panelGolSubiti;
    private JLabel labelGolSubiti;
    private JPanel panelModificaRuolo;
    private JPanel panelModificaNazionalita;
    private JPanel panelInvio;
    private JPanel panelIndietro;
    private JPanel panelSesso;
    private JLabel labelSesso;
    private JRadioButton radioButtonMaschio;
    private JRadioButton radioButtonFemmina;
    private JPanel panelPiede;
    private JLabel labelPiede;
    private JComboBox comboBoxPiede;
    private JPanel panelSquadra;
    private JLabel labelSquadra;
    private JComboBox comboBoxSquadra;
    private Controller controller;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private LocalDate dataRitiro2;
    private LocalDate dataNascita2;
    private char sesso2;

    /**
     * Instantiates a new Modifica.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     * @param idCalciatore   the id calciatore
     * @param idSquadra      the id squadra
     * @param nome           the nome
     * @param cognome        the cognome
     * @param piede          the piede
     * @param sesso          the sesso
     * @param dataNascita    the data nascita
     * @param dataRitiro     the data ritiro
     * @param squadra        the squadra
     * @param golFatti       the gol fatti
     * @param golSubiti      the gol subiti
     * @param ruolo          the ruolo
     * @param nazionalita    the nazionalità
     */
    public Modifica(JFrame frameChiamante, Controller controller, int idCalciatore, int idSquadra, String nome,
                    String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro,
                    String squadra, Integer golFatti, Integer golSubiti, String ruolo, String nazionalita) {
        frame = new JFrame("Modifica");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(559, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textNome.setEnabled(false);
        textCognome.setEnabled(false);
        textDataNascita.setEnabled(false);
        textDataRitiro.setEnabled(false);
        textGolFatti.setEnabled(false);
        textGolSubiti.setEnabled(false);

        comboBoxPiede.addItem("Destro");
        comboBoxPiede.addItem("Sinistro");
        comboBoxPiede.addItem("Ambidestro");

        for (Squadra squadraD : controller.getSquadre()) {
            comboBoxSquadra.addItem(squadraD.getNome());
        }

        textNome.setText(nome);
        textCognome.setText(cognome);
        textDataNascita.setText(String.valueOf(dataNascita));
        textDataRitiro.setText(String.valueOf(dataRitiro));
        comboBoxSquadra.setSelectedItem(squadra);
        piede = piede.substring(0, 1).toUpperCase() + piede.substring(1).toLowerCase();
        comboBoxPiede.setSelectedItem(piede);
        textGolFatti.setText(String.valueOf(golFatti));
        textGolSubiti.setText(String.valueOf(golSubiti));
        buttonGroupSesso = new ButtonGroup();
        buttonGroupSesso.add(radioButtonFemmina);
        buttonGroupSesso.add(radioButtonMaschio);
        radioButtonFemmina.setEnabled(false);
        radioButtonMaschio.setEnabled(false);
        comboBoxPiede.setEnabled(false);
        comboBoxSquadra.setEnabled(false);

        if (sesso == 'M')
            radioButtonMaschio.setSelected(true);
        else
            radioButtonFemmina.setSelected(true);

        checkBoxNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxNome.isSelected()) {
                    textNome.setEnabled(true);
                } else {
                    textNome.setEnabled(false);
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
        checkBoxPiede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxPiede.isSelected()) {
                    comboBoxPiede.setEnabled(true);
                } else {
                    comboBoxPiede.setEnabled(false);
                }
            }
        });
        checkBoxSesso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxSesso.isSelected()) {
                    radioButtonMaschio.setEnabled(true);
                    radioButtonFemmina.setEnabled(true);
                } else {
                    radioButtonMaschio.setEnabled(false);
                    radioButtonFemmina.setEnabled(false);
                }
            }
        });
        checkDataNascita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDataNascita.isSelected()) {
                    textDataNascita.setEnabled(true);
                } else {
                    textDataNascita.setEnabled(false);
                }
            }
        });
        textDataNascita.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c1 = e.getKeyChar();
                if (!(Character.isDigit(c1) || c1 == KeyEvent.VK_BACK_SPACE || c1 == KeyEvent.VK_DELETE ||
                        c1 == KeyEvent.VK_SLASH)) {
                    e.consume();
                }
            }
        });
        checkDataRitiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDataRitiro.isSelected()) {
                    textDataRitiro.setEnabled(true);
                } else {
                    textDataRitiro.setEnabled(false);
                }
            }
        });
        textDataRitiro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c1 = e.getKeyChar();
                if (!(Character.isDigit(c1) || c1 == KeyEvent.VK_BACK_SPACE || c1 == KeyEvent.VK_DELETE ||
                        c1 == KeyEvent.VK_SLASH)) {
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
                }
            }
        });
        checkBoxGolFatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxGolFatti.isSelected()) {
                    textGolFatti.setEnabled(true);
                } else {
                    textGolFatti.setEnabled(false);
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
        checkBoxGolSubiti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxGolSubiti.isSelected()) {
                    if(controller.controlloRuoloPortiere(idCalciatore) == false) {
                        JOptionPane.showMessageDialog(null, "Il calciatore non è un portiere");
                        checkBoxGolSubiti.setSelected(false);
                        return;
                    }
                    textGolSubiti.setEnabled(true);
                } else {
                    textGolSubiti.setEnabled(false);
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
        buttonModificaRuolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificaRuolo modificaRuolo = new ModificaRuolo(frame, controller, idCalciatore, nome, cognome, ruolo);
                modificaRuolo.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonModificaNazionalita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificaNazionalita modificaNazionalita = new ModificaNazionalita(frame, controller, idCalciatore);
                modificaNazionalita.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonInvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textNome.getText().equals("") || textCognome.getText().equals("") ||
                        textDataNascita.getText().equals("") || textGolFatti.getText().equals("") ||
                        textGolSubiti.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserire tutti i campi");
                    return;
                }
                boolean isValidFormat = false;
                if (textDataRitiro.getText().equals(""))
                    dataRitiro2 = null;
                else {
                    try {
                        dataRitiro2 = LocalDate.parse(textDataRitiro.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        isValidFormat = true;
                    } catch (DateTimeParseException e1) {
                        try {
                            dataRitiro2 = LocalDate.parse(textDataRitiro.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            isValidFormat = true;
                        } catch (DateTimeParseException e2) {
                            isValidFormat = false;
                        }
                    }
                }
                if (!isValidFormat && dataRitiro2 != null) {
                    JOptionPane.showMessageDialog(null, "Formato data di ritiro non valido");
                    return;
                }
                try {
                    dataNascita2 = LocalDate.parse(textDataNascita.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    isValidFormat = true;
                } catch (DateTimeParseException e1) {
                    try {
                        dataNascita2 = LocalDate.parse(textDataNascita.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        isValidFormat = true;
                    } catch (DateTimeParseException e2) {
                        isValidFormat = false;
                    }
                }
                if (!isValidFormat) {
                    JOptionPane.showMessageDialog(null, "Formato data di nascita non valido");
                    return;
                }

                if (radioButtonMaschio.isSelected())
                    sesso2 = 'M';
                else
                    sesso2 = 'F';
                controller.modificaCalciatore(idCalciatore, idSquadra, textNome.getText(), textCognome.getText(),
                        (String) comboBoxPiede.getSelectedItem(), sesso2, LocalDate.parse(textDataNascita.getText()),
                        dataRitiro2, Integer.parseInt(textGolFatti.getText()), Integer.parseInt(textGolSubiti.getText()),
                        (String) comboBoxSquadra.getSelectedItem());
                JOptionPane.showMessageDialog(null, "Calciatore modificato con successo");
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
