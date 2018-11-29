package edoe.testUtil;

import edoe.Usuario;

public class UsuarioTeste extends Usuario {
  public UsuarioTeste() {
    super("12345", "Teste", "teste@teste.com", "999", "PESSOA", false);
  }

  public UsuarioTeste(String id) {
    super(id, "Teste", "teste@teste.com", "999", "PESSOA", false);
  }
}
