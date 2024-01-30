package model;

/**
 * La classe Feature contiene le informazioni di una feature
 */
public class Feature {
    private String tipoFeature;
    private String descrizione;

    /**
     * Istanzia una nuova feature.
     *
     * @param tipoFeature il tipo feature
     * @param descrizione la descrizione
     */
    public Feature(String tipoFeature, String descrizione){
        this.tipoFeature=tipoFeature;
        this.descrizione=descrizione;
    }

    /**
     * Get tipo feature string.
     *
     * @return the string
     */
    public String getTipoFeature(){return tipoFeature;}

    /**
     * Get descrizione string.
     *
     * @return the string
     */
    public String getDescrizione(){return descrizione;}

    /**
     * Set tipo feature.
     *
     * @param tipoFeature the tipo feature
     */
    public void setTipoFeature(String tipoFeature){this.tipoFeature=tipoFeature;}

    /**
     * Set descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
