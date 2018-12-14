package edoe;

public class Mediator {

  private UsuarioController usuarioController;
  private DoadosController doadosController;
  private NecessitadoController necessitadoController;
  private DoacaoController doacaoController;

  public Mediator() {
    this.usuarioController = new UsuarioController();
    this.doadosController = new DoadosController();
    this.necessitadoController = new NecessitadoController();
    this.doacaoController = new DoacaoController();
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

  public String adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.cadastraItem(user, descricaoItem, quantidade, tags);
  }

  public String exibeItem(String id, String idDoador) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.exibeItem(user, id);
  }

  public String atualizaItemParaDoacao(String id, String idDoador, int quantidade, String tags) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    return this.doadosController.atualizaItem(user, id, quantidade, tags);
  }

  public void removeItemParaDoacao(String id, String idDoador) {
    Usuario user = this.usuarioController.getUsuario(idDoador);
    this.doadosController.removeItem(user, id);
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

    return this.necessitadoController.cadastraItem(usuario, descricao, quantidade, tags);
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
    var user = this.usuarioController.getUsuario(idReceptor);
    var item = this.necessitadoController.getItem(user, idItemNecessario);
    var todosItems = this.doadosController.getTodosItens();
    var matcher = new ItemMatcher(item);

    return matcher.match(todosItems);
  }

  public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
    Item itemNec = this.necessitadoController.getItemPorId(idItemNec);
    Item itemDoado = this.doadosController.getItemPorId(idItemDoado);
    String result = this.doacaoController.realizaDoacao(itemNec, itemDoado, data);

    this.necessitadoController.removeItemVazio(itemNec);
    this.doadosController.removeItemVazio(itemDoado);

    return result;
  }

  public String listaDoacoes() {
    return this.doacaoController.listaDoacoes();
  }

  public void finalizaSistema() {
  	this.doadosController.salvar();
  	this.necessitadoController.salvar();
  	this.usuarioController.salvar();
  }
  
}
