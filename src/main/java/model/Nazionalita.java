package model;

/**
 * La classe Nazionalita descrive le informazioni delle nazionalità.
 */
public class Nazionalita {
    private String nome;
    private String continente;

    /**
     * Instantiates a new Nazionalita.
     *
     * @param nome       the nome
     * @param continente the continente
     */
    public Nazionalita(String nome, String continente){
        this.nome=nome;
        this.continente=continente;
    }

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){return nome;}

    /**
     * Get continente string.
     *
     * @return the string
     */
    public String getContinente(){return continente;}

    /**
     * Set nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome){this.nome=nome;}

    /**
     * Set continente.
     *
     * @param continente the continente
     */
    public void setContinente(String continente){this.continente=continente;}
}
