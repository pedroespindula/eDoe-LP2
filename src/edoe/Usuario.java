package edoe;

import java.util.Objects;

public class Usuario {

  private String id;
  private String nome;
  private String email;
  private String telefone;
  private String classe;
  private boolean ehDoador;

  public Usuario(String id, String nome, String email, String telefone, String classe, boolean ehDoador) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.classe = classe;
    this.ehDoador = ehDoador;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    String status = this.getStringStatus();
    return this.nome + "/" + this.id + ", " + this.email + ", " + this.telefone + ", status: " + status;
  }

  private String getStringStatus() {
    return ehDoador ? "doador" : "receptor";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario usuario = (Usuario) o;
    return Objects.equals(id, usuario.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
