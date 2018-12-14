package edoe;

import util.Validador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DoacaoController {

  private List<Doacao> doacoes;

  /**
   * Cria uma lista de doacoes que armazenara todas as doacoes feitas no sistema.
   */
  public DoacaoController() {
    this.doacoes = new ArrayList<>();
  }

  /**
   * Metodo utilizado para criar e armazenar uma doação no sitema. Ele criara essa doacao a partir de
   * dois itens (Um doado e um necessitado) e uma data. Ao ocorrer a doacao ha a alteracao da quantidade
   * do item. O metodo lancara excessoes se os objetos recebidos por ele forem vazios ou nulos.
   * @param itemNec O item necessitado.
   * @param itemDoado O item doado.
   * @param data A data que foi realizada a doacao.
   * @return A representacao da doacao.
   */
  public String realizaDoacao(Item itemNec, Item itemDoado, String data) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(data, "Entrada invalida: data nao pode ser vazia ou nula.");
    validador.verificaStringsDiferentes(itemNec.getDescricao(), itemDoado.getDescricao(), "Os itens nao tem descricoes iguais.");
    validador.verificaNulo(itemNec, "Entrada invalida: item necessitado nao pode ser nulo.");
    validador.verificaNulo(itemDoado, "Entrada invalida: item doado nao pode ser nulo.");

    int qntDoacao = getQntDoacao(itemNec, itemDoado);

    Doacao doacao = new Doacao(data, itemDoado.getUsuario(), itemDoado.getDescricao(), qntDoacao, itemNec.getUsuario());
    this.doacoes.add(doacao);

    itemNec.setQuantidade(itemNec.getQuantidade() - qntDoacao);
    itemDoado.setQuantidade(itemDoado.getQuantidade() - qntDoacao);

    return doacao.toString();
  }

  private int getQntDoacao(Item itemNec, Item itemDoado) {
    return itemDoado.getQuantidade() - itemNec.getQuantidade() >= 0 ? itemNec.getQuantidade() : itemDoado.getQuantidade();
  }

  /**
   * Lista todas as doacoes realizadas no sistema ordenadas por datas em ordem decrescente. Se as datas forem iguais
   * a comparacao é feita pelo descritor por ordem alfabetica.
   * @return A representacao de todas as doacoes realizadas no sistema separadas por ' | '.
   */
  public String listaDoacoes() {
    return doacoes.stream()
      .sorted(Comparator.comparing(Doacao::getData).reversed().thenComparing(Doacao::getDescricao))
      .map(Doacao::toString)
      .collect(Collectors.joining(" | "));
  }
}
