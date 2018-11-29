package edoe;

import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Set;

import util.Validador;

/**
 * controlador dos itens doados
 *
 * @author vitor
 * @author anderson
 */
public class DoadosController {

	private Set<String> descricoes;
	private Map<Usuario, Map<Integer, Item>> itens;

	public DoadosController() {
		this.itens = new HashMap<>();
		this.descricoes = new HashSet<>();
	}

  /**
   * adiciona um descritor a colecao de descritores do sistema
   * @param descritor
   */
  public void adicionaDescritor(String descritor) {
    Validador validador = new Validador();
    validador.verificaStringVazia(descritor, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaContem(descritor, this.itens,  "Descritor de Item ja existente: "descritor + ".");
    this.descricoes.add(descritor);
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
    Validador validador = new Validador();
    validador.verificaStringVazia(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");
    validador.verificaNulo(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
    validador.verificaContem(doador, this.itens, "Usuario nao encontrado: " + doador + ".");

    Item item = new Item(id, descricao, quantidade, tags, doador);
	  Map<Integer, Item> items = new HashMap<Integer, Item>();
	  items.put(id, item);
	  this.itens.put(doador, items);

    return id;
  }

  /**
   * Mostra um determinado item de um doador especifico
   * @param doador
   * @param id
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, int id) {
    if (this.itens.containsKey(doador)) {
      return this.itens.get(doador).get(id).toString();
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
    if (this.itens.containsKey(doador)) {
      if (this.itens.get(doador).containsKey(id)) {
    	this.itens.get(doador).get(id).setQuantidade(quantidade);
    	this.itens.get(doador).get(id).setTags(tags);
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
    if (this.itens.containsKey(doador)) {
      if (this.itens.get(doador).containsKey(id)) {
        this.itens.get(doador).remove(id);
      }
    }
  }
  
	public String listaDescritorDeItensParaDoacao() {
		return descricoes.stream().sorted()
				.map(descricao -> itens.values().stream().flatMap(mapa -> mapa.values().stream())
						.filter(item -> item.getDescricao().equals(descricao)).map(Item::getQuantidade).reduce(0, Integer::sum)
						+ " - " + descricao)
				.collect(Collectors.joining(" | "));

	}

	public String listaItensParaDoacao() {
		return itens.values().stream().flatMap(mapa -> mapa.values().stream())
				.sorted(new ItemComparatorQuantidadeDescricao())
				.map(item -> item.toString() + ", doador: " + item.getUsuarioIdentificacao())
				.collect(Collectors.joining(" | "));

	}

	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return this.itens.values().stream().flatMap(mapa -> mapa.values().stream())
				.filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
				.sorted(Comparator.comparing(Item::getDescricao)).map(item -> item.toString())
				.collect(Collectors.joining(" | "));
	}

}
