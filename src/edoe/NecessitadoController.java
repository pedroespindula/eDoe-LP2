package edoe;

import util.Validador;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador de itens necessitados por usuarios receptores.
 * Armazena esses itens e permite sua manipulacao (CRUD).
 *
 * @author Bruno Siqueira - 118110854
 */
public class NecessitadoController extends ItemController {

  /**
   * Cria um novo NecessitadoController com itens vazios.
   */
  public NecessitadoController() {
    super();
  }

  /**
   * Lista todos os itens cadastrados neste sistema,
   * ordenados pelo id de cada item.
   * @return uma String contendo todos os itens no seguinte formato:
   * ex.: id1 - descrição, tags: [tag1, tag2, ...], quantidade: n, Receptor: Fulano | id2... | id3...
   */
  public String listaTodos() {
    return this.listaTodos(Comparator.comparingInt(Item::getId));
  }
}
