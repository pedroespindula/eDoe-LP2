package edoe;

public class Mediator {

  private UsuarioController usuarioController;
  private DoadosController doadosController;
  private NecessitadoController necessitadoController;

  public Mediator() {

  }

  public String adicionaDoador (String id, String nome, String email, String celular, String classe) {
    return "";
  }

  public String pesquisaUsuarioPorId (String id) {
    return "";
  }

  public String pesquisaUsuarioPorNome (String nome) {
    return "";
  }

  public String atualizaUsuario (String id, String nome, String email, String celular) {
    return "";
  }

  public void removeUsuario (String id) {

  }

  public void lerReceptores(String caminho) {

  }

  public void adicionaDescritor (String descricao) {

  }

  public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags) {
    return "";
  }

  public String exibeItem (String id, String idDoador) {
    return "";
  }

  public String atualizaItemParaDoacao (String id, String idDoador, int quantidade, String tags) {
    return "";
  }

  public void removeItemParaDoacao (String id, String idDoador) {

  }

  public String listaDescritorDeItensParaDoacao () {
    return "";
  }

  public  String listaItensParaDoacao () {
    return "";
  }

  public String pesquisaItemParaDoacaoPorDescricao (String desc) {
    return "";
  }

}
