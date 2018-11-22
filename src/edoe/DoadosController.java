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

  public String adicionaItemParaDoacao(int id, Usuario doador, String descricao, int quantidade, String tags) {
    adicionaItemPorDoador(id, doador, descricao, quantidade, tags);
    adicionaItemPorDescritor(id, doador, descricao, quantidade, tags);
    String ID = String.valueOf(id);
    return ID;
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

  public String atualizaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags, String id) {
    Integer ID = Integer.valueOf(id);
    if (this.itemsPorDoador.containsKey(doador)) {
      try {
      this.itemsPorDoador.get(doador).get(ID).setQuantidade(quantidade);
      this.itemsPorDoador.get(doador).get(ID).setTags(tags);
      this.itemsPorDescritor.get(descricao).get(ID).setQuantidade(quantidade);
      this.itemsPorDescritor.get(descricao).get(ID).setTags(tags);
      return id;
      } catch(IllegalArgumentException e) {   	}
    }
    return "Item nao encontrado.";
  }

  public void removeItemParaDoacao(Usuario doador, String id, String descritor) {
    int ID = Integer.valueOf(id);
    if (this.itemsPorDescritor.containsKey(descritor)) {
      this.itemsPorDescritor.get(descritor).remove(ID);
      this.itemsPorDoador.get(doador).remove(ID);
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
