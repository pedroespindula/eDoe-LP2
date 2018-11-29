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
  private int cont;

  public DoadosController() {
    this.itens = new HashMap<>();
    this.descricoes = new HashSet<>();
    this.cont = 1;
  }

  /**
   * adiciona um descritor a colecao de descritores do sistema
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
   * Cadastra um novo item para doacao, se a sua descricao ja estiver cadastrada no sistema
   *
   * @param doador
   * @param descricao
   * @param quantidade
   * @param tags
   * @return cont
   */
  public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
    validador.verificaInteiroMaiorQueZero(quantidade, "Entrada invalida: quantidade deve ser maior que zero.");
    //validador.verificaNaoContem(descricao, descricoes, "Entrada invalida: descricao nao cadastrado.");
    this.descricoes.add(descricao);

    Map<Integer, Item> itensUsuario = this.itens.getOrDefault(doador, new HashMap<>());
    Item item = new Item(this.cont, descricao, quantidade, tags, doador);
    Item itemReal = itensUsuario.values().stream().filter(i -> i.equals(item)).findFirst().orElse(item);
    itemReal.setQuantidade(quantidade);

    itensUsuario.put(itemReal.getId(), itemReal);
    this.itens.put(doador, itensUsuario);
    this.cont += 1;
    return itemReal.getId();
  }

  /**
   * Mostra um determinado item de um doador especifico
   *
   * @param doador
   * @param id
   * @return representacao textual do item
   */
  public String exibeItem(Usuario doador, int id) {
    Item item = this.getItem(doador, id);
    return item.toString();
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
    Item item = this.getItem(doador, id);

    if (quantidade > 0) {
      item.setQuantidade(quantidade);
    }
    if (tags != null && !tags.isEmpty()) {
      item.setTags(tags);
    }

    return item.toString();
  }

  /**
   * Remove um item de um doador especifico
   *
   * @param doador
   * @param id
   */
  public void removeItemParaDoacao(Usuario doador, int id) {
    this.getItem(doador, id);

    this.itens.get(doador).remove(id);
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

  private Item getItem(Usuario doador, int id) {
    Validador validador = new Validador();
    validador.verificaInteiroNegativo(id, "Entrada invalida: id do item nao pode ser negativo.");
    validador.verificaNaoContem(doador, this.itens, "O Usuario nao possui itens cadastrados.");

    Map<Integer, Item> itensUsuario = this.itens.get(doador);

    validador.verificaNaoContem(id, itensUsuario, "Item nao encontrado: " + id + ".");

    return itensUsuario.get(id);
  }
}
