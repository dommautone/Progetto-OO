package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La gui Pannello Amministratore permette di scegliere se aggiungere, modificare o eliminare un calciatore.
 */
public class PannelloAmministratore {
    private JPanel panel;
    private JButton buttonAggiungi;
    private JButton buttonModificaCalciatore;
    private JButton buttonElimina;
    private JPanel panelAggiungi;
    private JPanel panelModifica;
    private JPanel panelElimina;
    private JPanel panelRicercaCalciatore;
    private JButton buttonRicercaCalciatore;
    private JPanel panelPannello;
    private JLabel labelPannello;
    private Controller controller;

    /**
     * The constant flag.
     */
    public static int flag;
    /**
     * The constant frame.
     */
    public static JFrame frame;


    /**
     * Instantiates a new Pannello amministratore.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public PannelloAmministratore(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Pannello di controllo");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(809, 378);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        buttonAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiCalciatore aggiungiCalciatore = new AggiungiCalciatore(frame, controller);
                aggiungiCalciatore.frame.setVisible(true);
                frame.dispose();
            }
        });
        buttonModificaCalciatore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 1;
                RicercaAmministratore ricercaAmministratore = new RicercaAmministratore(frame, controller);
                ricercaAmministratore.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonElimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = 0;
                RicercaAmministratore ricercaAmministratore = new RicercaAmministratore(frame, controller);
                ricercaAmministratore.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonRicercaCalciatore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicercaUtente ricercaUtente = new RicercaUtente(frame, controller);
                ricercaUtente.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }


}



