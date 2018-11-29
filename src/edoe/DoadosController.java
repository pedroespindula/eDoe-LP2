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
	private int id;

	public DoadosController() {
		this.itens = new HashMap<>();
		this.descricoes = new HashSet<>();
		this.id = 0;
	}

  /**
   * adiciona um descritor a colecao de descritores do sistema
   * @param descritor
   */
  public void adicionaDescritor(String descritor) {
    Validador validador = new Validador();
    validador.verificaStringNulaOuVazia(descritor, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaContem(descritor, this.itens,  "Descritor de Item ja existente: " + descritor + ".");
    this.descricoes.add(descritor);
  }

  /**
   * Cadastra um novo item para doacao, se a sua descricao ja estiver cadastrada no sistema
   *
   * @param doador
   * @param descricao
   * @param quantidade
   * @param tags
   * @return id
   */
public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    Validador validador = new Validador();
    validador.verificaStringVazia(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");
    this.id++;
    Item item = new Item(this.id, descricao, quantidade, tags, doador);
	  Map<Integer, Item> items = new HashMap<Integer, Item>();
	  items.put(this.id, item);
	  this.itens.put(doador, items);

    return id;
  }

  /**
   * Mostra um determinado item de um doador especifico
   * @param doador
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, int id) {
    Validador validador = new Validador();
    validador.verificaContem(doador, this.itens, "Item nao encontrado: " + id +  ".");

    return this.itens.get(doador).get(id).toString();
  }
  /**
   * Atualiza quantidade de unidades de um item de um doador e altera suas tags
   * 
   * @param doador
   * @param quantidade
   * @param tags
   * @return confirmacao ou negacao da atualizacao
   */
  public String atualizaItemParaDoacao(Usuario doador, int id, int quantidade, String tags) {
    Validador validador = new Validador();
    validador.verificaInteiroMaiorQueZero(this.id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaContem(doador, this.itens, "Item nao encontrado: " + id + ".");
    this.itens.get(doador).get(id).setQuantidade(quantidade);
    this.itens.get(doador).get(id).setTags(tags);
    return this.itens.get(doador).get(id).toString();
  }
  
  /**
   * Remove um item de um doador especifico
   * @param doador
   */
  public void removeItemParaDoacao(Usuario doador, int id) {
    Validador validador = new Validador();
    validador.verificaInteiroMaiorQueZero(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaContem(doador, this.itens, "Item nao encontrado: " + id + ".");
    validador.verificaContem(doador, this.itens, "O Usuario nao possui itens cadastrados.");

    this.itens.get(doador).remove(this.id);
  }
  
	public String listaDescritorDeItensParaDoacao() {
		return descricoes.stream().sorted()
				.map(descricao -> itens.values().stream()
						.flatMap(mapa -> mapa.values().stream())
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
		Validador validador = new Validador("Entrada invalida");
		validador.verificaStringNulaOuVazia(desc, "texto da pesquisa nao pode ser vazio ou nulo.");
		
		return this.itens.values().stream().flatMap(mapa -> mapa.values().stream())
				.filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
				.sorted(Comparator.comparing(Item::getDescricao)).map(item -> item.toString())
				.collect(Collectors.joining(" | "));
	}

}
