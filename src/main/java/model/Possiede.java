package model;

public class Possiede {
    Calciatore calciatore;
    Feature feature;

    public Possiede(Calciatore calciatore, Feature feature) {
        this.calciatore = calciatore;
        this.feature = feature;
    }

    public Calciatore getCalciatore() {return calciatore;}
    public Feature getFeature() {return feature;}

    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}
    public void setFeature(Feature feature) {this.feature = feature;}
}
