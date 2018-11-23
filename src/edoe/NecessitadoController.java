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
    items.putIfAbsent(itemTemp.getId(), itemTemp);

    this.itemsPorReceptor.put(receptor, items);

    return itemTemp.getId();
  }

  public String listaTodos() {
    return itemsPorReceptor.values().stream()
      .map(Map::values)
      .flatMap(Collection::stream)
      .sorted((i1, i2) -> i2.getQuantidade() - i1.getQuantidade())
      .map(i -> i.toString() + ", Receptor: " + i.getUsuario().getNome() + "/" + i.getUsuario().getId())
      .collect(Collectors.joining(" | "));
  }

  public String atualizaItem(Usuario receptor, String idItem, int quantidade, String tags) {
    var item = this.itemsPorReceptor.get(receptor).get(idItem);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (!tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  public void removeItem(Usuario receptor, String idItem) {
      this.itemsPorReceptor.get(receptor).remove(idItem);
  }

}
