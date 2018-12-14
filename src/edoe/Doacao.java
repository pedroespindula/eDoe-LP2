package edoe;

import util.Validador;

public class Doacao {

  private String data;
  private Usuario doador;
  private String descricao;
  private int quantidade;
  private Usuario receptor;

  public Doacao(String data, Usuario doador, String descricao, int quantidade, Usuario receptor) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(data, "Entrada invaida: data nao pode ser vazia ou nula.");
    validador.verificaStringNulaOuVazia(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");

    validador.verificaNulo(doador, "Doador nao pode ser nulo.");
    validador.verificaNulo(receptor, "Receptor nao pode ser nulo.");
    validador.verificaInteiroNegativo(quantidade, "Entrada invalida: quantidade nao pode ser negativa.");

    this.data = data;
    this.doador = doador;
    this.descricao = descricao;
    this.quantidade = quantidade;
    this.receptor = receptor;
  }

  @Override
  public String toString() {
    return this.data + " - doador: " + this.doador.getIdentificacao() + ", item: " + this.descricao + ", quantidade: " + this.quantidade + ", receptor: " + this.receptor.getIdentificacao();
  }

  public String getData() {
    return data;
  }

  public String getDescricao() {
    return descricao;
  }
}
