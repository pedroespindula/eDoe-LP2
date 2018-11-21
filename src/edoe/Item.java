package edoe;

import java.util.List;

public class Item {

  private String descricao;
  private int quantidade;
  private List<String> tags;
  private Usuario usuario;
  private String data;

  public String getDescricao() {
    return "";
  }

  public Usuario getUsuario() {
    return null;
  }

  public void setQuantidade(int quantidade) {

  }

  public void setTags(List<String> tags) {

  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
