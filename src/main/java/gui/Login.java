package gui;

import controller.Controller;
import controller.DatiNonValidiExeption;
import controller.PasswordNonValidaExeption;
import controller.UtenteNonRegistratoExeption;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * La gui Login permette di effettuare il login come amministratore.
 */
public class Login {
    private JPanel panel;
    private JButton buttonDimenticata;
    private JButton buttonRegistrati;
    private JButton buttonAccedi;
    private JButton buttonIndietro;
    private JPanel panelIndietro;
    private JTextField textUsername;
    private JPasswordField passwordPassword;
    private JPanel panelDimenticata;
    private JPanel panelAccedi;
    private JPanel panelPassword;
    private JLabel labelPassword;
    private JPanel panelUsername;
    private JLabel labelUsername;
    private JPanel panelLogo;
    private JLabel labelLogo;
    private JPanel panelRegistrati;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    /**
     * The Accesso.
     */
    public Accesso accesso;
    private Controller controller;

    /**
     * Instantiates a new Login.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public Login(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setSize(732, 432);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        buttonRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrazione registrazione = new Registrazione(frame, controller);
                registrazione.frame.setVisible(true);
                frame.setVisible(false);
            }

        });
        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accesso.frame.setVisible(true);
                frame.dispose();
            }
        });
        buttonDimenticata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mi dispiace il servizio richiesto è" +
                        " temporaneamente non disponibile, la preghiamo di riprovare in seguito.");
            }
        });
        buttonAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUsername.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserisci l'username");
                    return;
                } else if (String.valueOf(passwordPassword.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserisci la password");
                    return;
                }
                String username = textUsername.getText();
                String password = String.valueOf(passwordPassword.getPassword());
                try {
                    controller.login(username, password);
                    JOptionPane.showMessageDialog(null, "Login effettuato con successo");
                } catch (DatiNonValidiExeption ex) {
                    JOptionPane.showMessageDialog(null, "Username o password errati");
                    return;
                } catch (UtenteNonRegistratoExeption ex) {
                    JOptionPane.showMessageDialog(null, "Utente non registrato");
                    return;
                } catch (PasswordNonValidaExeption ex) {
                    JOptionPane.showMessageDialog(null, "Password errata");
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore");
                    return;
                }

                PannelloAmministratore pannelloAmministratore = new PannelloAmministratore(frame, controller);
                pannelloAmministratore.frame.setVisible(true);
                frame.dispose();
            }
        });
    }

}