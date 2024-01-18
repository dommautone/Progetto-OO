package model;

import java.util.ArrayList;

public class Ruolo {
    private String posizione;
    private String descrizione;

    public Ruolo(String posizione, String descrizione){
        setPosizione(posizione);
        this.descrizione=descrizione;
    }

    public String getPosizione(){return posizione;}
    public String getDescrizione(){return descrizione;}

    public void setPosizione(String posizione){this.posizione=posizione;}
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
