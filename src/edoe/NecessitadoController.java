package edoe;

import util.Validador;

import java.util.*;
import java.util.stream.Collectors;

public class NecessitadoController {

  private Map<Usuario, Map<Integer, Item>> itemsPorReceptor;
  private int contador;

  public NecessitadoController() {
    this.itemsPorReceptor = new HashMap<>();
    this.contador = 0;
  }

  public String cadastraItemPedido(Usuario receptor, String descritor, int quantidade, String tags) {
    // Validacao
    var validador = new Validador("Entrada invalida");
    validador.verificaStringNulaOuVazia(descritor, "descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "quantidade deve ser maior que zero.");

    var items = this.itemsPorReceptor.getOrDefault(receptor, new HashMap<>());
    var itemTemp = new Item(this.contador, descritor, quantidade, tags, receptor);
    items.putIfAbsent(itemTemp.getId(), itemTemp);

    this.itemsPorReceptor.put(receptor, items);

    this.contador += 1;
    return String.valueOf(itemTemp.getId());
  }

  public String listaTodos() {
    return itemsPorReceptor.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted((i1, i2) -> i2.getQuantidade() - i1.getQuantidade())
      .map(i -> i.toString() + ", Receptor: " + i.getUsuarioIdentificacao())
      .collect(Collectors.joining(" | "));
  }

  public String atualizaItem(Usuario receptor, String idItem, int quantidade, String tags) {
    var id = Integer.parseInt(idItem);
    var item = this.getItem(receptor, id);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (!tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  public void removeItem(Usuario receptor, String idItem) {
      this.itemsPorReceptor.get(receptor).remove(Integer.parseInt(idItem));
  }

  private Item getItem(Usuario receptor, int id) {
    // Validacao
    var validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(receptor, this.itemsPorReceptor, "O Usuario nao possui itens cadastrados.");

    var itemsUsuario = this.itemsPorReceptor.get(receptor);
    validador.verificaNaoContem(id, itemsUsuario, "Item nao encontrado: " + id);

    return itemsUsuario.get(id);
  }
}
