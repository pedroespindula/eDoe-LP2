package edoe;

import java.util.List;
import java.util.Objects;

public class Item {

  private int id;
  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  public Item(int id, String descricao, int quantidade, List<String> tags, Usuario usuario) {
    this.id = id;
    setDescricao(descricao);
    this.quantidade = quantidade;
    this.tags = tags;
    this.usuario = usuario;
  }
  public int getId() {
    return this.id;
  }
  public String getDescricao() {
    return this.descricao;
  }
  public Usuario getUsuario() {
    return this.usuario;
  }
  public void setDescricao(String descricao){
    this.descricao = descricao.toLowerCase();
  }
  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }
  public void setTags(List<String> tags) {
    for (String tag: tags)
      this.tags.add(tag);
  }

  @Override
  public String toString() {
    return this.descricao + ", tags: " + this.tags + ", " + this.quantidade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return id == item.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
