package edoe;

import util.Validador;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador de itens necessitados por usuarios receptores.
 * Armazena esses itens e permite sua manipulacao (CRUD).
 *
 * @author Bruno Siqueira - 118110854
 */
public class NecessitadoController {

  private Map<Usuario, Map<Integer, Item>> itemsPorReceptor;
  private int contador;

  /**
   * Cria um novo NecessitadoController com itens vazios.
   */
  public NecessitadoController() {
    this.itemsPorReceptor = new HashMap<>();
    this.contador = 0;
  }

  /**
   * Cadastra um novo item necessitado por um usuario receptor,
   * retornando seu ID unico, sendo este iniciado em 0 e sempre incrementado quando um novo item e cadastrado.
   * Se o item a ser cadastrado ja pertence ao receptor,
   * entao somente a sua quantidade e alterada.
   * (Um item e identico a outro quando suas descricoes e tags sao iguais).
   *
   * @param receptor   usuario receptor que necessita do item a ser cadastrado
   * @param descritor  descricao do item a ser cadastrado
   * @param quantidade quantidade necessitada pelo receptor
   * @param tags       tags do item que o receptor necessita
   * @return em string o id do item cadastrado (numero inteiro 0+)
   */
  public String cadastraItemPedido(Usuario receptor, String descritor, int quantidade, String tags) {
    // Validacao
    var validador = new Validador("Entrada invalida");
    validador.verificaStringNulaOuVazia(descritor, "descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "quantidade deve ser maior que zero.");

    var items = this.itemsPorReceptor.getOrDefault(receptor, new HashMap<>());
    var itemTemp = new Item(this.contador, descritor, quantidade, tags, receptor);

    // Checa item existente para apenas alterar quantidade -> nao especificado
    var itemExistente = items.values().stream()
      .filter(i -> i.equals(itemTemp))
      .findFirst()
      .orElse(null);

    if (itemExistente != null) {
      itemExistente.setQuantidade(quantidade);
      return String.valueOf(itemExistente.getId());
    }
    //

    items.putIfAbsent(itemTemp.getId(), itemTemp);
    this.itemsPorReceptor.put(receptor, items);

    this.contador += 1;
    return String.valueOf(itemTemp.getId());
  }

  /**
   * Representa todos os itens (atraves de seus toString) atualmente necessitados por todos os receptores.
   * Os items sao ordenados de acordo com seus IDs (independente do receptor).
   *
   * @return uma string contendo todos os items separados por " | ".
   * ex.: id1 - descrição, tags: [tag1, tag2, ...], quantidade: n, Receptor: Fulano | id2... | id3...
   */
  public String listaTodos() {
    return itemsPorReceptor.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted(Comparator.comparingInt(Item::getId))
      .map(i -> i.toString() + ", Receptor: " + i.getUsuarioIdentificacao())
      .collect(Collectors.joining(" | "));
  }

  /**
   * Atualiza a quantidade e/ou tags de um item previamente cadastrado.
   *
   * @param receptor   receptor que necessita do item
   * @param idItem     o id do item a ser atualizado
   * @param quantidade a nova quantidade do item (parametro ignorado se menor que 0)
   * @param tags       as novas tags do item (parametro ignorado se null ou vazio)
   * @return a representacao em String do item agora atualizado.
   */
  public String atualizaItem(Usuario receptor, String idItem, int quantidade, String tags) {
    var id = Integer.parseInt(idItem);
    var item = this.getItem(receptor, id);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (tags != null && !tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  /**
   * Remove um item necessitado que pertence a um receptor.
   *
   * @param receptor o receptor do item a ser removido
   * @param idItem   id do item a ser removido
   */
  public void removeItem(Usuario receptor, String idItem) {
    var id = Integer.parseInt(idItem);
    getItem(receptor, id);

    this.itemsPorReceptor.get(receptor).remove(id);
  }

  private Item getItem(Usuario receptor, int id) {
    // Validacao
    var validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(receptor, this.itemsPorReceptor, "O Usuario nao possui itens cadastrados.");

    var itemsUsuario = this.itemsPorReceptor.get(receptor);
    validador.verificaNaoContem(id, itemsUsuario, "Item nao encontrado: " + id + ".");

    return itemsUsuario.get(id);
  }

  public Item getItem(int id) {
    Validador validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContemMapaDeMapa(id, this.itemsPorReceptor, "Item nao encontrado: " + id + ".");

    for (Map<Integer, Item> m: this.itemsPorReceptor.values()){
      if (m.containsKey(id)){
        return m.get(id);
      }
    }
    return null;

  }
}
