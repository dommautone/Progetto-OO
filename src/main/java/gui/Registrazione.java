package gui;

import controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La gui Registrazione permette di registrarsi come amministratore.
 */
public class Registrazione {
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private JPanel panel;
    private JPanel panelRegistrazione;
    private JLabel labelRegistrazione;
    private JPasswordField passwordPassword;
    private JPasswordField passwordConfermaPassword;
    private JPanel panelLogo;
    private JTextField textUsername;
    private JPanel panelPassword;
    private JPanel panelConfermaPassword;
    private JPanel panelUsername;
    private JLabel labelUsername;
    private JLabel labelConfermaPassword;
    private JButton buttonIndietro;
    private JButton buttonRegistrati;
    private JPanel panelRegistrati;
    private JPanel panelIndietro;
    private JLabel labelPassword;
    private JLabel labelLogo;
    private Controller controller;

    /**
     * Instantiates a new Registrazione.
     *
     * @param frameChiamante the frame chiamante
     * @param controller     the controller
     */
    public Registrazione(JFrame frameChiamante, Controller controller) {
        frame = new JFrame("Registrazione");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(950, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        buttonRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textUsername.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserire username");
                    return;
                } else if (String.valueOf(passwordPassword.getPassword()).equals("") ||
                        String.valueOf(passwordConfermaPassword.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(null, "Inserire password");
                    return;
                } else if (!String.valueOf(passwordPassword.getPassword()).equals(String.valueOf(passwordConfermaPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Le password non coincidono");
                    return;
                }
                String username = textUsername.getText();
                String password = String.valueOf(passwordPassword.getPassword());
                try {
                    controller.registrazione(username, password);
                    JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo");
                } catch (AlreadyExistsExeption ex) {
                    JOptionPane.showMessageDialog(null, "Username già esistente");
                    return;
                } catch (PasswordCortaException ex) {
                    JOptionPane.showMessageDialog(null, "Password troppo corta");
                    return;
                } catch (UsernameCortoException ex) {
                    JOptionPane.showMessageDialog(null, "Username troppo corto");
                    return;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Errore");
                }
                frameChiamante.setVisible(true);
                frame.dispose();
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
