package edoe;

import java.util.Comparator;

/**
 * Comparator que compara um item primeiro pela sua quantidade e depois pela descricao.
 * @author Anderson Felipe - 118111107
 */
public class ItemComparatorQuantidadeDescricao implements Comparator<Item> {

  @Override
  public int compare(Item o1, Item o2) {
    if (o1.getQuantidade() != o2.getQuantidade()) {
      return o2.getQuantidade() - o1.getQuantidade();
    }

    return o1.getDescricao().compareTo(o2.getDescricao());
  }

}
