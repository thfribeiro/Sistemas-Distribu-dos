package tcpcliente;

import java.io.Serializable;

 
//A classe deve implementar Serializable
public class Pessoa implements Serializable
 
{
  private String nome;
  private int idade;
  public Pessoa(String nome, int idade)
  {
    super();
    this.nome = nome;
    this.idade = idade;
  }

  public Pessoa(){
    super();
    this.nome = "";
    this.idade = 0;
  }

  public String getNome()
  {
    return nome;
  }
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  public String toString()
  {
    return this.nome  +"\n" + "Idade: " + this.idade;
  }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}