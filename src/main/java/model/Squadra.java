package model;

/**
 * La classe Squadra contiene le informazioni di una squadra
 */
public class Squadra {
    private int idSquadra;
    private String nome;
    private int annoFondazione;
    private char categoria;
    private Nazionalita nazionalita;

    /**
     * Istanzia una nuova squadra.
     *
     * @param idSquadra      id squadra
     * @param nome           il nome
     * @param categoria      la categoria
     * @param annoFondazione l'anno fondazione
     * @param nazionalita    la nazionalita
     */
    public Squadra(int idSquadra, String nome, char categoria, int annoFondazione, Nazionalita nazionalita){
        this.idSquadra=idSquadra;
        this.nome=nome;
        this.categoria=categoria;
        this.annoFondazione=annoFondazione;
        this.nazionalita = nazionalita;
    }

    /**
     * Get id squadra int.
     *
     * @return the int
     */
    public int getIdSquadra(){return idSquadra;}

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){return nome;}

    /**
     * Get anno fondazione int.
     *
     * @return the int
     */
    public int getAnnoFondazione(){return annoFondazione;}

    /**
     * Get categoria char.
     *
     * @return the char
     */
    public char getCategoria(){return categoria;}

    /**
     * Get nazionalita nazionalita.
     *
     * @return the nazionalita
     */
    public Nazionalita getNazionalita(){return nazionalita;}

    /**
     * Set id squadra.
     *
     * @param idSquadra the id squadra
     */
    public void setIdSquadra(int idSquadra){this.idSquadra=idSquadra;}

    /**
     * Set nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome){this.nome=nome;}

    /**
     * Set anno fondazione.
     *
     * @param annoFondazione the anno fondazione
     */
    public void setAnnoFondazione(int annoFondazione){this.annoFondazione=annoFondazione;}

    /**
     * Set categoria.
     *
     * @param categoria the categoria
     */
    public void setCategoria(char categoria){this.categoria=categoria;}

    /**
     * Set nazionalita.
     *
     * @param nazionalita the nazionalita
     */
    public void setNazionalita(Nazionalita nazionalita){this.nazionalita = nazionalita;}

}
