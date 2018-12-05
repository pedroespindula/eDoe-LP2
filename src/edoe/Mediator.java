package edoe;

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

  public void lerReceptores(String caminho) {
    this.usuarioController.lerReceptores(caminho);
  }

  public void adicionaDescritor(String descricao) {
    this.doadosController.adicionaDescritor(descricao);
  }

  public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.adicionaItemParaDoacao(user, descricaoItem, quantidade, tags);
  }

  public String exibeItem(int id, String idDoador) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.exibeItem(user, id);
  }

  public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.atualizaItemParaDoacao(user, id, quantidade, tags);
  }

  public void removeItemParaDoacao(int id, String idDoador) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    this.doadosController.removeItemParaDoacao(user, id);
  }

  public String listaDescritorDeItensParaDoacao() {
    return this.doadosController.listaDescritorDeItensParaDoacao();
  }

  public String listaItensParaDoacao() {
    return this.doadosController.listaItensParaDoacao();
  }

  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    return this.doadosController.pesquisaItemParaDoacaoPorDescricao(desc);
  }

  public String adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
    var usuario = this.usuarioController.getUsuario(idReceptor);

    return this.necessitadoController.cadastraItemPedido(usuario, descricao, quantidade, tags);
  }

  public String listaItensNecessarios() {
    return this.necessitadoController.listaTodos();
  }

  public String atualizaItemNecessario(String idReceptor, String idItem, int quantidade, String tags) {
    var usuario = this.usuarioController.getUsuario(idReceptor);

    return this.necessitadoController.atualizaItem(usuario, idItem, quantidade, tags);
  }

  public void removeItemNecessario(String idReceptor, String idItem) {
    var usuario = this.usuarioController.getUsuario(idReceptor);

    this.necessitadoController.removeItem(usuario, idItem);
  }

  public String match(String idReceptor, int idItemNecessario) {
    return "";
  }

  public String realizaDoacao(int idItemNecessitado, int idItemDoado, String data) {
    return this.doadosController.realizaDoacao(idItemDoado, this.necessitadoController.getItem(idItemNecessitado), data);
  }

  public void finalizaSistema() {
  }

  public void iniciaSistema() {
    
  }
}
