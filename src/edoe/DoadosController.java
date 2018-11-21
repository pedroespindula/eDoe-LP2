package edoe;

import java.util.List;
import java.util.Map;

public class DoadosController {

  private Map<Usuario, List<Item>> itemsPorDoador;
  private Map<String, List<Item>> itemsPorDescritor;

  public void adiconaDescritor (String descritor) {

  }

  public String cadastraItemDoacao (Usuario doador, String descricao, int quantidade, String tags) {
    return "";
  }

  public String exibeItem (Usuario doador, String id) {
    return "";
  }

  public String atualizaItemDoacao (Usuario doador, String descricao, int quantidade, String tags) {
    return "";
  }

  public void removeItemDoacao (Usuario doador, String id) {

  }

  public String listaDescritorItemDoacao() {
    return "";
  }

  public String listaItemDoacao() {
    return "";
  }

  public String pesquisaItemParaDoacaoPorDescricao (String desc) {
    return "";
  }

}
