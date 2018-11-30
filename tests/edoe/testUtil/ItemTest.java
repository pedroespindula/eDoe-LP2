package edoe.testUtil;

import edoe.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ItemTest {
  ArrayList<Item> item;

  @BeforeEach
  public void createItem() {
    this.item = new ArrayList<Item>();
    Item item = new Item (1, "camisa", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.item.add(item);
    item = new Item (2, "camisa", 150, "bonita, cheirosa, arrumada", new UsuarioTeste());
    this.item.add(item);
    item = new Item (3, "sapato", 12, "estranho, fedido, acabado", new UsuarioTeste());
    this.item.add(item);
    item = new Item (4, "sapato", 150, "elegante, bonito, engraxado", new UsuarioTeste());
    this.item.add(item);
  }

  @Test
  public void getIdTest(){
    assertEquals(1, this.item.get(0).getId());
    assertEquals(2, this.item.get(1).getId());
    assertEquals(3, this.item.get(2).getId());
    assertEquals(4, this.item.get(3).getId());
  }

  @Test
  public void getDescricaoTest(){
    assertEquals("camisa", this.item.get(0).getDescricao());
    assertEquals("camisa", this.item.get(1).getDescricao());
    assertEquals("sapato", this.item.get(2).getDescricao());
    assertEquals("sapato", this.item.get(3).getDescricao());
  }

  @Test
  public void getQuantidadeTest(){
    assertEquals(100, this.item.get(0).getQuantidade());
    assertEquals(150, this.item.get(1).getQuantidade());
    assertEquals(12, this.item.get(2).getQuantidade());
    assertEquals(150, this.item.get(3).getQuantidade());
  }

  @Test
  public void setQuantidadeTest(){
    this.item.get(0).setQuantidade(20);
    this.item.get(1).setQuantidade(40);
    this.item.get(2).setQuantidade(100);
    this.item.get(3).setQuantidade(10);
    assertEquals(20, this.item.get(0).getQuantidade());
    assertEquals(40, this.item.get(1).getQuantidade());
    assertEquals(100, this.item.get(2).getQuantidade());
    assertEquals(10, this.item.get(3).getQuantidade());
  }

  @BeforeEach
  public void setTags(){
    this.item.get(0).setTags("Esquisita, Grande");
    this.item.get(1).setTags("Arrumada, Caprichada");
    this.item.get(2).setTags("42, Feio");
    this.item.get(3).setTags("35, Bonito");
  }

  @Test
  public void toStringTest(){
    String esperado = "1 - camisa, tags: [Esquisita,  Grande], quantidade: 100";
    assertEquals(esperado, this.item.get(0).toString());

    esperado = "2 - camisa, tags: [Arrumada,  Caprichada], quantidade: 150";
    assertEquals(esperado, this.item.get(1).toString());

    esperado = "3 - sapato, tags: [42,  Feio], quantidade: 12";
    assertEquals(esperado, this.item.get(2).toString());

    esperado = "4 - sapato, tags: [35,  Bonito], quantidade: 150";
    assertEquals(esperado, this.item.get(3).toString());

  }
}
