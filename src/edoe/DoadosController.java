package edoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  }

  public String adicionaItemParaDoacao(int id, Usuario doador, String descricao, int quantidade, String tags) {
    adicionaItemPorDoador(id, doador, descricao, quantidade, tags);
    adicionaItemPorDescritor(id, doador, descricao, quantidade, tags);
	return "Item cadastrado com sucesso.";
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
		} catch(IllegalArgumentException e) {   	}
	}
    return "Item nao encontrado.";
  }

  public void removeItemParaDoacao(Usuario doador, String id) {

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
