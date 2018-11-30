package util;

import java.util.Map;
import java.util.Set;

public class Validador {
  private String msgGeral;

  public Validador() {
    this.msgGeral = "";
  }

  public Validador(String msgGeral) {
    this.msgGeral = msgGeral + ": ";
  }

  public void verificaNulo(Object parametro, String mensagem) {
    if (parametro == null) {
      throw new NullPointerException(this.msgGeral + mensagem);
    }
  }

  public void verificaStringVazia(String parametro, String mensagem) {
    if (parametro.trim().isEmpty()) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaStringVazia(String parametro, String msgGeral, String mensagem) {
    if (parametro.trim().isEmpty()) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaNulo(Object parametro, String msgGeral, String mensagem) {
    if (parametro == null) {
      throw new NullPointerException(this.msgGeral + mensagem);
    }
  }

  public void verificaStringNulaOuVazia(String parametro, String mensagem) {
    verificaNulo(parametro, mensagem);
    verificaStringVazia(parametro, mensagem);
  }

  public void verificaContem(Object chave, Map<?, ?> mapa, String mensagem) {
    if (mapa.containsKey(chave)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaNaoContem(Object chave, Map<?, ?> mapa, String mensagem) {
    if (!mapa.containsKey(chave)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaInteiroNegativo(int parametro, String mensagem) {
    if (parametro < 0) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaInteiroMaiorQueZero(int parametro, String mensagem) {
    if (parametro <= 0) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }

  public void verificaNaoContem(Object obj, Set<?> set, String mensagem) {
    if (!set.contains(obj)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }

  }

  public void verificaContem(Object obj, Set<?> set, String mensagem) {
    if (set.contains(obj)) {
      throw new IllegalArgumentException(this.msgGeral + mensagem);
    }
  }
}
