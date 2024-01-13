package model;

public class Appartiene {
    Calciatore calciatore;
    Nazionalità nazionalità;

    public Appartiene(Calciatore calciatore, Nazionalità nazionalità) {
        this.calciatore = calciatore;
        this.nazionalità = nazionalità;
    }

    public Calciatore getCalciatore() {return calciatore;}
    public Nazionalità getNazionalità() {return nazionalità;}

    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}
    public void setNazionalità(Nazionalità nazionalità) {this.nazionalità = nazionalità;}
}
