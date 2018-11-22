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

  public String adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    
	
	  
	return "";
  }

  public String exibeItem (Usuario doador, String id) {
    return "";
  }

  public String atualizaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    return "";
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
