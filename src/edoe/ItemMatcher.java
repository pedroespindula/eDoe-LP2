package edoe;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemMatcher {
  private Item itemBase;

  public ItemMatcher(Item item) {
    this.itemBase = item;
  }

  public String match(List<Item> outros) {
    return outros.stream()
      .filter(i -> itemBase.match(i) > 0)
      .sorted(Comparator.comparingInt(itemBase::match).reversed().thenComparing(Item::getId))
      .map(Item::toStringComUsuario)
      .collect(Collectors.joining(" | "));
  }
}
