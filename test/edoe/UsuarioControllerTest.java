package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

  private String idPadrao;
  private String nomePadrao;
  private String emailPadrao;
  private String telefonePadrao;
  private String classePadrao;
  private String caminhoReceptores;
  private UsuarioController usuarioControllerPadrao;

  @BeforeEach
  void setUp() {
    this.idPadrao = "11122233344";
    this.nomePadrao = "Pedro Espindula";
    this.emailPadrao = "pedro@espindula.me";
    this.telefonePadrao = "99999-8888";
    this.classePadrao = "PESSOA_FISICA";
    this.caminhoReceptores = "arquivos_sistema/novosReceptores.csv";

    this.usuarioControllerPadrao = new UsuarioController();
  }

  @Test
  void testAdicionaDoador() {
    usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, telefonePadrao, classePadrao);
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-8888, status: doador", usuarioControllerPadrao.pesquisaPorId(idPadrao));
  }

  @Test
  void testAdicionaDoadorIdVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador("", nomePadrao, emailPadrao, telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorNomeVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, "", emailPadrao, telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorEmailVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, "", telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorTelefoneVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, "", classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorClasseVazia() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, telefonePadrao, "");
    });
  }

  @Test
  void testAdicionaDoadorIdNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(null, nomePadrao, emailPadrao, telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorNomeNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, null, emailPadrao, telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorEmailNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, null, telefonePadrao, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorTelefoneNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, null, classePadrao);
    });
  }

  @Test
  void testAdicionaDoadorClasseNula() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, telefonePadrao, null);
    });
  }

  @Test
  void testAdicionaDoadorSobrescrevendo() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.adicionaDoador(idPadrao, nomePadrao, emailPadrao, telefonePadrao, classePadrao);
      usuarioControllerPadrao.adicionaDoador(idPadrao, "Matheus Gaudencio", "matheus@gaudencio.me", "99999-7777", classePadrao);
    });
  }

  @Test
  void testPesquisaPorId() {
    testAdicionaDoador();
  }

  @Test
  void testPesquisaPorIdVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.pesquisaPorId("");
    });
  }

  @Test
  void testPesquisaPorIdNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.pesquisaPorId(null);
    });
  }

  @Test
  void testPesquisaPorIdInexistente() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.pesquisaPorId("12312312321");
    });
  }

  @Test
  void testPesquisaPorNome() {
    testAdicionaDoador();
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-8888, status: doador", usuarioControllerPadrao.pesquisaPorNome(nomePadrao));
  }

  @Test
  void testPesquisaPorNomeMultiplosNomes() {
    testAdicionaDoador();
    usuarioControllerPadrao.adicionaDoador("12312312321", nomePadrao, emailPadrao, telefonePadrao, classePadrao);
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-8888, status: doador" + " | " +
      "Pedro Espindula/12312312321, pedro@espindula.me, 99999-8888, status: doador", usuarioControllerPadrao.pesquisaPorNome(nomePadrao));
  }

  @Test
  void testPesquisaPorNomeVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.pesquisaPorNome("");
    });
  }

  @Test
  void testPesquisaPorNomeNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.pesquisaPorNome(null);
    });
  }

  @Test
  void testPesquisaPorNomeInexistente() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.pesquisaPorNome("Matheus");
    });
  }

  @Test
  void testAtualizaUsuarioNome() {
    testAdicionaDoador();
    String nomeNovo = "Matheus Gaudencio";
    assertNotEquals(nomePadrao, nomeNovo);
    usuarioControllerPadrao.atualizaUsuario(idPadrao, nomeNovo, null, null);
    assertEquals("Matheus Gaudencio/11122233344, pedro@espindula.me, 99999-8888, status: doador", usuarioControllerPadrao.pesquisaPorId(idPadrao));
  }

  @Test
  void testAtualizaUsuarioEmail() {
    testAdicionaDoador();
    String novoEmail = "matheus@gaundencio.me";
    assertNotEquals(emailPadrao, novoEmail);
    usuarioControllerPadrao.atualizaUsuario(idPadrao, null, novoEmail, null);
    assertEquals("Pedro Espindula/11122233344, matheus@gaundencio.me, 99999-8888, status: doador", usuarioControllerPadrao.pesquisaPorId(idPadrao));
  }

  @Test
  void testAtualizaUsuarioTelefone() {
    testAdicionaDoador();
    String novoTelefone = "99999-7777";
    assertNotEquals(telefonePadrao, novoTelefone);
    usuarioControllerPadrao.atualizaUsuario(idPadrao, null, null, novoTelefone);
    assertEquals("Pedro Espindula/11122233344, pedro@espindula.me, 99999-7777, status: doador", usuarioControllerPadrao.pesquisaPorId(idPadrao));
  }

  @Test
  void testAtualizaUsuarioInexistente() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.atualizaUsuario("99988877766", "Matheus", null, null);
    });
  }

  @Test
  void testAtualizaUsuarioIdVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.atualizaUsuario("", "Matheus", null, null);
    });
  }

  @Test
  void testAtualizaUsuarioIdNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.atualizaUsuario(null, "Matheus", null, null);
    });
  }

  @Test
  void testRemoveUsuario() {
    testAdicionaDoador();
    usuarioControllerPadrao.removeUsuario(idPadrao);
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.pesquisaPorId(idPadrao);
    });
  }

  @Test
  void testRemoveUsuarioVazio() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.removeUsuario("");
    });
  }

  @Test
  void testRemoveUsuarioNulo() {
    assertThrows(NullPointerException.class, () -> {
      usuarioControllerPadrao.removeUsuario(null);
    });
  }

  @Test
  void testRemoveUsuarioInexistente() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.removeUsuario(idPadrao);
    });
  }

  @Test
  void testLerReceptores() {
    usuarioControllerPadrao.lerReceptores(caminhoReceptores);
    assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", usuarioControllerPadrao.pesquisaPorId("84473712044"));
  }

  @Test
  void testLerReceptoresCaminhoErrado() {
    assertThrows(IllegalArgumentException.class, () -> {
      usuarioControllerPadrao.lerReceptores("caminho/que/nao/existe");
    });
  }
}
