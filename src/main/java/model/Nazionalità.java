package model;

public class Nazionalità {
    private String nome;
    private String continente;

    public Nazionalità(String nome, String continente){
        this.nome=nome;
        setContinente(continente);
    }

    public String getNome(){return nome;}
    public String getContinente(){return continente;}

    public void setNome(String nome){this.nome=nome;}
    public void setContinente(String continente){
        if(continente.equals("Europa") || continente.equals("Asia") || continente.equals("Africa") || continente.equals("Oceania") || continente.equals("NordAmerica") || continente.equals("SudAmerica")){
            this.continente=continente;
        }
        else{
            System.out.println("Continente non valido");
        }
    }
}
