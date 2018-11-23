package edoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map.Entry;
public class DoadosController {

	private Map<Usuario, List<Item>> itemsPorDoador;
	private Map<String, List<Item>> itemsPorDescritor;
	
	public static void main(String[] args) {
		DoadosController d = new DoadosController();
		System.out.println(d.pesquisaItemParaDoacaoPorDescricao("cad"));
	}

	public DoadosController() {
		itemsPorDescritor = new TreeMap<>();
		itemsPorDescritor.put("cadeira de rodas", new ArrayList<>()); 
		itemsPorDescritor.put("curso de programacao", new ArrayList<>());
		itemsPorDescritor.put("cobertor", new ArrayList<>());
		
		itemsPorDescritor.get("cadeira de rodas").add(new Item("cadz", 5, "asdas asda", new Usuario("1", "Andre", "asda", "98952", "irra", true)));
		itemsPorDescritor.get("cadeira de rodas").add(new Item("cada", 3, "asdas asda", new Usuario("1", "Andre", "asda", "98952", "irra", true)));
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
		/*List<String> listaDescricoes = new ArrayList<String>();

		for (String chave : itemsPorDescritor.keySet()) {
			listaDescricoes.add(this.quantidadeDescricao(chave) + " - " + chave);
		}

		return String.join(" | ",listaDescricoes);
		*/
		return this.itemsPorDescritor.entrySet().stream()
				.map(l -> l.getValue().stream()
						.map(item -> item.getQuantidade())
						.reduce(0, Integer::sum) + " - " + l.getKey())
				.collect(Collectors.joining( " | "));
	
	}

	/*private int quantidadeDescricao(String chave) {
		int somador = 0;
		List<Item> items = itemsPorDescritor.get(chave);
		
		for(Item item: items) {
			somador += item.getQuantidade();
		}
		
		return somador;
	}*/

	public String listaItensParaDoacao() {
		//this.itemsPorDescritor.values().stream().flatMap(List::stream).
		return null;
	}

	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return this.itemsPorDescritor.entrySet().stream()
				.filter(tupla -> tupla.getKey().contains(desc))
				.map(Entry::getValue)
				.flatMap(lista -> lista.stream())
				.sorted(Comparator.comparing(Item::getDescricao))
				.map(Item::toString).collect(Collectors.joining(" | "));
	}

}
