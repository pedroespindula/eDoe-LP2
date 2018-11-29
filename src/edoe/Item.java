package edoe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstraçao de um item no sistema.
 *
 * @author Vitor Diniz - 118110145
 *
 */
public class Item {
  private int id;
  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  /**
   * cria um item com seus devidos atributos
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
   * @return id
   */
  public int getId() {
    return this.id;
  }

  /**
   * informa a descrição do item
   * @return descrição
   */
  public String getDescricao() {
    return this.descricao;
  }

  /**
   * informa qual usuário está relacionado ao item
   * @return usuario
   */
  public Usuario getUsuario() {
    return this.usuario;
  }

  /**
   * informa a quantidade de itens iguais a este existem
   * @return quantidade
   */
  public int getQuantidade() {
    return this.quantidade;
  }

  /**
   * altera a quantidade de itens iguais a este existem
   * @param quantidade
   */
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  /**
   * altera as tags do item
   * @param tags
   */
  public void setTags(String tags) {
    this.tags = Arrays.asList(tags.split(","));
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
   * @return identificação do usuario
   */
  public String getUsuarioIdentificacao() {
	return this.usuario.getIdentificacao();
  }
}
