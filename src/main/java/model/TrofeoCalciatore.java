package model;

/**
 * La classe TrofeoCalciatore contiene le informazioni di un trofeo assegnato ad un calciatore
 */
public class TrofeoCalciatore {
    private String nome;
    private String descrizione;
    private char categoria;

    /**
     * Istanzia un nuovo trofeo calciatore.
     *
     * @param nome        il nome
     * @param descrizione la descrizione
     * @param categoria   la categoria
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
