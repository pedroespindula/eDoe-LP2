package edoe;

import static org.junit.jupiter.api.Assertions.*;

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
  public void adicionaDescritorTest() {
    this.doados.adicionaDescritor("bola de tenis");
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("sapato");

    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaDescritor("camisa"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaDescritor(""));
  }

  @Test
  public void adicionaItemParaDoacaoTest() {
    assertEquals("1", this.doados.cadastraItem(new UsuarioTeste(), "camisa", 10, "M, BL"));
    assertEquals("2", this.doados.cadastraItem(new UsuarioTeste(), "sapato", 50, "42"));
    assertEquals("3", this.doados.cadastraItem(new UsuarioTeste(), "bola de tenis", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.cadastraItem(new UsuarioTeste(), "", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.cadastraItem(new UsuarioTeste(), "camisa", -10, "M, N"));
  }

  @Test
  void exibeItemTest() {
    UsuarioTeste user = new UsuarioTeste();

    String id = this.doados.cadastraItem(user, "camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [M,  BL], quantidade: 50";
    assertEquals(esperado, this.doados.exibeItem(user, id));

    id = this.doados.cadastraItem(user, "sapato", 50, "42");
    esperado = id + " - sapato, tags: [42], quantidade: 50";
    assertEquals(esperado, this.doados.exibeItem(user, id));
  }

  @Test
  void atualizaItemParaDoacaoTest() {
    UsuarioTeste user = new UsuarioTeste();

    String id = this.doados.cadastraItem(user, "camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItem(user, id, 100, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.cadastraItem(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItem(user, id, -10, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.cadastraItem(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItem(user, id, -10, ""));

    user = new UsuarioTeste();
    id = this.doados.cadastraItem(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 110";
    assertEquals(esperado, this.doados.atualizaItem(user, id, 110, ""));

  }

  @Test
  void testaListaDescritoresVazio() {
    assertEquals(doados.listaDescritorDeItensParaDoacao(), "");
  }

  @Test
  void testaItensParaDoacaoVazio() {
    assertEquals(doados.listaItensParaDoacao(), "");
  }

  @Test
  void testaPesquisaItemParaDoacaoVazio() {
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("a"), "");
  }

  @Test
  void testaPesquisaItemParametroVazio() {
    try {
      doados.pesquisaItemParaDoacaoPorDescricao("       ");
      fail("DEVIA TER QUEBRADO");
    } catch (IllegalArgumentException o) {
      assert (true);
    }
  }

  @Test
  void testaPesquisaItemParametroNulo() {
    try {
      doados.pesquisaItemParaDoacaoPorDescricao(null);
      fail("DEVIA TER QUEBRADO");
    } catch (NullPointerException o) {
      assert (true);
    }
  }

  @Test
  void testaListaDescritoresUm() {
    this.doados.adicionaDescritor("bola de tenis");
    assertEquals(doados.listaDescritorDeItensParaDoacao(), "0 - bola de tenis");
  }

  @Test
  void testaListaDescritoresDois() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("bola de tenis");
    assertEquals(doados.listaDescritorDeItensParaDoacao(), "0 - bola de tenis | 0 - camisa");
  }

  @Test
  void testaListaDescritoresComItens() {

    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("bola de tenis");

    this.doados.cadastraItem(new UsuarioTeste(), "camisa", 10, "M,BL");
    this.doados.cadastraItem(new UsuarioTeste(), "bola de tenis", 5, "M,BL");

    assertEquals(doados.listaDescritorDeItensParaDoacao(), "5 - bola de tenis | 10 - camisa");
  }

  @Test
  void testaItensParaDoacaoAlguns() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("bola de tenis");

    this.doados.cadastraItem(new UsuarioTeste(true), "camisa", 3, "M,BL");
    this.doados.cadastraItem(new UsuarioTeste(true), "bola de tenis", 5, "M,BL");

    assertEquals(doados.listaItensParaDoacao(), "2 - bola de tenis, tags: [M, BL], quantidade: 5, doador: Teste/12345 | 1 - camisa, tags: [M, BL], quantidade: 3, doador: Teste/12345");
  }

  @Test
  void testaPesquisaItemParaDoacaoUnico() {
    this.doados.adicionaDescritor("camisa");
    this.doados.cadastraItem(new UsuarioTeste(), "camisa", 10, "M,BL");
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("   CAM        "), "1 - camisa, tags: [M, BL], quantidade: 10");
  }

  @Test
  void testaPesquisaItemParaDoacaoDois() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("camisola");
    this.doados.cadastraItem(new UsuarioTeste(), "camisola", 1, "M,BL");
    this.doados.cadastraItem(new UsuarioTeste(), "camisa", 3, "M,BL");
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("      CaM      "), "2 - camisa, tags: [M, BL], quantidade: 3 | 1 - camisola, tags: [M, BL], quantidade: 1");
  }

}
