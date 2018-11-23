package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTeste extends Usuario {
  UsuarioTeste() {
    super("12345", "Teste", "teste@teste.com", "999", "PESSOA", false);
  }
}

class NecessitadoControllerTest {
  private NecessitadoController controller;
  @BeforeEach
  void criarController() {
    this.controller = new NecessitadoController();
  }

  @Test
  void criaItemNecessitado() {
    var id = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      10, "grande, adulta");
    var esperado = id + " - cadeira de rodas, tags: [grande, adulta], quantidade: 10";

    assertEquals(esperado, this.controller.listaTodos());
  }

  @Test
  void listaTodosPorQuantidade() {
    var id3 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      30, "grande, adulta, primeira");
    var id1 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      10, "grande, adulta, ultima");
    var id2 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      20, "grande, adulta, segunda");

    var esperado = id1 + " - cadeira de rodas, tags: [grande, adulta], quantidade: 10";

    assertEquals(esperado, this.controller.listaTodos());
  }
}
