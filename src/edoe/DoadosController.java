package edoe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Set;

public class DoadosController {

	private Set<String> descricoes;
	private Map<Usuario, Map<Integer, Item>> itens;

	public DoadosController() {
		this.itens = new HashMap<>();
		this.descricoes = new HashSet<>();
	}

	public void adicionaDescritor(String descritor) {

	}

	public String adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
		return "";
	}

	public String exibeItem(Usuario doador, String id) {
		return "";
	}

	public String atualizaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
		return "";
	}

	public void removeItemParaDoacao(Usuario doador, String id) {

	}

	public String listaDescritorDeItensParaDoacao() {

		return descricoes.stream()
				.sorted()
				.map(descricao -> itens.values().stream()
						.flatMap(mapa -> mapa.values().stream())
						.filter(item -> item.getDescricao().equals(descricao))
						.map(Item::getQuantidade)
						.reduce(0, Integer::sum)	+ " - " + descricao)
				.collect(Collectors.joining(" | "));

	}

	public String listaItensParaDoacao() {
		return null;
	}

	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return this.itens.values().stream()
				.flatMap(mapa -> mapa.values().stream())
				.filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
				.map(item -> item.toString())
				.collect(Collectors.joining(" | "));
	}

}
