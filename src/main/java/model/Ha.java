package model;

public class Ha {
    Calciatore calciatore;
    Ruolo ruolo;

    public Ha(Calciatore calciatore, Ruolo ruolo) {
        this.calciatore = calciatore;
        this.ruolo = ruolo;
    }

    public Calciatore getCalciatore() {return calciatore;}
    public Ruolo getRuolo() {return ruolo;}

    public void setCalciatore(Calciatore calciatore) {this.calciatore = calciatore;}
    public void setRuolo(Ruolo ruolo) {this.ruolo = ruolo;}
}
