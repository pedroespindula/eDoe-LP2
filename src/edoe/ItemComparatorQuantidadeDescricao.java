package edoe;

import java.util.Comparator;

public class ItemComparatorQuantidadeDescricao implements Comparator<Item> {

  @Override
  public int compare(Item o1, Item o2) {
    if (o1.getQuantidade() != o2.getQuantidade()) {
      return o2.getQuantidade() - o1.getQuantidade();
    }

    return o1.getDescricao().compareTo(o2.getDescricao());
  }

}
