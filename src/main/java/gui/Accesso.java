package gui;

import postgresDAO.ImplementazionePostgresDAO;
import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La gui Accesso permette di scegliere se accedere come amministratore o come utente.
 */
public class Accesso {
    private JPanel panel;
    private JPanel panelLogo;
    private JLabel labelLogo;
    private JPanel panelAccedi;
    private JPanel panelUtente;
    private JButton buttonAccedi;
    private JButton buttonUtente;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private Controller controller = new Controller(new ImplementazionePostgresDAO());

    /**
     * Instantiates a new Accesso.
     */
    public Accesso() {
        controller.setSchema();
        buttonAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(frame, controller);
                login.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicercaUtente ricercaUtente = new RicercaUtente(frame, controller);
                ricercaUtente.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        frame = new JFrame("Accesso");
        frame.setContentPane(new Accesso().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}