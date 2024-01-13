package model;

public class Competizione {
    private String nome;
    private String descrizione;
    private char categoria;
    private Nazionalità nazionalità;
    private TrofeoCompetizione trofeoCompetizione;

    public Competizione(String nome, String descrizione, char categoria, Nazionalità nazionalità, TrofeoCompetizione trofeoCompetizione){
        this.nome=nome;
        this.descrizione=descrizione;
        setCategoria(categoria);
        this.nazionalità=nazionalità;
        this.trofeoCompetizione=trofeoCompetizione;
    }

    public String getNome(){return nome;}
    public String getDescrizione(){return descrizione;}
    public char getCategoria(){return categoria;}
    public Nazionalità getNazionalità(){return nazionalità;}
    public TrofeoCompetizione getTrofeoCompetizione(){return trofeoCompetizione;}

    public void setNome(String nome){this.nome=nome;}
    public void setDescrizione(String descrizione){this.descrizione=descrizione;}
    public void setCategoria(char categoria){
        if(categoria=='M' || categoria=='F'){
            this.categoria=categoria;
        }
        else{
            System.out.println("Categoria non valida");
        }

    }
    public void setNazionalità(Nazionalità nazionalità){this.nazionalità=nazionalità;}
    public void setTrofeoCompetizione(TrofeoCompetizione trofeoCompetizione){this.trofeoCompetizione=trofeoCompetizione;}
}
