package edoe;

import util.Validador;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controlador de itens abstratos,
 * onde se armazena um mapa de Usuarios para outro mapa,
 * que por sua vez mapeia de Integer (id do item) para um item.
 *
 * Armazena esses itens e permite sua manipulacao (CRUD).
 */
public abstract class ItemController {
  protected Map<Usuario, Map<Integer, Item>> usuarioItensMap;
  protected int contador;

  /**
   * Cria um novo controlador de Itens,
   * iniciando seu contador (de onde surgirao os id dos itens) em 1.
   */
  public ItemController() {
    this.usuarioItensMap = new HashMap<>();
    this.contador = 1;
  }

  /**
   * Cadastra um novo item associado a um usuario,
   * retornando seu ID unico, sendo este iniciado em 0 e sempre incrementado quando um novo item e cadastrado.
   * Se o item a ser cadastrado ja pertence ao usuario,
   * entao somente a sua quantidade e alterada.
   * (Um item e identico a outro quando suas descricoes e tags sao iguais).
   *
   * @param usuario   usuario associado ao item a ser cadastrado
   * @param descritor  descricao do item a ser cadastrado
   * @param quantidade quantidade do item
   * @param tags       tags do item
   * @return em string o id do item cadastrado (numero inteiro > 0)
   */
  public String cadastraItem(Usuario usuario, String descritor, int quantidade, String tags) {
    // Validacao
    var validador = new Validador("Entrada invalida");
    validador.verificaStringNulaOuVazia(descritor, "descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "quantidade deve ser maior que zero.");

    var items = this.usuarioItensMap.getOrDefault(usuario, new HashMap<>());
    var itemTemp = new Item(this.contador, descritor, quantidade, tags, usuario);

    var item = items.values().stream()
      .filter(i -> i.equals(itemTemp))
      .findFirst()
      .orElse(itemTemp);

    item.setQuantidade(quantidade);

    items.putIfAbsent(item.getId(), item);
    this.usuarioItensMap.put(usuario, items);

    this.contador += 1;
    return String.valueOf(item.getId());
  }

  /**
   * Representa todos os itens (atraves de seus toString) com a adicao de informacao quanto ao usuario.
   * Os items sao ordenados de acordo com o segundo parametro passado (Um Comparator).
   *
   * @param comparador um Comparator que ordenara a lista de exibicao
   * @return uma string contendo todos os items separados por " | ".
   * ex.: id1 - descrição, tags: [tag1, tag2, ...], quantidade: n, <doador/Receptor>: Fulano | id2... | id3...
   */
  protected String listaTodos(Comparator<Item> comparador) {
    return usuarioItensMap.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted(comparador)
      .map(Item::toStringComUsuario)
      .collect(Collectors.joining(" | "));
  }

  /**
   * Atualiza a quantidade e/ou tags de um item previamente cadastrado.
   *
   * @param usuario    usuario associado ao item
   * @param idItem     o id do item a ser atualizado
   * @param quantidade a nova quantidade do item (parametro ignorado se menor que 0)
   * @param tags       as novas tags do item (parametro ignorado se null ou vazio)
   * @return a representacao em String do item agora atualizado.
   */
  public String atualizaItem(Usuario usuario, String idItem, int quantidade, String tags) {
    var id = Integer.parseInt(idItem);
    var item = this.getItem(usuario, id);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (tags != null && !tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  /**
   * Remove um item que esta associado a um usuario.
   *
   * @param usuario o usuario associado ao item a ser removido.
   * @param idItem   id do item a ser removido.
   */
  public void removeItem(Usuario usuario, String idItem) {
    var id = Integer.parseInt(idItem);
    getItem(usuario, id);

    this.usuarioItensMap.get(usuario).remove(id);
  }

  public Item getItem(Usuario usuario, int id) {
    // Validacao
    var validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(usuario, this.usuarioItensMap, "O Usuario nao possui itens cadastrados.");

    var itemsUsuario = this.usuarioItensMap.get(usuario);
    validador.verificaNaoContem(id, itemsUsuario, "Item nao encontrado: " + id + ".");

    return itemsUsuario.get(id);
  }

  public List<Item> getItensUsuario(Usuario usuario) {
    return new ArrayList<>(this.usuarioItensMap.get(usuario).values());
  }

  public List<Item> getTodosItens() {
    return this.usuarioItensMap.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
  }
}
