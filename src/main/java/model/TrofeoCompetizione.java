package model;

public class TrofeoCompetizione {
    private String nome;
    private String descrizione;
    private char categoria;

    public TrofeoCompetizione(String nome, String descrizione, char categoria){
        this.nome=nome;
        this.descrizione=descrizione;
        setCategoria(categoria);
    }

    public String getNome(){return nome;}
    public String getDescrizione(){return descrizione;}
    public char getCategoria(){return categoria;}

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
}
