package model;

public class Squadra {
    private int idSquadra;
    private String nome;
    private int annoFondazione;
    private char categoria;
    private Nazionalità nazionalità;

    public Squadra(int idSquadra, String nome, int annoFondazione, char categoria, Nazionalità nazionalità){
        this.idSquadra=idSquadra;
        this.nome=nome;
        setAnnoFondazione(annoFondazione);
        setCategoria(categoria);
        this.nazionalità=nazionalità;
    }

    public int getIdSquadra(){return idSquadra;}
    public String getNome(){return nome;}
    public int getAnnoFondazione(){return annoFondazione;}
    public char getCategoria(){return categoria;}
    public Nazionalità getNazionalità(){return nazionalità;}
    public void setIdSquadra(int idSquadra){this.idSquadra=idSquadra;}
    public void setNome(String nome){this.nome=nome;}
    public void setAnnoFondazione(int annoFondazione){
        if(annoFondazione>1856){
            this.annoFondazione=annoFondazione;
        }
        else{
            System.out.println("Anno di fondazione non valido");
        }
    }
    public void setCategoria(char categoria){
        if(categoria=='M' || categoria=='F'){
            this.categoria=categoria;
        }
        else{
            System.out.println("Categoria non valida");
        }
    }
    public void setNazionalità(Nazionalità nazionalità){this.nazionalità=nazionalità;}

}
