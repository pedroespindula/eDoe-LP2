package edoe;

import util.Validador;

import java.util.Objects;

public class Usuario {

  private String id;
  private String nome;
  private String email;
  private String telefone;
  private String classe;
  private boolean ehDoador;

  public Usuario(String id, String nome, String email, String telefone, String classe, boolean ehDoador) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(telefone, "Entrada invalida: telefone nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(classe, "Entrada invalida: classe nao pode ser vazio ou nulo.");

    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.classe = classe;
    this.ehDoador = ehDoador;
  }

  public void setNome(String nome) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");

    this.nome = nome;
  }

  public void setEmail(String email) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(email, "Entrada invalida: email nao pode ser vazio ou nulo.");

    this.email = email;
  }

  public void setTelefone(String telefone) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: telefone nao pode ser vazio ou nulo.");

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
    return this.getIdentificacao() + ", " + this.email + ", " + this.telefone + ", status: " + status;
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

  public String getIdentificacao() {
    return this.nome + "/" + this.id;
  }
}
