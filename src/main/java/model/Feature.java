package model;

public class Feature {
    private String tipoFeature;
    private String descrizione;

    public Feature(String tipoFeature, String descrizione){
        this.tipoFeature=tipoFeature;
        this.descrizione=descrizione;
    }

    public String getTipoFeature(){return tipoFeature;}
    public String getDescrizione(){return descrizione;}

    public void setTipoFeature(String tipoFeature){this.tipoFeature=tipoFeature;}
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
