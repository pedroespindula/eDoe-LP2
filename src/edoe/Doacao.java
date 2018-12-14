package edoe;

public class Doacao {

  private String data;
  private Usuario doador;
  private String descricao;
  private int quantidade;
  private Usuario receptor;

  public Doacao(String data, Usuario doador, String descricao, int quantidade, Usuario receptor) {
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
