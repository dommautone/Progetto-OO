package model;

/**
 * The type Possiede.
 */
public class Possiede {
    /**
     * The Calciatore.
     */
    Calciatore calciatore;
    /**
     * The Feature.
     */
    Feature feature;

    /**
     * Instantiates a new Possiede.
     *
     * @param calciatore the calciatore
     * @param feature    the feature
     */
    public Possiede(Calciatore calciatore, Feature feature) {
        this.calciatore = calciatore;
        this.feature = feature;
    }

    /**
     * Gets calciatore.
     *
     * @return the calciatore
     */
    public Calciatore getCalciatore() {return calciatore;}

    /**
     * Gets feature.
     *
     * @return the feature
     */
    public Feature getFeature() {return feature;}

    /**
     * Sets calciatore.
     *
     * @param calciatore the calciatore
     */
    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}

    /**
     * Sets feature.
     *
     * @param feature the feature
     */
    public void setFeature(Feature feature) {this.feature = feature;}
}
