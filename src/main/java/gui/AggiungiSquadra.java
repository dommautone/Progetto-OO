package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public static JFrame frame;
    private Controller controller;

    public AggiungiSquadra(JFrame frameChiamante, Controller controller, int idCalciatore) {
        frame = new JFrame("Aggiungi squadra");
        frame.setContentPane(this.comboBoxSquadra);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        buttonAggiungiSquadra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonTornaIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
            }
        });
    }
}
