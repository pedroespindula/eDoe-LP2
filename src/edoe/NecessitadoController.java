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
    var items = this.itemsPorReceptor.getOrDefault(receptor, new HashMap<>());
    var itemTemp = new Item(contador, descritor, quantidade, tags, receptor);
    items.putIfAbsent(itemTemp.getId(), itemTemp);

    this.itemsPorReceptor.put(receptor, items);

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
    var item = this.itemsPorReceptor.get(receptor).get(Integer.parseInt(idItem));

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

}
