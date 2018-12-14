package edoe;

public class EDoeFacade {

  private Mediator mediator;

  public EDoeFacade() {
    this.mediator = new Mediator();
  }

  // US1
  public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
    return this.mediator.adicionaDoador(id, nome, email, celular, classe);
  }

  public String pesquisaUsuarioPorId(String id) {
    return this.mediator.pesquisaUsuarioPorId(id);
  }

  public String pesquisaUsuarioPorNome(String nome) {
    return this.mediator.pesquisaUsuarioPorNome(nome);
  }

  public String atualizaUsuario(String id, String nome, String email, String celular) {
    return this.mediator.atualizaUsuario(id, nome, email, celular);
  }

  public void removeUsuario(String id) {
    this.mediator.removeUsuario(id);
  }

  public void lerReceptores(String caminho) {
    this.mediator.lerReceptores(caminho);
  }

  // US2
  public void adicionaDescritor(String descricao) {
    this.mediator.adicionaDescritor(descricao);
  }

  public String adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
    return this.mediator.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
  }

  public String exibeItem(String id, String idDoador) {
    return this.mediator.exibeItem(id, idDoador);
  }

  public String atualizaItemParaDoacao(String id, String idDoador, int quantidade, String tags) {
    return this.mediator.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
  }

  public void removeItemParaDoacao(String id, String idDoador) {
    this.mediator.removeItemParaDoacao(id, idDoador);
  }

  // US3
  public String listaDescritorDeItensParaDoacao() {
    return this.mediator.listaDescritorDeItensParaDoacao();
  }

  public String listaItensParaDoacao() {
    return this.mediator.listaItensParaDoacao();
  }

  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    return this.mediator.pesquisaItemParaDoacaoPorDescricao(desc);
  }

  //US4
  public String adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
    return this.mediator.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
  }

  public String listaItensNecessarios() {
    return this.mediator.listaItensNecessarios();
  }

  public String atualizaItemNecessario(String idReceptor, String idItem, int novaQuantidade, String novasTags) {
    return this.mediator.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
  }

  public void removeItemNecessario(String idReceptor, String idItem) {
    this.mediator.removeItemNecessario(idReceptor, idItem);
  }

  // US5
  public String match (String idReceptor, int idItemNecessario) {
    return this.mediator.match(idReceptor, idItemNecessario);
  }

  // US6
  public String realizaDoacao (int idItemNec, int idItemDoado, String data) {
    return this.mediator.realizaDoacao(idItemNec, idItemDoado, data);
  }

  public String listaDoacoes() {
    return this.mediator.listaDoacoes();
  }

  // US7
  public void finalizaSistema() {
    this.mediator.finalizaSistema();
  }

  public void iniciaSistema() {
    this.mediator.iniciaSistema();
  }

}
