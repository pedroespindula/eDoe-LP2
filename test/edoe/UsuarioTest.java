package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

  private String idPadrao;
  private String nomePadrao;
  private String emailPadrao;
  private String telefonePadrao;
  private String classePadrao;
  private Usuario usuarioPadrao;

  @BeforeEach
  void setUp() {
    this.idPadrao = "11122233344";
    this.nomePadrao = "Pedro Espindula";
    this.emailPadrao = "pedro@espindula.me";
    this.telefonePadrao = "99999-8888";
    this.classePadrao = "PESSOA_FISICA";

    this.usuarioPadrao = new Usuario(idPadrao, nomePadrao, emailPadrao, telefonePadrao, classePadrao, true);
  }

  @Test
  void testConstutorNaoEhDoador() {
    new Usuario(idPadrao, nomePadrao, emailPadrao, telefonePadrao, classePadrao, false);
  }

  @Test
  void testConstrutorIdVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario("", nomePadrao, emailPadrao, telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorNomeVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario(idPadrao, "", emailPadrao, telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorEmailVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario(idPadrao, nomePadrao, "", telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorTelefoneVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario(idPadrao, nomePadrao, emailPadrao, "", classePadrao, true);
    });
  }

  @Test
  void testConstrutorClasseVazia() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario(idPadrao, nomePadrao, emailPadrao, telefonePadrao, "", true);
    });
  }

  @Test
  void testConstrutorIdNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Usuario(null, nomePadrao, emailPadrao, telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorNomeNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Usuario(idPadrao, null, emailPadrao, telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorEmailNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Usuario(idPadrao, nomePadrao, null, telefonePadrao, classePadrao, true);
    });
  }

  @Test
  void testConstrutorTelefoneNulo() {
    assertThrows(NullPointerException.class, () -> {
      new Usuario(idPadrao, nomePadrao, emailPadrao, null, classePadrao, true);
    });
  }

  @Test
  void testConstrutorClasseNula() {
    assertThrows(NullPointerException.class, () -> {
      new Usuario(idPadrao, nomePadrao, emailPadrao, telefonePadrao, null, true);
    });
  }

  @Test
  void testGetIdentificacao() {
    assertEquals(nomePadrao + "/" + idPadrao, usuarioPadrao.getIdentificacao());
  }

  @Test
  void testSetNomeVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.usuarioPadrao.setNome("");
    });
  }

  @Test
  void testSetEmailVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.usuarioPadrao.setEmail("");
    });
  }

  @Test
  void testSetTelefoneVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      this.usuarioPadrao.setTelefone("");
    });
  }

  @Test
  void testSetNomeNulo() {
    assertThrows(NullPointerException.class, () -> {
      this.usuarioPadrao.setNome(null);
    });
  }

  @Test
  void testSetEmailNulo() {
    assertThrows(NullPointerException.class, () -> {
      this.usuarioPadrao.setEmail(null);
    });
  }

  @Test
  void testSetTelefoneNulo() {
    assertThrows(NullPointerException.class, () -> {
      this.usuarioPadrao.setTelefone(null);
    });
  }

  @Test
  void testToStringDoador() {
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-8888, status: doador", this.usuarioPadrao.toString());
  }

  @Test
  void testToStringReceptor() {
    Usuario receptor = new Usuario(idPadrao, nomePadrao, emailPadrao, telefonePadrao, classePadrao, false);
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-8888, status: receptor", receptor.toString());
  }


}
