package edoe;

import util.Validador;

import java.util.*;
import java.util.stream.Collectors;

public class NecessitadoController {

  private Map<Usuario, Map<String, Item>> itemsPorReceptor;

  public NecessitadoController() {
    this.itemsPorReceptor = new HashMap<>();
  }

  public String cadastraItemPedido(Usuario receptor, String descritor, int quantidade, String tags) {
    var items = this.itemsPorReceptor.getOrDefault(receptor, new HashMap<>());
    var itemTemp = new Item(descritor, quantidade, tags, receptor);
    Item item = items.getOrDefault(itemTemp.getId(), itemTemp);

    items.put(item.getId(), item);
    this.itemsPorReceptor.put(receptor, items);

    return item.getId();
  }

  public String listaTodos() {
    return itemsPorReceptor.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted((i1, i2) -> i2.getQuantidade() - i1.getQuantidade())
      .map(i -> i.toString() + ", Receptor: " + i.getUsuario().getNome() + "/" + i.getUsuario().getId())
      .collect(Collectors.joining(" | "));
  }

  public void atualizaItem(Usuario receptor, String idItem, int quantidade, String tags) {


  }

  public void removeItem(Usuario receptor, String idItem) {

  }

}
