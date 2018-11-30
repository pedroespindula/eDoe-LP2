package edoe;

import util.Validador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioController {

  private Map<String, Usuario> usuarios;
  private Set<String> classes;

  /**
   * Construtor que cria um UsuarioController com um LinkedHashMap (Visto que a ordem de insercao
   * importa) e um HashSet (Visto que so eh necessario saber se um elemento esta contido nele ou nao).
   * Nesse LinkedHashMap estao contidos os usuarios onde a chave eh o seu id e o valor eh o objeto
   * usuario. No HashSet os elementos sao as classes possiveis para um usuario.
   */
  public UsuarioController() {
    this.usuarios = new LinkedHashMap<>();
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

  /**
   * Adiciona um doador ao sistema, adicionando-o ao mapa de usuarios. Ele so eh
   * adicionado se todos os seus parametros nao forem nulos e nem vazios, se a
   * classe estiver dentro das classes possiveis e se nao existir um usuario com
   * o mesmo id.
   *
   * @param id      O id do usuario.
   * @param nome    O nome do usuario.
   * @param email   O email do usuario.
   * @param celular O celular do usuario.
   * @param classe  A classe do usuario.
   * @return O id do usuario.
   */
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
    this.usuarios.put(id, usuario);
    return usuario.getId();
  }

  /**
   * Chama o metodo getUsuario(id) e aplica o toString() ao retorno do metodo.
   *
   * @param id O id do usuario.
   * @return O toString() do usuario.
   */
  public String pesquisaPorId(String id) {
    return this.getUsuario(id).toString();
  }

  /**
   * Pesquisa todos os usuarios que tiverem o nome igual ao que foi passado como
   * parametro. Esse nome nao pode ser vazio e nem nulo se nao um erro eh lancado.
   * Alem disso, se nenhum usuario for encontrado um erro tambem eh lancado.
   *
   * @param nome O nome do usuario.
   * @return A representacao de todos os usuarios com aquele nome.
   */
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

  /**
   * Atualiza um Usuario a partir do seu Id e dos parametros a serem alterados.
   * Se algum dos parametros a ser atualizado for nulo ou vazio, esse parametro
   * nao eh atualizado.
   *
   * @param id      O id do usuario.
   * @param nome    O novo nome.
   * @param email   O novo email.
   * @param celular O novo celular.
   * @return A representacao do usuario atualizada.
   */
  public String atualizaUsuario(String id, String nome, String email, String celular) {

    Usuario usuario = this.getUsuario(id);

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

  /**
   * Remove um Usuario do sistema a partir do seu id.
   * Se o id for vazio ou nulo um erro eh lancado.
   * Se o usuario nao existir um erro tambem eh lancado.
   *
   * @param id O id do usuario.
   */
  public void removeUsuario(String id) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaNaoContem(id, this.usuarios, "Usuario nao encontrado: " + id + ".");

    this.usuarios.remove(id);
  }

  /**
   * Cadastra todos os receptores do sistema a partir do caminho de um arquivo.
   * Esse caminho ira direcionar o metodo ao arquivo e ele ira fazer a leitura
   * dos dados do arquivo, cadastrando os usuario receptores.
   *
   * @param caminho O caminho para o arquivo.
   */
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

  /**
   * Pesquisa um usuario pelo id. Esse id nao pode ser nulo e nem vazio se nao um erro
   * eh lancado. Alem disso, se o usuario nao for encontrado um erro tambem eh lancado.
   *
   * @param id O id do usuario.
   * @return O objeto Usuario.
   */
  public Usuario getUsuario(String id) {

    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaNaoContem(id, this.usuarios, "Usuario nao encontrado: " + id + ".");

    return this.usuarios.get(id);
  }

}
