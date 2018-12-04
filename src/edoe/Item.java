package edoe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Abstraçao de um item no sistema.
 *
 * @author Vitor Diniz - 118110145
 */
public class Item {
  private int id;
  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  /**
   * cria um item com seus devidos atributos
   *
   * @param id
   * @param descricao
   * @param quantidade
   * @param tags
   * @param usuario
   */
  public Item(int id, String descricao, int quantidade, String tags, Usuario usuario) {
    this.id = id;
    this.descricao = descricao.toLowerCase();
    this.quantidade = quantidade;
    setTags(tags);
    this.usuario = usuario;
  }

  /**
   * informa o numero identificador (id) do item
   *
   * @return id
   */
  public int getId() {
    return this.id;
  }

  /**
   * informa a descrição do item
   *
   * @return descrição
   */
  public String getDescricao() {
    return this.descricao;
  }

  /**
   * informa qual usuário está relacionado ao item
   *
   * @return usuario
   */
  public Usuario getUsuario() {
    return this.usuario;
  }

  /**
   * informa a quantidade de itens iguais a este existem
   *
   * @return quantidade
   */
  public int getQuantidade() {
    return this.quantidade;
  }

  /**
   * altera a quantidade de itens iguais a este existem
   *
   * @param quantidade
   */
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  /**
   * altera as tags do item
   *
   * @param tags
   */
  public void setTags(String tags) {
    this.tags = Arrays.asList(tags.split(","));
  }

  /**
   * Calcula a pontuacao de match entre este item e outro.
   * A pontuacao e calculada da seguinte maneira:
   *  Descricao igual: + 20 pontos
   *    Para cada tag:
   *      Tag igual na mesma posicao: + 10 pontos
   *      Tag igual em posicao diferente: 5 pontos
   *  Descricao diferente: 0 pontos (sem calculo quanto as tags).
   * @param outro o item a ser comparado para match
   * @return a pontuacao de match entre os dois items
   */
  public int match(Item outro) {
    if (!outro.getDescricao().equals(this.descricao)) {
      return 0;
    }
    return 20 + this.matchTags(outro);
  }

  private int matchTags(Item outro) {
    return IntStream.range(0, outro.tags.size())
      .filter(i -> this.tags.contains(outro.tags.get(i))) // Para cada tag, filtra apenas as existentes no this.tags
      .map(i -> {
        // Se a index da tag for maior que o tamanho da this.tags, entao impossivel estar na mesma posicao: 5 pontos
        if (i >= this.tags.size()) return 5;
        // Compara se a tag esta na mesma posicao, se sim: 10 pontos, se nao: 5 pontos;
        return this.tags.get(i).equals(outro.tags.get(i)) ? 10 : 5;
      })
      .reduce(0, Integer::sum);
  }

  @Override
  public String toString() {
    return this.getId() + " - " + this.descricao + ", tags: " + this.tagsEmString() +
      ", quantidade: " + this.quantidade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return Objects.equals(descricao, item.descricao) &&
      Objects.equals(tags, item.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(descricao, tags);
  }

  private String tagsEmString() {
    return "[" + String.join(", ", this.tags) + "]";
  }

  /**
   * informa a identificação do usuario ligado ao item
   *
   * @return identificação do usuario
   */
  public String getUsuarioIdentificacao() {
    return this.usuario.getIdentificacao();
  }
}
