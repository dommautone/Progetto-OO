package model;

/**
 * La classe Amministratore contiene le informazioni di un amministratore
 */
public class Amministratore {
    private String username;
    private String password;

    /**
     * Istanzia un nuovo amministratore
     *
     * @param username l'username
     * @param password la password
     */
    public Amministratore(String username, String password){
        this.username=username;
        this.password=password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {return username;}

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {return password;}

    /**
     * Set username.
     *
     * @param username the username
     */
    public void setUsername(String username){this.username=username;}

    /**
     * Set password.
     *
     * @param password the password
     */
    public void setPassword(String password){this.password=password;}

}
