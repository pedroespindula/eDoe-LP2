package edoe;

import static org.junit.jupiter.api.Assertions.*;

import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edoe.Item;
import edoe.Usuario;
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
    assertEquals(1, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 10, "M, BL"));
    assertEquals(2, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "sapato", 50, "42"));
    assertEquals(3, this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "bola de tenis", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "", 500, "padrao"));
    assertThrows(IllegalArgumentException.class, () -> this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", -10, "M, N"));
  }

  @Test
  void exibeItemTest() {
    UsuarioTeste user = new UsuarioTeste();

    int id = this.doados.adicionaItemParaDoacao(user, "camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [M,  BL], quantidade: 50";
    assertEquals(esperado, this.doados.exibeItem(user, id));

    id = this.doados.adicionaItemParaDoacao(user, "sapato", 50, "42");
    esperado = id + " - sapato, tags: [42], quantidade: 50";
    assertEquals(esperado, this.doados.exibeItem(user, id));
  }

  @Test
  void atualizaItemParaDoacaoTest() {
    UsuarioTeste user = new UsuarioTeste();

    int id = this.doados.adicionaItemParaDoacao(user, "camisa", 50, "M, BL");
    String esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, 100, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [P,  N], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, -10, "P, N"));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 100";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, -10, ""));

    user = new UsuarioTeste();
    id = this.doados.adicionaItemParaDoacao(user, "camisa", 100, "GG, BL");
    esperado = id + " - camisa, tags: [GG,  BL], quantidade: 110";
    assertEquals(esperado, this.doados.atualizaItemParaDoacao(user, id, 110, ""));

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

    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 10, "M,BL");
    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "bola de tenis", 5, "M,BL");

    assertEquals(doados.listaDescritorDeItensParaDoacao(), "5 - bola de tenis | 10 - camisa"); }

  @Test
  void testaItensParaDoacaoAlguns() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("bola de tenis");

    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 3, "M,BL");
    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "bola de tenis", 5, "M,BL");

    assertEquals(doados.listaItensParaDoacao(), "2 - bola de tenis, tags: [M, BL], quantidade: 5, doador: Teste/12345 | 1 - camisa, tags: [M, BL], quantidade: 3, doador: Teste/12345");
  }

  @Test
  void testaPesquisaItemParaDoacaoUnico() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 10, "M,BL");
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("   CAM        "), "1 - camisa, tags: [M, BL], quantidade: 10");
  }

  @Test
  void testaPesquisaItemParaDoacaoDois() {
    this.doados.adicionaDescritor("camisa");
    this.doados.adicionaDescritor("camisola");
    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisola", 1, "M,BL");
    this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 3, "M,BL");
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("      CaM      "), "2 - camisa, tags: [M, BL], quantidade: 3 | 1 - camisola, tags: [M, BL], quantidade: 1");
  }


  @Test
  void realizaDoacaoTest() {
    Usuario user1 = new Usuario(2000, "zefa", "zefa@email.com", "9899", "c", false);
    Item necessitado = new Item(1, "botas", 6, "limpas, bonitas", user1);
    Usuario user2 = new Usuario(1000, "ze", "ze@email.com", "9898", "c", false);
    this.doados.adicionaDescritor("botas");
    this.doados.adicionaItemParaDoacao(user2, "botas", 70, "feias, sujas");

    assertEquals(this.doados.realizaDoacao(1, necessitado, "05/12/2018"), "05/12/2018 - doador: ze/1000, item: botas, quantidade: 70, receptor: zefa/2000");
    assertThrows(IllegalArgumentException.class, () -> this.doados.realizaDoacao(6, necessitado, "05/12/2018");
    assertThrows(IllegalArgumentException.class, () -> this.doados.realizaDoacao(1, necessitado, "");
  }

}
