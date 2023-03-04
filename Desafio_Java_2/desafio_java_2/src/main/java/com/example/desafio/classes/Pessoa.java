package main.java.com.example.desafio.classes;

public class Pessoa {
    //Atributos
    private String Nome;
    private int ID, Idade, PosFila;

    private static int Id = 0;
    private IncrementId(){
        Id++;
    }

    //Construtor padrao
    public Pessoa(){
        setID(Id);
        setNome("Valdemir");
        setIdade(19);
        setPosFila(1);
        IncrementID();
    }

    //Construtor com parametros
    public Pessoa(String nome, int idade, int posFila){
        setID(Id)
        setNome(nome);
        setIdade(idade);
        setPosFila(posFila);
    }


    //get e setters da classe

    public int getID(){
        return this.Id;
    }
    public void setID(int Id){
        this.Id = Id;
    }
    public String getNome(){
        return this.Nome;
    }
    public void setNome(String nome){
        this.Nome = nome;
    }
    public int getIdade(){
        return this.Idade;
    }
    public void setIdade(int idade){
        this.Idade = idade;
    }
    public int getPosFila(){
        return this.PosFila;
    }
    public void setPosFila(int PosFila){
        this.PosFila = PosFila;
    }
}
