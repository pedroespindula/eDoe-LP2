package edoe;

import edoe.Item;
import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ItemTest {
  ArrayList<Item> item;

  @BeforeEach
  public void createItem() {
    this.item = new ArrayList<Item>();
    Item item = new Item(1, "camisa", 100, "mulambenta, feia, velha", new UsuarioTeste());
    this.item.add(item);
    item = new Item(2, "camisa", 150, "bonita, cheirosa, arrumada", new UsuarioTeste());
    this.item.add(item);
    item = new Item(3, "sapato", 12, "estranho, fedido, acabado", new UsuarioTeste());
    this.item.add(item);
    item = new Item(4, "sapato", 150, "elegante, bonito, engraxado", new UsuarioTeste());
    this.item.add(item);
  }

  @Test
  public void getIdTest() {
    assertEquals(1, this.item.get(0).getId());
    assertEquals(2, this.item.get(1).getId());
    assertEquals(3, this.item.get(2).getId());
    assertEquals(4, this.item.get(3).getId());
  }

  @Test
  public void getDescricaoTest() {
    assertEquals("camisa", this.item.get(0).getDescricao());
    assertEquals("camisa", this.item.get(1).getDescricao());
    assertEquals("sapato", this.item.get(2).getDescricao());
    assertEquals("sapato", this.item.get(3).getDescricao());
  }

  @Test
  public void getQuantidadeTest() {
    assertEquals(100, this.item.get(0).getQuantidade());
    assertEquals(150, this.item.get(1).getQuantidade());
    assertEquals(12, this.item.get(2).getQuantidade());
    assertEquals(150, this.item.get(3).getQuantidade());
  }

  @Test
  public void setQuantidadeTest() {
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
  public void setTags() {
    this.item.get(0).setTags("Esquisita, Grande");
    this.item.get(1).setTags("Arrumada, Caprichada");
    this.item.get(2).setTags("42, Feio");
    this.item.get(3).setTags("35, Bonito");
  }

  @Test
  public void toStringTest() {
    String esperado = "1 - camisa, tags: [Esquisita,  Grande], quantidade: 100";
    assertEquals(esperado, this.item.get(0).toString());

    esperado = "2 - camisa, tags: [Arrumada,  Caprichada], quantidade: 150";
    assertEquals(esperado, this.item.get(1).toString());

    esperado = "3 - sapato, tags: [42,  Feio], quantidade: 12";
    assertEquals(esperado, this.item.get(2).toString());

    esperado = "4 - sapato, tags: [35,  Bonito], quantidade: 150";
    assertEquals(esperado, this.item.get(3).toString());

  }

  @Test
  public void matchTest() {
    int MATCH = 20;
    int MATCH_POS_IGUAL = 10;
    int MATCH_POS_DIF = 5;

    Item item1 = new Item(0, "sapato", 5, "infantil,colorido", new UsuarioTeste());

    Item item2 = new Item(1, "sapato", 5, "infantil", new UsuarioTeste("54321"));
    Item item3 = new Item(2, "sapato", 5, "colorido", new UsuarioTeste("54321"));
    Item item4 = new Item(3, "sapato", 5, "colorido,infantil", new UsuarioTeste("54321"));
    Item item5 = new Item(4, "sapato", 5, "infantil,colorido", new UsuarioTeste("54321"));
    Item item6 = new Item(5, "sapato", 5, "tag1,tag2,tag3,colorido", new UsuarioTeste("54321"));
    Item item7 = new Item(6, "sapato", 5, "couro,adulto", new UsuarioTeste("54321"));
    Item item8 = new Item(7, "bolsa", 5, "infantil,colorido", new UsuarioTeste("54321"));

    assertEquals(MATCH + MATCH_POS_IGUAL, item1.match(item2));
    assertEquals(MATCH + MATCH_POS_DIF, item1.match(item3));
    assertEquals(MATCH + (MATCH_POS_DIF * 2), item1.match(item4));
    assertEquals(MATCH + (MATCH_POS_IGUAL * 2), item1.match(item5));
    assertEquals(MATCH + (MATCH_POS_DIF), item1.match(item6));
    assertEquals(MATCH, item1.match(item7));
    assertEquals(0, item1.match(item8));
  }
}
