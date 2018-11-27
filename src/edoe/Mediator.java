package edoe;

import java.io.FileNotFoundException;

public class Mediator {

  private UsuarioController usuarioController;
  private DoadosController doadosController;
  private NecessitadoController necessitadoController;

  public Mediator() {
    this.usuarioController = new UsuarioController();
    this.doadosController = new DoadosController();
    this.necessitadoController = new NecessitadoController();
  }
  
  public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
    return this.usuarioController.adicionaDoador(id, nome, email, celular, classe);
  }

  public String pesquisaUsuarioPorId(String id) {
    return this.usuarioController.pesquisaPorId(id);
  }

  public String pesquisaUsuarioPorNome(String nome) {
    return this.usuarioController.pesquisaPorNome(nome);
  }

  public String atualizaUsuario(String id, String nome, String email, String celular) {
    return this.usuarioController.atualizaUsuario(id, nome, email, celular);
  }

  public void removeUsuario(String id) {
    this.usuarioController.removeUsuario(id);
  }

  public void lerReceptores(String caminho) throws FileNotFoundException {
    this.usuarioController.lerReceptores(caminho);
  }

  public void adicionaDescritor(String descricao) {

  }

  public String adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
    return "";
  }

  public String exibeItem(String id, String idDoador) {
    return "";
  }

  public String atualizaItemParaDoacao(String id, String idDoador, int quantidade, String tags) {
    return "";
  }

  public void removeItemParaDoacao(String id, String idDoador) {

  }

  public String listaDescritorDeItensParaDoacao() {
    return "";
  }

  public String listaItensParaDoacao() {
    return "";
  }

  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    return "";
  }

}
