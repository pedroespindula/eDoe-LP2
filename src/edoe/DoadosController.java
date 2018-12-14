package edoe;

import java.util.Map;
import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.Set;

import util.Validador;

/**
 * Controlador dos itens doados por usuarios doadores.
 * Permite armazenar e manipular esses itens.
 *
 * @author Vitor Diniz - 118110145
 * @author Anderson Felipe - 118111107
 */
public class DoadosController extends ItemController {

  private Set<String> descricoes;

  /**
   * cria um mapa e uma coleção para auxiliar a manipulação dos itens. Inicializa um contador que
   * será a identificação do item
   */
  public DoadosController() {
    super();
    this.descricoes = new HashSet<>();
  }

  /**
   * adiciona um descritor à colecao de descritores do sistema
   *
   * @param descritor
   */
  public void adicionaDescritor(String descritor) {
    Validador validador = new Validador();
    validador.verificaStringNulaOuVazia(descritor, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaContem(descritor.toLowerCase(), this.descricoes, "Descritor de Item ja existente: " + descritor.toLowerCase() + ".");
    this.descricoes.add(descritor.toLowerCase());
  }

  /**
   * Cadastra um item no sistema,
   * adicionando sua descricao a colecao de descritores caso nao esteja ja presente.
   * @param usuario   usuario associado ao item a ser cadastrado
   * @param descricao descricao do item
   * @param quantidade quantidade do item
   * @param tags       tags do item
   * @return o id do item cadastrado
   */
  @Override
  public String cadastraItem(Usuario usuario, String descricao, int quantidade, String tags) {
    if (!this.descricoes.contains(descricao)) {
      this.adicionaDescritor(descricao);
    }

    return super.cadastraItem(usuario, descricao, quantidade, tags);
  }

  /**
   * Mostra um determinado item de um doador especifico
   *
   * @param doador o usuario associado ao item
   * @param idItem id do item a ser exibido
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, String idItem) {
    var id = Integer.parseInt(idItem);
    Item item = this.getItem(doador, id);
    return item.toString();
  }

  /**
   * Lista todos os descritores cadastrados e a quantidade de itens com aquela
   * descricao no formato "quantidade - descricao" separadas por " | " caso
   * contenha mais de uma descricao cadastrada. Caso nao exista nenhuma descricao
   * cadastrada, o metodo retorna uma String vazia.
   *
   * @return uma String com os descritores e a quantidade de itens cadastrados de
   * cada.
   */
  public String listaDescritorDeItensParaDoacao() {
    return descricoes.stream().sorted()
      .map(descricao -> usuarioItensMap.values().stream()
        .flatMap(mapa -> mapa.values().stream())
        .filter(item -> item.getDescricao().equals(descricao)).map(Item::getQuantidade).reduce(0, Integer::sum)
        + " - " + descricao)
      .collect(Collectors.joining(" | "));
  }

  /**
   * Lista todos os itens cadastrados ordenando prioritariamente pela quantidade
   * de forma decrescente, mas caso possuam a mesma quantidade a ordenacao sera de
   * forma crescente pela descricao.
   *
   * @return uma String com todos os itens cadastrados ordenados pela quantidade e
   * pela descricao.
   */
  public String listaItensParaDoacao() {
    return this.listaTodos(Comparator
        .comparingInt(Item::getQuantidade)
        .reversed() // Do maior para o menor
        .thenComparing(Item::getDescricao));
  }

  /**
   * Lista todos os itens que possuam na sua descricao a String passada por
   * parametro. Tanto as descricoes pegas pelo item quanto a String do parametro
   * serao comparadas ignorando os espacos laterais e o sensitive case.
   *
   * @param desc O pedaco de descricao que se quer encontrar nos itens.
   * @return uma String com uma lista de itens cadastrados que possuam na sua
   * descricao a String passada por parametro.
   */
  public String pesquisaItemParaDoacaoPorDescricao(String desc) {
    Validador validador = new Validador("Entrada invalida");
    validador.verificaStringNulaOuVazia(desc, "texto da pesquisa nao pode ser vazio ou nulo.");

    return this.usuarioItensMap.values().stream().flatMap(mapa -> mapa.values().stream())
      .filter(item -> item.getDescricao().toLowerCase().trim().contains(desc.toLowerCase().trim()))
      .sorted(Comparator.comparing(Item::getDescricao))
      .map(Item::toString)
      .collect(Collectors.joining(" | "));
  }

	public void salvar() throws IOException {
		Persistencia persistencia = new Persistencia();
		persistencia.salvar(super.usuarioItensMap, "GuardaDados/ItensDoados.txt");
	}
}
