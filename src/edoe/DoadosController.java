package edoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Validador;

/**
 * controlador dos itens doados
 *
 * @author vitor
 * @author anderson
 */
public class DoadosController {

  private Map<Usuario, List<Item>> itemsPorDoador;
  private Map<String, List<Item>> itemsPorDescritor;

  public void adicionaDescritor(String descritor) {
    if (!this.itemsPorDescritor.containsKey(descritor)) {
      List<Item> lista = new ArrayList<Item>();
      this.itemsPorDescritor.put(descritor, lista);
    } else {
      Validador valida = new Validador("Erro");
      valida.verificaNulo(this.itemsPorDescritor.get(descritor), "Item inexistente");
    }
  }

  public int adicionaItemParaDoacao(int id, Usuario doador, String descricao, int quantidade, String tags) {
    Item item = new Item(id, descricao, quantidade, tags, doador);
    adicionaItemPorDoador(id, doador, descricao, quantidade, tags, item);
    adicionaItemPorDescritor(id, doador, descricao, quantidade, tags, item);
    return id;
  }

  private void adicionaItemPorDoador(int id, Usuario doador, String descricao, int quantidade, String tags, Item item) {
    if (this.itemsPorDoador.containsKey(doador)) {
      this.itemsPorDoador.get(doador).add(item);
    } else {
      List<Item> lista = new ArrayList<Item>();
      lista.add(item);
      this.itemsPorDoador.put(doador, lista);
    }
  }

  private void adicionaItemPorDescritor(int id, Usuario doador, String descricao, int quantidade, String tags, Item item) {
    if (this.itemsPorDescritor.containsKey(descricao)) {
      this.itemsPorDescritor.get(descricao).add(item);
    } else {
      List<Item> lista = new ArrayList<Item>();
      lista.add(item);
      this.itemsPorDescritor.put(descricao, lista);
    }
  }

  public String exibeItem(Usuario doador, int id) {
    if (this.itemsPorDoador.containsKey(doador)) {
      List<Item> list = this.itemsPorDoador.get(doador);
      for (Item i : list) {
        if (i.getId() == id) {
          return i.toString();
        }
      }
    }
    return "Item nao encontrado.";
  }

  public String atualizaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    if (this.itemsPorDoador.containsKey(doador)) {
      for (int i = 0; i < this.itemsPorDoador.get(doador).size(); i++) {
        for (int j = 0; j < this.itemsPorDescritor.get(descricao).size(); j++) {
          if (this.itemsPorDoador.get(doador).get(i).equals(this.itemsPorDescritor.get(descricao).get(j))) {
            this.itemsPorDoador.get(doador).get(i).setTags(tags);
            this.itemsPorDoador.get(doador).get(i).setQuantidade(quantidade);
          }
        }
      }
      return "Item atualizado.";
    }
    return "Item nao encontrado.";
  }

  public void removeItemParaDoacao(Usuario doador, int id, String descritor) {
    if (this.itemsPorDescritor.containsKey(descritor)) {
      this.itemsPorDescritor.get(descritor).remove(id);
      this.itemsPorDoador.get(doador).remove(id);
    }
  }

  public String listaDescritorDeItensParaDoacao() {
    return "";
  }

  public String listaItensParaDoacao() {
    return "";
  }

  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    return "";
  }

}
