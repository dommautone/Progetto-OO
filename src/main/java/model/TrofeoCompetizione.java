package model;

/**
 * La classe Trofeo competizione descrive un trofeo di una competizione.
 */
public class TrofeoCompetizione {
    private String nome;
    private String descrizione;
    private char categoria;

    /**
     * Instantiates a new Trofeo competizione.
     *
     * @param nome        the nome
     * @param descrizione the descrizione
     * @param categoria   the categoria
     */
    public TrofeoCompetizione(String nome, String descrizione, char categoria){
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
