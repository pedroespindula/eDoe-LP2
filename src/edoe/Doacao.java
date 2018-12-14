package edoe;

import util.Validador;

public class Doacao {

  private String data;
  private Usuario doador;
  private String descricao;
  private int quantidade;
  private Usuario receptor;

  /**
   * Construtor que cria uma doacao a partir de uma data, um usuario doador, uma descricao
   * uma quantidade e um usuario receptor. Ele lancará erros se for criado com parametros nulos
   * ou Strings vazias. Além disso, ele verifica tambem se a quantidade é positiva.
   *
   * @param data       A data da doacao.
   * @param doador     O doador que doou o item.
   * @param descricao  A descricao do item doado.
   * @param quantidade A quantidade do item que foi doado.
   * @param receptor   O receptor que recebeu o item.
   */
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
