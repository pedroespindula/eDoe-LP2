package edoe;

import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    var esperado = id + " - cadeira de rodas, tags: [grande, adulta], quantidade: 10, Receptor: Teste/12345";

    assertEquals(esperado, this.controller.listaTodos());
  }

  @Test
  void listaTodosPorQuantidade() {
    var id3 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      11, "grande, adulta, primeira");
    var id1 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      9, "grande, adulta, ultima");
    var id2 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      10, "grande, adulta, segunda");

    var esperado = id3 + " - cadeira de rodas, tags: [grande, adulta, primeira], quantidade: 11, Receptor: Teste/12345 | " +
                id2 + " - cadeira de rodas, tags: [grande, adulta, segunda], quantidade: 10, Receptor: Teste/12345 | " +
                id1 + " - cadeira de rodas, tags: [grande, adulta, ultima], quantidade: 9, Receptor: Teste/12345";

    assertEquals(esperado, this.controller.listaTodos());
  }
}
