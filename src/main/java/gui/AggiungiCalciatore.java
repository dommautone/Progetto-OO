package gui;

import controller.CategoriaNonCorrispondeException;
import controller.Controller;
import controller.DataNonCoerenteException;
import model.Nazionalita;
import model.Ruolo;
import model.Squadra;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La gui AggiungiCalciatore permette di aggiungere un calciatore se si ha eseguita l'accesso come amministratore.
 */
public class AggiungiCalciatore {
    private JPanel panel;
    private JTextField textNome;
    private JLabel labelAggiungi;
    private JButton buttonInivio;
    private JTextField textCognome;
    private JComboBox comboBoxPiede;
    private JRadioButton radioButtonMaschio;
    private JRadioButton radioButtonFemmina;
    private ButtonGroup buttonGroupSesso;
    private JTextField textDataNascita;
    private JTextField textDataRitiro;
    private JList listRuolo;
    private JList listNazionalita;
    private JComboBox comboBoxSquadra;
    private JPanel panelAggiungi;
    private JPanel panelNome;
    private JLabel labelNome;
    private JPanel panelCognome;
    private JLabel labelCognome;
    private JPanel panelPiede;
    private JLabel labelPiede;
    private JPanel panelSesso;
    private JLabel labelSesso;
    private JPanel panelDataNascita;
    private JLabel labelDataNascita;
    private JRadioButton radioButtonSi;
    private JRadioButton radioButtonNo;
    private ButtonGroup buttonGroupRitiro;
    private JPanel panelDataRitiro;
    private JLabel labelDataRitiro;
    private JPanel panelRuolo;
    private JLabel labelRuolo;
    private JPanel panelNazionalita;
    private JLabel labelNazionalita;
    private JPanel panelInivio;
    private JLabel labelSquadra;
    private JPanel panelSquadra;
    private JLabel labelRitiroSiNo;
    private JScrollPane scrollPaneRuolo;
    private JScrollPane scrollPaneNazionalita;
    private JPanel panelDataFine;
    private JLabel labelSquadraAttuale;
    private JPanel panelDataInizio;
    private JLabel labelDataInizio;
    private JTextField textDataInizio;
    private JRadioButton radioButtonSi2;
    private JRadioButton radioButtonNo2;
    private ButtonGroup buttonGroupCarriera;
    private JTextField textDataFine;
    private JLabel labelDataFine;
    private JButton buttonIndietro;
    private JPanel panelIndietro;
    private Controller controller;
    /**
     * The Data ritiro.
     */
    LocalDate dataRitiro = null;
    /**
     * The Data fine.
     */
    LocalDate dataFine = null;
    /**
     * The Data inizio.
     */
    LocalDate dataInizio = null;
    /**
     * The Data nascita.
     */
    LocalDate dataNascita = null;
    /**
     * The Sesso.
     */
    char sesso;


    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Aggiungi calciatore.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public AggiungiCalciatore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Aggiungi calciatore");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(475, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        labelDataRitiro.setVisible(false);
        textDataRitiro.setVisible(false);
        labelDataFine.setVisible(false);
        textDataFine.setVisible(false);
        buttonGroupRitiro = new ButtonGroup();
        buttonGroupRitiro.add(radioButtonSi);
        buttonGroupRitiro.add(radioButtonNo);
        buttonGroupSesso = new ButtonGroup();
        buttonGroupSesso.add(radioButtonMaschio);
        buttonGroupSesso.add(radioButtonFemmina);
        buttonGroupCarriera = new ButtonGroup();
        buttonGroupCarriera.add(radioButtonSi2);
        buttonGroupCarriera.add(radioButtonNo2);
        comboBoxSquadra.addItem("");
        comboBoxPiede.addItem("");
        comboBoxPiede.addItem("Destro");
        comboBoxPiede.addItem("Sinistro");
        comboBoxPiede.addItem("Ambidestro");

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
        textCognome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        radioButtonMaschio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonMaschio.isSelected()) {
                    comboBoxSquadra.removeAllItems();
                    comboBoxSquadra.addItem("");
                    for (Squadra squadra : controller.getSquadreCategoria('M'))
                        comboBoxSquadra.addItem(squadra.getNome());
                }
            }
        });
        radioButtonFemmina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonFemmina.isSelected()) {
                    comboBoxSquadra.removeAllItems();
                    comboBoxSquadra.addItem("");
                    for (Squadra squadra : controller.getSquadreCategoria('F'))
                        comboBoxSquadra.addItem(squadra.getNome());
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

        textDataInizio.addKeyListener(new KeyAdapter() {
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

        textDataFine.addKeyListener(new KeyAdapter() {
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


        radioButtonSi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelDataRitiro.setVisible(true);
                textDataRitiro.setVisible(true);
                labelSquadraAttuale.setVisible(false);
                radioButtonNo2.setVisible(false);
                radioButtonSi2.setVisible(false);
                labelDataFine.setVisible(true);
                textDataFine.setVisible(true);
            }
        });
        radioButtonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelDataRitiro.setVisible(false);
                textDataRitiro.setVisible(false);
                textDataRitiro.setText("");
                labelSquadraAttuale.setVisible(true);
                radioButtonNo2.setVisible(true);
                radioButtonSi2.setVisible(true);
                labelDataFine.setVisible(false);
                textDataFine.setVisible(false);
                textDataFine.setText("");
            }
        });
        DefaultListModel modelRuolo = new DefaultListModel();
        DefaultListModel modelNazionalita = new DefaultListModel();
        listRuolo.setModel(modelRuolo);
        listNazionalita.setModel(modelNazionalita);
        listRuolo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listNazionalita.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (Ruolo ruolo : controller.getRuoli()) {
            modelRuolo.addElement(ruolo.getPosizione());
        }
        for (Nazionalita nazionalita : controller.getNazionalita()) {
            modelNazionalita.addElement(nazionalita.getNome());
        }
        for (Squadra squadra : controller.getSquadre()) {
            comboBoxSquadra.addItem(squadra.getNome());
        }

        radioButtonSi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelDataFine.setVisible(false);
                textDataFine.setVisible(false);
                textDataFine.setText("");
            }
        });
        radioButtonNo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelDataFine.setVisible(true);
                textDataFine.setVisible(true);
            }
        });
        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        buttonInivio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textNome.getText().equals("") || textCognome.getText().equals("") ||
                        comboBoxPiede.getSelectedItem().equals("") || textDataNascita.getText().equals("") ||
                        listRuolo.getSelectedValuesList().isEmpty() || listNazionalita.getSelectedValuesList().isEmpty() ||
                        comboBoxSquadra.getSelectedItem().equals("") ||
                        (radioButtonSi.isSelected() && textDataRitiro.getText().equals("")) ||
                        (radioButtonNo2.isSelected() && textDataFine.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Inserire tutti i campi");
                    return;
                }
                if (radioButtonSi.isSelected())
                    try {
                        dataRitiro = LocalDate.parse(textDataRitiro.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Inserire una data di ritiro valida");
                        return;
                    }
                else
                    dataRitiro = null;
                if (radioButtonNo2.isSelected() || radioButtonSi.isSelected())
                    try {
                        dataFine = LocalDate.parse(textDataFine.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Inserire una data di fine contratto" +
                                " valida");
                        return;
                    }
                else
                    dataFine = null;
                if (radioButtonFemmina.isSelected())
                    sesso = 'F';
                else
                    sesso = 'M';
                try {
                    dataNascita = LocalDate.parse(textDataNascita.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Inserire una data di nascita valida");
                    return;
                }
                try {
                    dataInizio = LocalDate.parse(textDataInizio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Inserire una data di inizio contratto" +
                            " valida");
                    return;
                }
                try {
                    controller.aggiungiCalciatore(textNome.getText(), textCognome.getText(), sesso,
                            comboBoxSquadra.getSelectedItem().toString(),
                            (ArrayList<String>) listNazionalita.getSelectedValuesList(),
                            comboBoxPiede.getSelectedItem().toString(),
                            dataNascita, (ArrayList<String>) listRuolo.getSelectedValuesList(),
                            dataRitiro, dataInizio, dataFine);
                    JOptionPane.showMessageDialog(null, "Calciatore aggiunto con successo");
                } catch (CategoriaNonCorrispondeException ex) {
                    JOptionPane.showMessageDialog(null, "Il sesso del calciatore non corrisponde " +
                            "alla categoria della squadra");
                } catch (DataNonCoerenteException ex) {
                    JOptionPane.showMessageDialog(null, "Qualche data non è coerente");

                }
            }
        });
    }

}
