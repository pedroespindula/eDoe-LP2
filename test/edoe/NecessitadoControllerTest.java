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
      10, "grande,adulta");
    var esperado = id + " - cadeira de rodas, tags: [grande, adulta], quantidade: 10, Receptor: Teste/12345";

    assertEquals(esperado, this.controller.listaTodos());
  }

  @Test
  void atualizaQuantidadeItemCriadoIgual() {
    var id = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      10, "grande,adulta");
    this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      5, "grande,adulta");
    var esperado = id + " - cadeira de rodas, tags: [grande, adulta], quantidade: 5, Receptor: Teste/12345";

    assertEquals(esperado, this.controller.listaTodos());
  }

  @Test
  void falhaCriarComDescInvalida() {
    assertThrows(NullPointerException.class,
      () -> this.controller.cadastraItemPedido(new UsuarioTeste(), null,
        10, "grande,adulta"));
    assertThrows(IllegalArgumentException.class,
      () -> this.controller.cadastraItemPedido(new UsuarioTeste(), "",
        10, "grande,adulta"));
  }

  @Test
  void falhaCriarQtdMenorZero() {
    this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      1, "grande,adulta");

    assertThrows(IllegalArgumentException.class,
      () -> this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
        0, "grande,adulta"));
    assertThrows(IllegalArgumentException.class,
      () -> this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
        -1, "grande,adulta"));
  }

  @Test
  void atualizaItem() {
    var usuarioTeste = new UsuarioTeste();
    var id = this.controller.cadastraItemPedido(usuarioTeste, "cadeira de rodas",
      1, "grande,adulta");

    var qtdAlterada = this.controller.atualizaItem(usuarioTeste, id, 2, "");
    var tagsAlteradas = this.controller.atualizaItem(usuarioTeste, id, 0, "gigante,infantil");

    var esperadaQtd = "0 - cadeira de rodas, tags: [grande, adulta], quantidade: 2";
    var esperadaTags = "0 - cadeira de rodas, tags: [gigante, infantil], quantidade: 2";

    assertEquals(esperadaQtd, qtdAlterada);
    assertEquals(esperadaTags, tagsAlteradas);
  }

  @Test
  void falhaAtualizacaoInvalida() {
    var usuarioTeste = new UsuarioTeste();
    var id = this.controller.cadastraItemPedido(usuarioTeste, "cadeira de rodas",
      1, "grande,adulta");

    assertThrows(IllegalArgumentException.class,
      () -> this.controller.atualizaItem(usuarioTeste, "-1", 5, "nova,tag"));
    assertThrows(IllegalArgumentException.class,
      () -> this.controller.atualizaItem(usuarioTeste, "150", 5, "nova,tag"));
  }

  @Test
  void listaTodosPorOrdemId() {
    var id1 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      11, "grande,adulta,primeira");
    var id2 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      9, "grande,adulta,segunda");
    var id3 = this.controller.cadastraItemPedido(new UsuarioTeste(), "cadeira de rodas",
      10, "grande,adulta,ultima");

    var esperado = id1 + " - cadeira de rodas, tags: [grande, adulta, primeira], quantidade: 11, Receptor: Teste/12345 | " +
                id2 + " - cadeira de rodas, tags: [grande, adulta, segunda], quantidade: 9, Receptor: Teste/12345 | " +
                id3 + " - cadeira de rodas, tags: [grande, adulta, ultima], quantidade: 10, Receptor: Teste/12345";

    assertEquals(esperado, this.controller.listaTodos());
  }

  @Test
  void removeItem() {
    var usuarioTeste = new UsuarioTeste();
    var id = this.controller.cadastraItemPedido(usuarioTeste, "cadeira de rodas",
      1, "grande,adulta");

    this.controller.removeItem(usuarioTeste, id);

    assertThrows(IllegalArgumentException.class,
      () -> this.controller.atualizaItem(usuarioTeste, id, 2, "nova,tag"));
  }

  @Test
  void falhaRemoverItem() {
    var usuarioTeste = new UsuarioTeste();
    var id = this.controller.cadastraItemPedido(usuarioTeste, "cadeira de rodas",
      1, "grande,adulta");

    // Item nao encontrado
    assertThrows(IllegalArgumentException.class,
      () -> this.controller.atualizaItem(usuarioTeste, "150", 2, "nova,tag"));

    // Usuario sem items
    assertThrows(IllegalArgumentException.class,
      () -> this.controller.atualizaItem(new UsuarioTeste("54321"), "150", 2, "nova,tag"));
  }
}
