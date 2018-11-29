package edoe;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	private void adicionaItemPorDescritor(int id, Usuario doador, String descricao, int quantidade, String tags,
			Item item) {
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

	/**
	 * Lista todos os descritores cadastrados e a quantidade de itens com aquela
	 * descricao no formato "quantidade - descricao" separadas por " | " caso
	 * contenha mais de uma descricao cadastrada. Caso nao exista nenhuma descricao
	 * cadastrada, o metodo retorna uma String vazia.
	 * 
	 * @return uma String com os descritores e a quantidade de itens cadastrados de
	 *         cada.
	 */
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
		Validador validador = new Validador("Entrada invalida");
		validador.verificaStringNulaOuVazia(desc, "texto da pesquisa nao pode ser vazio ou nulo.");

		return this.itens.values().stream().flatMap(mapa -> mapa.values().stream())
				.filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
				.sorted(Comparator.comparing(Item::getDescricao)).map(item -> item.toString())
				.collect(Collectors.joining(" | "));
	}

}
