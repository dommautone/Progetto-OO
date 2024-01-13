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

    public void setPosizione(String posizione){
        if(posizione.equals("Portiere") || posizione.equals("Difensore") || posizione.equals("Centrocampista") || posizione.equals("Attaccante")){
            this.posizione=posizione;
        }
        else{
            System.out.println("Posizione non valida");
        }

    }
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
}
