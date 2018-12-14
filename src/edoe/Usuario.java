package edoe;

import util.Validador;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que representa um usuario que utiliza o eDoe.
 *
 * @author Pedro Espindula - 118110035
 */
public class Usuario implements Serializable {


	private static final long serialVersionUID = 1L;
	private String id;
  private String nome;
  private String email;
  private String telefone;
  private String classe;
  private boolean ehDoador;

  /**
   * Construtor que cria um usuario a partir do id, do nome, do email, do telefone
   * e de uma classe. Nenhum desses atributos pode ser nulo ou vazio se nao um erro
   * eh lancado.
   *
   * @param id       O id do usuario.
   * @param nome     O nome do usuario.
   * @param email    O email do usuario.
   * @param telefone O telefone do usuario.
   * @param classe   A classe do usuario.
   * @param ehDoador Se o usuario eh doador ou nao.
   */
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

  /**
   * Altera o nome do usuario de acordo com um parametro.
   * Esse parametro nao pode ser vazio nem nulo se nao um
   * erro eh lancado.
   *
   * @param nome O novo nome.
   */
  public void setNome(String nome) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");

    this.nome = nome;
  }

  /**
   * Altera o email do usuario de acordo com um parametro.
   * Esse parametro nao pode ser vazio nem nulo se nao um
   * erro eh lancado.
   *
   * @param email O novo email.
   */
  public void setEmail(String email) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(email, "Entrada invalida: email nao pode ser vazio ou nulo.");

    this.email = email;
  }

  /**
   * Altera o telefone do usuario de acordo com um parametro.
   * Esse parametro nao pode ser vazio nem nulo se nao um
   * erro eh lancado.
   *
   * @param telefone O novo telefone.
   */
  public void setTelefone(String telefone) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(telefone, "Entrada invalida: telefone nao pode ser vazio ou nulo.");

    this.telefone = telefone;
  }

  /**
   * Retorna o id do usuario.
   *
   * @return O id do usuario.
   */
  public String getId() {
    return id;
  }

  /**
   * Retorna o nome do usuario.
   *
   * @return O nome do usuario.
   */
  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    String status = this.getStringStatus();
    return this.getIdentificacao() + ", " + this.email + ", " + this.telefone + ", status: " + status;
  }

  /**
   * A partir do atributo ehDoador, retorna a string correspondente.
   *
   * @return se ehDoador for true: "doador";
   * se ehDoador for false: "receptor";
   */
  private String getStringStatus() {
    return ehDoador ? "doador" : "receptor";
  }

  public boolean getEhDoador() {
    return ehDoador;
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

  /**
   * Retorna a identificacao completa do usuario,
   * Ou seja, seu nome e o id.
   *
   * @return A identificacao do usuario.
   */
  public String getIdentificacao() {
    return this.nome + "/" + this.id;
  }

	public String getTelefone() {
		return this.telefone;
	}

	public String getEmail() {
		return this.email;
	}
	
	public String getClasse() {
		return this.classe;
	}
}
