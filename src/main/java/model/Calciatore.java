package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calciatore {
    private int idCalciatore;
    private String nome;
    private String cognome;
    private String piede;
    private char sesso;
    private LocalDate dataNascita;
    private LocalDate dataRitiro;

    public Calciatore (String nome, String cognome, String piede, char sesso, String dataNascita, String dataRitiro){
        this.nome=nome;
        this.cognome=cognome;
        setPiede(piede);
        setSesso(sesso);
        setDataNascita(dataNascita);
        setDataRitiro(dataRitiro);
    }
    public int getIdCalciatore(){return idCalciatore;}
    public String getNome(){return nome;}
    public String getCognome(){return cognome;}
    public String getPiede(){return piede;}
    public char getSesso(){return sesso;}
    public LocalDate getDataNascita(){return dataNascita;}
    public LocalDate getDataRitiro(){return dataRitiro;}

    public void setIdCalciatore(int idCalciatore){this.idCalciatore=idCalciatore;}
    public void setNome(String nome){this.nome=nome;}
    public void setCognome(String cognome){this.cognome=cognome;}
    public void setPiede(String piede){
        if(piede.equals("Destro") || piede.equals("Sinistro") || piede.equals("Ambidestro")){
            this.piede=piede;
        }
        else{
            System.out.println("Piede non valido");
        }
    }
    public void setSesso(char sesso){
        if(sesso=='M' || sesso=='F'){
            this.sesso=sesso;
        }
        else{
            System.out.println("Sesso non valido");
        }
    }

    public LocalDate controlloData(String data){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(data, formatter);

            int giorno = parsedDate.getDayOfMonth();
            int mese = parsedDate.getMonthValue();

            if ((mese == 4 || mese == 6 || mese == 9 || mese == 11) && giorno > 30) {
                throw new IllegalArgumentException("Il giorno non può superare 30 per questo mese.");
            } else if (mese == 2 && giorno > 29) {
                throw new IllegalArgumentException("Il giorno non può superare 29 per Febbraio.");
            } else if (giorno > 31) {
                throw new IllegalArgumentException("Il giorno non può superare 31 per questo mese.");
            }

            return parsedDate;

        } catch (DateTimeException | IllegalArgumentException e) {
            System.out.println("Errore nella data di nascita: " + e.getMessage());
            return null;
        }
    }
    public void setDataNascita(String dataNascita){
        this.dataNascita=controlloData(dataNascita);
    }
    public void setDataRitiro(String dataRitiro){
        this.dataRitiro=controlloData(dataRitiro);
    }
}
