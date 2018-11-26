package edoe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {
  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  public Item(String descricao, int quantidade, String tags, Usuario usuario) {
    setDescricao(descricao);
    this.quantidade = quantidade;
    setTags(tags);
    this.usuario = usuario;
  }

  public String getId() {
    return String.valueOf(this.hashCode());
  }

  public String getDescricao() {
    return this.descricao;
  }

  public Usuario getUsuario() {
    return this.usuario;
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao.toLowerCase();
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public void setTags(String tags) {
    this.tags = Arrays.asList(tags.split(", "));
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
}
