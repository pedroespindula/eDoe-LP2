package util;

import edoe.Item;
import edoe.Usuario;

import java.util.Map;
import java.util.Set;

/**
 * Classe validador utilizada para validacao de parametros no sistema.
 */
public class Validador {
  private String msgGeral;

  /**
   * Cria um validador com uma mensagem geral vazia.
   */
  public Validador() {
    this.msgGeral = "";
  }

  /**
   * Cria um validador com uma mensagem geral passada como parametro.
   *
   * @param msgGeral A mensagem geral.
   */
  public Validador(String msgGeral) {
    this.msgGeral = msgGeral + ": ";
  }

  /**
   * Verifica se o parametro eh nulo.
   * Se for lanca um erro com a mensagem passada.
   *
   * @param parametro O parametro.
   * @param mensagem  A mensagem de erro.
   */
  public void verificaNulo(Object parametro, String mensagem) {
    if (parametro == null) {
      throw new NullPointerException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se a string eh vazia.
   * Se for lanca um erro com a mensagem passada.
   *
   * @param parametro O parametro verificado.
   * @param mensagem  A mensagem de erro.
   */
  public void verificaStringVazia(String parametro, String mensagem) {
    if (parametro.trim().isEmpty()) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se a string eh vazia ou nula.
   * Se for lanca um erro com a mensagem passada.
   *
   * @param parametro O parametro a ser verificado.
   * @param mensagem  A mensagem do erro.
   */
  public void verificaStringNulaOuVazia(String parametro, String mensagem) {
    verificaNulo(parametro, mensagem);
    verificaStringVazia(parametro, mensagem);
  }

  /**
   * Verifica se o parametro eh um inteiro negativo.
   * Se for, lanca um erro com a mensagem passada.
   *
   * @param parametro O inteiro a ser verificado.
   * @param mensagem  A mensagem do erro.
   */
  public void verificaInteiroNegativo(int parametro, String mensagem) {
    if (parametro < 0) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se o inteiro passado eh maior que zero.
   * Se for, lanca um erro com a mensagem passada.
   *
   * @param parametro O inteiro a ser verificado.
   * @param mensagem  A mensagem do erro.
   */
  public void verificaInteiroMaiorQueZero(int parametro, String mensagem) {
    if (parametro <= 0) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se um mapa contem a chave passada.
   * Se tiver, lanca um erro com a mensagem passada.
   *
   * @param chave    A chave.
   * @param mapa     O mapa.
   * @param mensagem A mensagem do erro.
   */
  public void verificaContem(Object chave, Map<?, ?> mapa, String mensagem) {
    if (mapa.containsKey(chave)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se nao contem a chave no mapa.
   * Se nao tiver, lanca um erro com a mensagem passada.
   *
   * @param chave    A chave.
   * @param mapa     O mapa.
   * @param mensagem A mensagem do erro.
   */
  public void verificaNaoContem(Object chave, Map<?, ?> mapa, String mensagem) {
    if (!mapa.containsKey(chave)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se nao contem a chave em um mapa de mapa.
   * Se nao tiver, lanca um erro com a mensagem passada.
   * @param chave
   * @param mapa
   * @param mensagem
   */
  public void verificaNaoContemMapaDeMapa(Object chave, Map<Usuario, Map<Integer, Item>> mapa, String mensagem){
    for (Map<?,?> o: mapa.values()){
      if (o.containsKey(chave)){
        return;
      }
    }
    throw new IllegalArgumentException(this.msgGeral + mensagem);

  }
  /**
   * Verifica se um objeto nao existe num Set.
   * Se nao existir, lanca um erro com a mensagem passada.
   *
   * @param obj      O objeto.
   * @param set      O set.
   * @param mensagem A mensagem do erro.
   */
  public void verificaContem(Object obj, Set<?> set, String mensagem) {
    if (set.contains(obj)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  /**
   * Verifica se um objeto existe num Set.
   * Se existir, lanca um erro com a mensagem passada.
   *
   * @param obj      O objeto.
   * @param set      O set.
   * @param mensagem A mensagem do erro.
   */
  public void verificaNaoContem(Object obj, Set<?> set, String mensagem) {
    if (!set.contains(obj)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }



}
