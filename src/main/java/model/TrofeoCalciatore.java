package model;

/**
 * La classe Trofeo calciatore descrive un trofeo che può essere vinto da un calciatore.
 */
public class TrofeoCalciatore {
    private String nome;
    private String descrizione;
    private char categoria;

    /**
     * Instantiates a new Trofeo calciatore.
     *
     * @param nome        the nome
     * @param descrizione the descrizione
     * @param categoria   the categoria
     */
    public TrofeoCalciatore(String nome, String descrizione, char categoria){
        this.nome=nome;
        this.descrizione=descrizione;
        this.categoria=categoria;
    }

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){return nome;}

    /**
     * Get descrizione string.
     *
     * @return the string
     */
    public String getDescrizione(){return descrizione;}

    /**
     * Get categoria char.
     *
     * @return the char
     */
    public char getCategoria(){return categoria;}

    /**
     * Set nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome){this.nome=nome;}

    /**
     * Set descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}

    /**
     * Set categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(char categoria){this.categoria=categoria;}
}
