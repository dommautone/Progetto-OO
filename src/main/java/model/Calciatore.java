package model;

import java.time.LocalDate;

/**
 * La classe Calciatore contiene le informazioni di un calciatore
 */
public class Calciatore {
    private int idCalciatore;
    private String nome;
    private String cognome;
    private String piede;
    private char sesso;
    private LocalDate dataNascita;
    private LocalDate dataRitiro;

    /**
     * Istanzia un nuovo calciatore.
     *
     * @param idCalciatore id calciatore
     * @param nome         il nome
     * @param cognome      il cognome
     * @param piede        il piede
     * @param sesso        il sesso
     * @param dataNascita  la data nascita
     * @param dataRitiro   la data ritiro
     */
    public Calciatore (int idCalciatore, String nome, String cognome, String piede, char sesso, LocalDate dataNascita, LocalDate dataRitiro){
        this.idCalciatore=idCalciatore;
        this.nome=nome;
        this.cognome=cognome;
        this.piede=piede;
        this.sesso=sesso;
        this.dataNascita=dataNascita;
        this.dataRitiro=dataRitiro;
    }

    /**
     * Get id calciatore int.
     *
     * @return the int
     */
    public int getIdCalciatore(){return idCalciatore;}

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){return nome;}

    /**
     * Get cognome string.
     *
     * @return the string
     */
    public String getCognome(){return cognome;}

    /**
     * Get piede string.
     *
     * @return the string
     */
    public String getPiede(){return piede;}

    /**
     * Get sesso char.
     *
     * @return the char
     */
    public char getSesso(){return sesso;}

    /**
     * Get data nascita local date.
     *
     * @return the local date
     */
    public LocalDate getDataNascita(){return dataNascita;}

    /**
     * Get data ritiro local date.
     *
     * @return the local date
     */
    public LocalDate getDataRitiro(){return dataRitiro;}

    /**
     * Set id calciatore.
     *
     * @param idCalciatore the id calciatore
     */
    public void setIdCalciatore(int idCalciatore){this.idCalciatore=idCalciatore;}

    /**
     * Set nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome){this.nome=nome;}

    /**
     * Set cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome){this.cognome=cognome;}

    /**
     * Set piede.
     *
     * @param piede the piede
     */
    public void setPiede(String piede){this.piede=piede;}

    /**
     * Set sesso.
     *
     * @param sesso the sesso
     */
    public void setSesso(char sesso){this.sesso=sesso;}

    /**
     * Set data nascita.
     *
     * @param dataNascita the data nascita
     */
    public void setDataNascita(LocalDate dataNascita){this.dataNascita=dataNascita;}

    /**
     * Set data ritiro.
     *
     * @param dataRitiro the data ritiro
     */
    public void setDataRitiro(LocalDate dataRitiro){this.dataRitiro=dataRitiro;}


}
