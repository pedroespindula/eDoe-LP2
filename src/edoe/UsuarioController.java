package edoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UsuarioController {

  private Map<String, Usuario> usuarios;

  public UsuarioController() {
    this.usuarios = new HashMap<>();
  }

  public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
    Usuario usuario = new Usuario(id, nome, email, celular, classe, true);
    this.usuarios.put(id, usuario);
    return usuario.getId();
  }

  public String pesquisaPorId(String id) {
    return this.usuarios.get(id).toString();
  }

  public String pesquisaPorNome (String nome) {
    return this.usuarios.values()
            .stream()
            .filter(usuario -> usuario.getNome().equals(nome))
            .map(Usuario::toString)
            .collect(Collectors.joining(" | "));
  }

  public String atualizaUsuario (String id, String nome, String email, String celular) {
    Usuario usuario = this.usuarios.get(id);

    if (!nome.trim().isEmpty()) {
      usuario.setNome(nome);
    }
    if (!email.trim().isEmpty()) {
      usuario.setEmail(email);
    }
    if (!celular.trim().isEmpty()) {
      usuario.setTelefone(celular);
    }

    return usuario.toString();
  }

  public void removeUsuario(String id) {
    this.usuarios.remove(id);
  }

  public void lerReceptores(String caminho) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(caminho));
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
  }

  public Usuario getUsuario(String id) {
    return this.usuarios.get(id);
  }

}
