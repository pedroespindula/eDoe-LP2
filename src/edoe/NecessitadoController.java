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

  public String listaTodos() {
    return this.listaTodos("Receptor");
  }
}
