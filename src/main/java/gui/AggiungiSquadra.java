package gui;

import controller.Controller;
import controller.DataNonCoerenteException;
import controller.SquadraGiàInseritaException;
import model.Squadra;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AggiungiSquadra {
    private JComboBox comboBoxSquadra;
    private JTextField textDataInizio;
    private JTextField textDataFine;
    private JTextField textGolSegnati;
    private JTextField textGolSubiti;
    private JTextField textPartiteGiocate;
    private JButton buttonAggiungiSquadra;
    private JButton buttonTornaIndietro;
    private JPanel panelTornaIndietro;
    private JPanel panelAggiungiSquadra;
    private JLabel labelPartiteGiocate;
    private JPanel panelPartiteGiocate;
    private JLabel labelGolSubiti;
    private JPanel panelGolSubiti;
    private JLabel labelGolSegnati;
    private JPanel panelGolSegnati;
    private JLabel labelDataFine;
    private JPanel panelDataFine;
    private JLabel labelDataInizio;
    private JPanel panelDataInizio;
    private JLabel labelSquadra;
    private JPanel panelSquadra;
    private JLabel labelTitolo;
    private JPanel panelTitolo;
    private JPanel panel;
    private JCheckBox checkBoxDataFine;
    private JLabel labelGolSubitiNo;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public static JFrame frame;
    private Controller controller;

    public AggiungiSquadra(JFrame frameChiamante, Controller controller, int idCalciatore, char sesso) {
        frame = new JFrame("Aggiungi squadra");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        textDataFine.setEnabled(false);
        labelGolSubitiNo.setVisible(false);

        if(controller.controlloRuoloPortiere(idCalciatore) == false) {
            labelGolSubitiNo.setVisible(true);
            textGolSubiti.setVisible(false);
            labelGolSubiti.setVisible(false);
        }

        comboBoxSquadra.addItem("");
        for (Squadra squadra : controller.getSquadreCategoria(sesso)) {
            comboBoxSquadra.addItem(squadra.getNome());
        }

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
        textGolSegnati.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c1 = e.getKeyChar();
                if (!(Character.isDigit(c1) || c1 == KeyEvent.VK_BACK_SPACE || c1 == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        textGolSubiti.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c1 = e.getKeyChar();
                if (!(Character.isDigit(c1) || c1 == KeyEvent.VK_BACK_SPACE || c1 == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        textPartiteGiocate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c1 = e.getKeyChar();
                if (!(Character.isDigit(c1) || c1 == KeyEvent.VK_BACK_SPACE || c1 == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        checkBoxDataFine.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(checkBoxDataFine.isSelected()) {
                    textDataFine.setEnabled(true);
                } else {
                    textDataFine.setEnabled(false);
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

        buttonAggiungiSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textDataInizio.getText().equals("") || textGolSegnati.getText().equals("") ||
                        textGolSubiti.getText().equals("") || textPartiteGiocate.getText().equals("") ||
                        comboBoxSquadra.getSelectedItem().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserire tutti i dati", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        dataInizio = LocalDate.parse(textDataInizio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Inserire una data di inizio contratto" +
                                " valida");
                        return;
                    }
                    try {
                        if(checkBoxDataFine.isSelected())
                            dataFine = LocalDate.parse(textDataFine.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Inserire una data di fine contratto" +
                                " valida");
                        return;
                    }

                    try {
                        controller.inserisciSquadra(idCalciatore, sesso, (String) comboBoxSquadra.getSelectedItem(),
                                dataInizio, dataFine, Integer.parseInt(textPartiteGiocate.getText()),
                                Integer.parseInt(textGolSegnati.getText()), Integer.parseInt(textGolSubiti.getText()));
                        JOptionPane.showMessageDialog(null, "Squadra inserita con successo",
                                "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SquadraGiàInseritaException ex) {
                        JOptionPane.showMessageDialog(null, "Squadra già inserita", "Errore",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (DataNonCoerenteException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Data di fine contratto precedente" +
                                        " a quella di inizio",
                                "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonTornaIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificaSquadra modificaSquadra = new ModificaSquadra(frameChiamante, controller, idCalciatore, sesso);
                modificaSquadra.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
