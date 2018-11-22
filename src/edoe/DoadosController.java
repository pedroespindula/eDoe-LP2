package edoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author vitor
 * @author anderson
 */
public class DoadosController {

  private Map<Usuario, List<Item>> itemsPorDoador;
  private Map<String, List<Item>> itemsPorDescritor;

  public DoadosController() {

  }

  public void adicionaDescritor (String descritor) {
    if (!this.itemsPorDescritor.containsKey(descritor)) {
      List <Item> lista = new ArrayList <Item>();
      this.itemsPorDescritor.put(descritor, lista);		
	}
    else {
      throw new IllegalArgumentException();
    }
  }

  public int adicionaItemParaDoacao(int id, Usuario doador, String descricao, int quantidade, String tags) {
    adicionaItemPorDoador(id, doador, descricao, quantidade, tags);
    adicionaItemPorDescritor(id, doador, descricao, quantidade, tags);
    return id;
  }
  private void adicionaItemPorDoador(int id, Usuario doador, String descricao, int quantidade, String tags) {
    if (this.itemsPorDoador.containsKey(doador)) {
      Item item = new Item (id,descricao, quantidade, tags, doador);
      this.itemsPorDoador.get(doador).add(item);
    }
    else {
   	  List <Item> lista = new ArrayList <Item>();
      Item item = new Item (id,descricao, quantidade, tags, doador);
      lista.add(item);
      this.itemsPorDoador.put(doador, lista);
    }
  }
  private void adicionaItemPorDescritor(int id, Usuario doador, String descricao, int quantidade, String tags) {
    if (this.itemsPorDescritor.containsKey(descricao)) {
      Item item = new Item (id,descricao, quantidade, tags, doador);
      this.itemsPorDescritor.get(descricao).add(item);
    }
    else {
      List <Item> lista = new ArrayList <Item>();
      Item item = new Item (id,descricao, quantidade, tags, doador);
      lista.add(item);
      this.itemsPorDescritor.put(descricao, lista);
    }  
  }
  
  public String exibeItem (Usuario doador, String id) {
    Integer ID = Integer.valueOf(id);
    if (this.itemsPorDoador.containsKey(doador)) {
     try {
     return this.itemsPorDoador.get(doador).get(ID).toString();
     } catch (IllegalArgumentException e) {	  }
    }
    return "Item nao encontrado.";
 }

  public int atualizaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags, int id) {
    if (this.itemsPorDoador.containsKey(doador)) {
      try {
      this.itemsPorDoador.get(doador).get(id).setQuantidade(quantidade);
      this.itemsPorDoador.get(doador).get(id).setTags(tags);
      this.itemsPorDescritor.get(descricao).get(id).setQuantidade(quantidade);
      this.itemsPorDescritor.get(descricao).get(id).setTags(tags);
      return id;
      } catch(IllegalArgumentException e) {   	}
    }
    return -1;
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

  public String pesquisaItemParaDoacaoPorDescricao (String desc) {
    return "";
  }

}
