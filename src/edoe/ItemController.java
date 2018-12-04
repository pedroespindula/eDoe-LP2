package edoe;

import util.Validador;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ItemController {
  protected Map<Usuario, Map<Integer, Item>> usuarioItensMap;
  protected int contador;

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
   * @param usuario   usuario que necessita do item a ser cadastrado
   * @param descritor  descricao do item a ser cadastrado
   * @param quantidade quantidade do item
   * @param tags       tags do item
   * @return em string o id do item cadastrado (numero inteiro >= 0)
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
   * Representa todos os itens (atraves de seus toString) atualmente necessitados por todos os receptores.
   * Os items sao ordenados de acordo com seus IDs (independente do receptor).
   *
   * @return uma string contendo todos os items separados por " | ".
   * ex.: id1 - descrição, tags: [tag1, tag2, ...], quantidade: n, Receptor: Fulano | id2... | id3...
   */
  protected String listaTodos(String tipoUsuario) {
    return this.listaTodos(tipoUsuario, Comparator.comparingInt(Item::getId));
  }

  protected String listaTodos(String tipoUsuario, Comparator<Item> comparador) {
    return usuarioItensMap.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted(comparador)
      .map(i -> i.toString() + ", " + tipoUsuario + ": " + i.getUsuarioIdentificacao())
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

    this.usuarioItensMap.get(receptor).remove(id);
  }

  public Item getItem(Usuario receptor, int id) {
    // Validacao
    var validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(receptor, this.usuarioItensMap, "O Usuario nao possui itens cadastrados.");

    var itemsUsuario = this.usuarioItensMap.get(receptor);
    validador.verificaNaoContem(id, itemsUsuario, "Item nao encontrado: " + id + ".");

    return itemsUsuario.get(id);
  }
}
