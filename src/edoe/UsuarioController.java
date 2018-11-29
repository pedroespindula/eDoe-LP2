package edoe;

import util.Validador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioController {

  private Map<String, Usuario> usuarios;
  private Set<String> classes;

  public UsuarioController() {
    this.usuarios = new HashMap<>();
    this.classes = new HashSet<>();

    this.classes.add("PESSOA_FISICA");
    this.classes.add("IGREJA");
    this.classes.add("ORGAO_PUBLICO_MUNICIPAL");
    this.classes.add("ORGAO_PUBLICO_ESTADUAL");
    this.classes.add("ORGAO_PUBLICO_FEDERAL");
    this.classes.add("ONG");
    this.classes.add("ASSOCIACAO");
    this.classes.add("SOCIEDADE");

  }

  public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
    validador.verificaStringNulaOuVazia(classe, "Entrada invalida: classe nao pode ser vazia ou nula.");
    validador.verificaNaoContem(classe, this.classes, "Entrada invalida: opcao de classe invalida.");

    validador.verificaContem(id, this.usuarios, "Usuario ja existente: " + id + ".");

    Usuario usuario = new Usuario(id, nome, email, celular, classe, true);
    this.usuarios.putIfAbsent(id, usuario);
    return usuario.getId();
  }

  public String pesquisaPorId(String id) {
    return this.getUsuario(id).toString();
  }

  public String pesquisaPorNome(String nome) {
    Validador validador = new Validador();
    validador.verificaStringNulaOuVazia(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");

    String result = this.usuarios.values()
      .stream()
      .filter(usuario -> usuario.getNome().equals(nome))
      .map(Usuario::toString)
      .collect(Collectors.joining(" | "));

    validador.verificaStringVazia(result, "Usuario nao encontrado: " + nome + ".");
    return result;
  }

  public String atualizaUsuario(String id, String nome, String email, String celular) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

    validador.verificaNaoContem(id, this.usuarios, "Usuario nao encontrado: " + id + ".");

    Usuario usuario = this.usuarios.get(id);

    if (nome != null && !nome.trim().isEmpty()) {
      usuario.setNome(nome);
    }
    if (email != null && !email.trim().isEmpty()) {
      usuario.setEmail(email);
    }
    if (celular != null && !celular.trim().isEmpty()) {
      usuario.setTelefone(celular);
    }

    return usuario.toString();
  }

  public void removeUsuario(String id) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaNaoContem(id, this.usuarios, "Usuario nao encontrado: " + id + ".");

    this.usuarios.remove(id);
  }

  public void lerReceptores(String caminho) {
    try (Scanner scanner = new Scanner(new File(caminho))) {
      scanner.nextLine();

      while (scanner.hasNext()) {
        String[] dadosUsuario = scanner.nextLine().split(",");

        String id = dadosUsuario[0];
        String nome = dadosUsuario[1];
        String email = dadosUsuario[2];
        String celular = dadosUsuario[3];
        String classe = dadosUsuario[4];

        Usuario usuario = new Usuario(id, nome, email, celular, classe, false);
        this.usuarios.put(id, usuario);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Entrada invalida: arquivo inexistente.");
    }
  }

  public Usuario getUsuario(String id) {

    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaNaoContem(id, this.usuarios, "Usuario nao encontrado: " + id + ".");

    return this.usuarios.get(id);
  }

}
