package edoe;

import util.Validador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DoacaoController {

  private List<Doacao> doacoes;

  public DoacaoController() {
    this.doacoes = new ArrayList<>();
  }

  public String realizaDoacao(Item itemNec, Item itemDoado, String data) {
    Validador validador = new Validador();

    validador.verificaStringNulaOuVazia(data, "Entrada invalida: data nao pode ser vazia ou nula.");
    validador.verificaStringsDiferentes(itemNec.getDescricao(), itemDoado.getDescricao(), "Os itens nao tem descricoes iguais.");

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

  public String listaDoacoes() {
    return doacoes.stream()
      .sorted(Comparator.comparing(Doacao::getData).reversed().thenComparing(Doacao::getDescricao))
      .map(Doacao::toString)
      .collect(Collectors.joining(" | "));
  }
}
