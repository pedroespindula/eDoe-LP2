package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoacaoTest {

  private Usuario doador;
  private Usuario receptor;
  private Doacao doacao;

  @BeforeEach
  void init() {
    this.doador = new Usuario("1", "Pedro", "pedro@espindula.me", "(83) 99999-8888", "PESSOA_FISICA", true);
    this.receptor = new Usuario("1", "Pedro", "pedro@espindula.me", "(83) 99999-8888", "PESSOA_FISICA", false);
    this.doacao = new Doacao("19/08/2000", doador, "ITEM", 10, receptor);
  }

  @Test
  void testConstructor() {
    init();
  }

  @Test
  void testConstructorDataVazia() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Doacao("", doador, "ITEM", 10, receptor);
    });
  }

  @Test
  void testConstructorDescricaoVazia() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Doacao("19/08/2000", doador, "", 10, receptor);
    });
  }

  @Test
  void testConstructorDataNula() {
    assertThrows(NullPointerException.class, () -> {
      new Doacao(null, doador, "ITEM", 10, receptor);
    });
  }

  @Test
  void testConstructorDoadorNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Doacao("19/08/2000/", null, "ITEM", 10, receptor);
    });
  }

  @Test
  void testConstructorDescricaoNula() {
    assertThrows(NullPointerException.class, () -> {
      new Doacao("19/08/2000/", doador, null, 10, receptor);
    });
  }

  @Test
  void testConstructorReceptorNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Doacao("19/08/2000/", doador, "ITEM", 10, null);
    });
  }

  @Test
  void testConstructorQuantidadeNegativa() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Doacao("19/08/2000/", doador, "ITEM", -1, receptor);
    });
  }


  @Test
  void testToString() {
    assertEquals("19/08/2000 - doador: Pedro/1, item: ITEM, quantidade: 10, receptor: Pedro/1", doacao.toString());
  }
}
