package edoe;

import java.util.*;
import java.util.stream.Collectors;

import util.Validador;

/**
 * Controlador dos itens doados por usuarios doadores.
 * Permite armazenar e manipular esses itens.
 *
 * @author Vitor Diniz - 118110145
 * @author Anderson Felipe - 118111107
 */
public class DoadosController {

  private Set<String> descricoes;
  private Map<Usuario, Map<Integer, Item>> itens;
  private List<String> doacoes;
  private int cont;

  /**
   * cria um mapa e uma coleção para auxiliar a manipulação dos itens. Inicializa um contador que
   * será a identificação do item
   */
  public DoadosController() {
    this.itens = new HashMap<>();
    this.descricoes = new HashSet<>();
    this.cont = 1;
    this.doacoes = new ArrayList<String>();
  }

  /**
   * adiciona um descritor à colecao de descritores do sistema
   *
   * @param descritor
   */
  public void adicionaDescritor(String descritor) {
    Validador validador = new Validador();
    validador.verificaStringNulaOuVazia(descritor, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaContem(descritor.toLowerCase(), this.descricoes, "Descritor de Item ja existente: " + descritor.toLowerCase() + ".");
    this.descricoes.add(descritor.toLowerCase());
  }

  /**
   * Cadastra um novo item para doacao, se a sua descricao ja estiver cadastrada no sistema
   *
   * @param doador
   * @param descricao
   * @param quantidade
   * @param tags
   * @return id do item
   */
  public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");
    //validador.verificaNaoContem(descricao, descricoes, "Entrada invalida: descricao nao cadastrado.");
    this.descricoes.add(descricao);

    Map<Integer, Item> itensUsuario = this.itens.getOrDefault(doador, new HashMap<>());
    Item item = new Item(this.cont, descricao, quantidade, tags, doador);
    Item itemReal = itensUsuario.values().stream().filter(i -> i.equals(item)).findFirst().orElse(item);
    itemReal.setQuantidade(quantidade);

    itensUsuario.put(itemReal.getId(), itemReal);
    this.itens.put(doador, itensUsuario);
    this.cont += 1;
    return itemReal.getId();
  }

  /**
   * Mostra um determinado item de um doador especifico
   *
   * @param doador
   * @param id
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, int id) {
    Item item = this.getItem(doador, id);
    return item.toString();
  }

  /**
   * Atualiza quantidade de unidades de um item de um doador e altera suas tags
   *
   * @param doador
   * @param id
   * @param quantidade
   * @param tags
   * @return representação textual do item
   */
  public String atualizaItemParaDoacao(Usuario doador, int id, int quantidade, String tags) {
    Item item = this.getItem(doador, id);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (tags != null && !tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  /**
   * Remove um item de um doador especifico
   *
   * @param doador
   * @param id
   */
  public void removeItemParaDoacao(Usuario doador, int id) {
    this.getItem(doador, id);

    this.itens.get(doador).remove(id);
  }

  /**
   * Lista todos os descritores cadastrados e a quantidade de itens com aquela
   * descricao no formato "quantidade - descricao" separadas por " | " caso
   * contenha mais de uma descricao cadastrada. Caso nao exista nenhuma descricao
   * cadastrada, o metodo retorna uma String vazia.
   *
   * @return uma String com os descritores e a quantidade de itens cadastrados de
   * cada.
   */
  public String listaDescritorDeItensParaDoacao() {
    return descricoes.stream().sorted()
      .map(descricao -> itens.values().stream()
        .flatMap(mapa -> mapa.values().stream())
        .filter(item -> item.getDescricao().equals(descricao)).map(Item::getQuantidade).reduce(0, Integer::sum)
        + " - " + descricao)
      .collect(Collectors.joining(" | "));
  }

  /**
   * Lista todos os itens cadastrados ordenando prioritariamente pela quantidade
   * de forma decrescente, mas caso possuam a mesma quantidade a ordenacao sera de
   * forma crescente pela descricao.
   *
   * @return uma String com todos os itens cadastrados ordenados pela quantidade e
   * pela descricao.
   */
  public String listaItensParaDoacao() {
    return itens.values().stream().flatMap(mapa -> mapa.values().stream())
      .sorted(new ItemComparatorQuantidadeDescricao())
      .map(item -> item.toString() + ", doador: " + item.getUsuarioIdentificacao())
      .collect(Collectors.joining(" | "));

  }

  /**
   * Lista todos os itens que possuam na sua descricao a String passada por
   * parametro. Tanto as descricoes pegas pelo item quanto a String do parametro
   * serao comparadas ignorando os espacos laterais e o sensitive case.
   *
   * @param desc O pedaco de descricao que se quer encontrar nos itens.
   * @return uma String com uma lista de itens cadastrados que possuam na sua
   * descricao a String passada por parametro.
   */
  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    Validador validador = new Validador("Entrada invalida");
    validador.verificaStringNulaOuVazia(desc, "texto da pesquisa nao pode ser vazio ou nulo.");

    return this.itens.values().stream().flatMap(mapa -> mapa.values().stream())
      .filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
      .sorted(Comparator.comparing(Item::getDescricao)).map(item -> item.toString())
      .collect(Collectors.joining(" | "));
  }

  private Item getItem(Usuario doador, int id) {
    Validador validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(doador, this.itens, "O Usuario nao possui itens cadastrados.");

    Map<Integer, Item> itensUsuario = this.itens.get(doador);

    validador.verificaNaoContem(id, itensUsuario, "Item nao encontrado: " + id + ".");

    return itensUsuario.get(id);
  }

  public Item getItem (int id){
    Validador validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContemMapaDeMapa(id, this.itens, "Item nao encontrado: " + id + ".");

    for(Usuario user: this.itens.keySet()){
      if (this.itens.get(user).containsKey(id)){
        return this.itens.get(user).get(id);
      }
    }
    return null;
  }

  /**
   * realiza efetivamente uma doacao
   * 
   * @param idItemDoado
   * @param itemNecessitado
   * @param data
   * @return
   */
  public String realizaDoacao(int idItemDoado, Item itemNecessitado, String data) {
    Validador validador = new Validador();
    validador.verificaStringNulaOuVazia(data, "Entrada invalida: data nao pode ser vazia ou nula.");

    Item itemDoado = this.getItem(idItemDoado);
    validador.verificaStringsIguais(itemDoado.getDescricao(), itemNecessitado.getDescricao(), "Os itens nao tem descricoes iguais.");

    String doacao = data + " - doador: " + itemDoado.getUsuario().getNome() + "/" + itemDoado.getUsuario().getId() + ", " +
                    "item: " + itemDoado.getDescricao() + ", quantidade: " + itemDoado.getQuantidade() +  ", " +
                    "receptor: " + itemNecessitado.getUsuario().getNome() + "/" + itemNecessitado.getId();
    this.doacoes.add(doacao);
    Collections.sort(this.doacoes);
    return doacao;
  }

}
