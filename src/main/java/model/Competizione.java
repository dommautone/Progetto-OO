package model;

/**
 * The type Competizione.
 */
public class Competizione {
    private String nome;
    private String descrizione;
    private char categoria;
    private Nazionalita nazionalita;
    private TrofeoCompetizione trofeoCompetizione;

    /**
     * Instantiates a new Competizione.
     *
     * @param nome               the nome
     * @param descrizione        the descrizione
     * @param categoria          the categoria
     * @param nazionalita        the nazionalità
     * @param trofeoCompetizione the trofeo competizione
     */
    public Competizione(String nome, String descrizione, char categoria, Nazionalita nazionalita, TrofeoCompetizione trofeoCompetizione){
        this.nome=nome;
        this.descrizione=descrizione;
        this.categoria=categoria;
        this.nazionalita = nazionalita;
        this.trofeoCompetizione=trofeoCompetizione;
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
     * Get nazionalità nazionalità.
     *
     * @return the nazionalità
     */
    public Nazionalita getNazionalita(){return nazionalita;}

    /**
     * Get trofeo competizione trofeo competizione.
     *
     * @return the trofeo competizione
     */
    public TrofeoCompetizione getTrofeoCompetizione(){return trofeoCompetizione;}

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

    /**
     * Set nazionalità.
     *
     * @param nazionalita the nazionalità
     */
    public void setNazionalita(Nazionalita nazionalita){this.nazionalita = nazionalita;}

    /**
     * Set trofeo competizione.
     *
     * @param trofeoCompetizione the trofeo competizione
     */
    public void setTrofeoCompetizione(TrofeoCompetizione trofeoCompetizione){this.trofeoCompetizione=trofeoCompetizione;}
}
