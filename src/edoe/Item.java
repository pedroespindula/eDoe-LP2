package edoe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {

  private int id;
  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  public Item(int id, String descricao, int quantidade, String tags, Usuario usuario) {
    this.id = id;
    this.descricao = descricao.toLowerCase();
    this.quantidade = quantidade;
    setTags (tags);
    this.usuario = usuario;
  }
  public String getId() {
    return Integer.toString(this.id);
  }
  public String getDescricao() {
    return this.descricao;
  }
  public Usuario getUsuario() {
    return this.usuario;
  }
  public int getQuantidade (){
    return this.quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }
  public void setTags(String tags) {
    this.tags = Arrays.asList(tags.split(", "));
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
