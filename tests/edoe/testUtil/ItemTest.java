package edoe.testUtil;

import edoe.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ItemTest {
  ArrayList<Item> item;

  @BeforeEach
  public void createItem() {
    this.item = new ArrayList<Item>();
    Item item = new Item (1, "camisa", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.item.add(item);
    item = new Item (2, "camisa", 150, "bonita, cheirosa, arrumada", new UsuarioTeste());
    
  }
}
