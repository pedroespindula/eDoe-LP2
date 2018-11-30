package edoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.DoadosController;

class DoadosControllerTest {

  private DoadosController doados;

  @BeforeEach
  public void criaController() {
    doados = new DoadosController();
  }

  @Test
  public void adicionaDescritorTest(){
    this.doados.adicionaDescritor("bola de tenis");
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("sapato");

    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaDescritor("camisa"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaDescritor(""));
  }

  @Test
  public void adicionaItemParaDoacaoTest () {
    assertEquals(1, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 10, "M, BL"));
    assertEquals(2, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "sapato", 50, "42"));
    assertEquals(3, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "bola de tenis", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", -10, "M, N"));
  }

  @Test
  void exibeItemTest() {
    UsuarioTeste user = new UsuarioTeste();

    int id = this.doados.adicionaItemParaDoacao(user,"camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [M,  BL], quantidade: 50";
    assertEquals( esperado, this.doados.exibeItem(user, id));

    id = this.doados.adicionaItemParaDoacao(user,"sapato", 50, "42");
    esperado = id + " - sapato, tags: [42], quantidade: 50";
    assertEquals( esperado, this.doados.exibeItem(user, id));
  }

  @Test
  void atualizaItemParaDoacaoTest() {
    UsuarioTeste user = new UsuarioTeste();

    int id = this.doados.adicionaItemParaDoacao(user,"camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, 100, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user,"camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, -10, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user,"camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, -10, ""));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user,"camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 110";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, 110, ""));

  }


}
