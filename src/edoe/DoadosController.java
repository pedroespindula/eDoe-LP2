package edoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Validador;

/**
 * controlador dos itens doados
 *
 * @author vitor
 * @author anderson
 */
public class DoadosController {

  private Map<Usuario, Map<Integer, Item>> items;
  private Set<String> descritores;

  /**
   * adiciona um descritor a colecao de descritores do sistema
   * @param descritor
   */
  public void adicionaDescritor(String descritor) {
    if (!this.descritores.contains(descritor)) {
      this.descritores.add(descritor);
    } else {
      Validador valida = new Validador("Erro");
      valida.verificaNulo(descritor, "Item ja existente");
    }
  }

  /**
   * Cadastra um novo item para doacao, se a sua descricao ja estiver cadastrada no sistema
   * 
   * @param id 
   * @param doador
   * @param descricao
   * @param quantidade
   * @param tags
   * @return id
   */
  public int adicionaItemParaDoacao(int id, Usuario doador, String descricao, int quantidade, String tags) {
	if (this.descritores.contains(descricao)) {
	  Item item = new Item(id, descricao, quantidade, tags, doador);
	  Map<Integer, Item> items = null;
	  items.put(id, item);
	  this.items.put(doador, items);
	} else {
	  Validador valida = new Validador("Erro");
	  valida.verificaNulo(descricao, "Descritor inexistente");
	} 
    return id;
  }


  public String exibeItem(Usuario doador, int id) {
    if (this.itemsPorDoador.containsKey(doador)) {
      List<Item> list = this.itemsPorDoador.get(doador);
      for (Item i : list) {
        if (id == i.getId()) {
          return i.toString();
        }
      }
      return this.itemsPorDoador.get(doador).get(id).toString();
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
