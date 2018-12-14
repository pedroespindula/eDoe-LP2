package edoe;

import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.NumberUp;

import static org.junit.jupiter.api.Assertions.*;

class DoacaoControllerTest {

  private Item item1;
  private Item item2;
  private Item item3;
  private Item item4;
  private DoacaoController doacaoController;

  @BeforeEach
  void init() {
    this.item1 = new Item(1, "camisa", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.item2 = new Item(2, "camisa", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.item3 = new Item(3, "camisa", 80, "mulambenta, feia, velha", new UsuarioTeste());
    this.item4 = new Item(4, "bermuda", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.doacaoController = new DoacaoController();
  }

  @Test
  void realizaDoacao() {
    this.doacaoController.realizaDoacao(item1, item2, "10/10/2010");
    assertEquals(0, item1.getQuantidade());
    assertEquals(0, item2.getQuantidade());
    assertEquals("10/10/2010 - doador: Teste/12345, item: camisa, quantidade: 100, receptor: Teste/12345", this.doacaoController.listaDoacoes());
  }

  @Test
  void realizaDoacaoQuantidadesDiferentesDoadorMaior() {
    this.doacaoController.realizaDoacao(item1, item3, "10/10/2010");
    assertEquals(20, item1.getQuantidade());
    assertEquals(0, item3.getQuantidade());
    assertEquals("10/10/2010 - doador: Teste/12345, item: camisa, quantidade: 80, receptor: Teste/12345", this.doacaoController.listaDoacoes());
  }

  @Test
  void realizaDoacaoQuantidadesDiferentesReceptorMaior() {
    this.doacaoController.realizaDoacao(item3, item1, "10/10/2010");
    assertEquals(0, item3.getQuantidade());
    assertEquals(20, item1.getQuantidade());
    assertEquals("10/10/2010 - doador: Teste/12345, item: camisa, quantidade: 80, receptor: Teste/12345", this.doacaoController.listaDoacoes());
  }

  @Test
  void realizaDoacaoDescricoesDiferentes() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.doacaoController.realizaDoacao(item1, item4, "10/10/2010");
    });
  }

  @Test
  void realizaDoacaoDataVazia() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.doacaoController.realizaDoacao(item1, item2, "");
    });
  }

  @Test
  void realizaDoacaoItemNecNulo() {
    assertThrows(NullPointerException.class, () -> {
      this.doacaoController.realizaDoacao(null, item2, "10/10/2010");
    });
  }

  @Test
  void realizaDoacaoItemDoadoNulo() {
    assertThrows(NullPointerException.class, () -> {
      this.doacaoController.realizaDoacao(item1, null, "10/10/2010");
    });
  }

  @Test
  void realizaDoacaoDataNula() {
    assertThrows(NullPointerException.class, () -> {
      this.doacaoController.realizaDoacao(item1, item2, null);
    });
  }

  @Test
  void listaDoacoes() {
    realizaDoacao();
  }
}
