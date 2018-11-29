package edoe;

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
  @SuppressWarnings("null")
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

  /**
   * Mostra um determinado item de um doador especifico
   * @param doador
   * @param id
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, int id) {
    if (this.items.containsKey(doador)) {
      return this.items.get(doador).get(id).toString();
    }
    return "Item nao encontrado.";
  }
  /**
   * Atualiza quantidade de unidades de um item de um doador e altera suas tags
   * 
   * @param doador
   * @param id
   * @param quantidade
   * @param tags
   * @return confirmacao ou negacao da atualizacao
   */
  public String atualizaItemParaDoacao(Usuario doador, int id, int quantidade, String tags) {
    if (this.items.containsKey(doador)) {
      if (this.items.get(doador).containsKey(id)) {
    	this.items.get(doador).get(id).setQuantidade(quantidade);
    	this.items.get(doador).get(id).setTags(tags);
        return "Item atualizado.";	  
      }
    }
    return "Item nao encontrado.";
  }
  
  /**
   * Remove um item de um doador especifico
   * @param doador
   * @param id
   */
  public void removeItemParaDoacao(Usuario doador, int id) {
    if (this.items.containsKey(doador)) {
      if (this.items.get(doador).containsKey(id)) {
        this.items.get(doador).remove(id);
      }
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
