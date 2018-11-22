package edoe;

import java.util.List;
import java.util.Objects;

public class Item {

  private String descricao; //id
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;

  public Item(String descricao, int quantidade, List<String> tags, Usuario usuario) {
      this.descricao = descricao;
      this.quantidade = quantidade;
      this.tags = tags;
      this.usuario = usuario;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public Usuario getUsuario() {

      return this.usuario;
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
        return Objects.equals(descricao, item.descricao) &&
                Objects.equals(tags, item.tags) &&
                Objects.equals(usuario, item.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, tags, usuario);
    }



}
