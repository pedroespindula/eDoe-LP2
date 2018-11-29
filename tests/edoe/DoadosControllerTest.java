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
  }

  @Test
  public void adicionaItemParaDoacaoTest () {
    int id = this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "camisa", 10, "M, BL");
    assertEquals(1, id);
    id = this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "sapato", 50, "42");
    assertEquals(2, id);
    id = this.doados.adicionaItemParaDoacao(new UsuarioTeste(), "bola de tenis", 500, "padrao");
    assertEquals(3, id);

  }

  @Test
  void testaItensParaDoacaoVazio() {
    assertEquals(doados.listaItensParaDoacao(), "");
  }

  @Test
  void testaPesquisaItemParaDoacaoVazio() {
    assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("a"), "");
  }


}
