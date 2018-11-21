package util;

import java.util.Map;

public class Validador {

    String msgGeral;

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
            throw new IllegalArgumentException(msgGeral + ": " + mensagem);
        }
    }

    public void verificaNulo(Object parametro, String msgGeral, String mensagem) {
        if (parametro == null) {
            throw new NullPointerException(msgGeral + ": " + mensagem);
        }
    }


    public void verificaStringNulaOuVazia(String parametro, String mensagem) {
        verificaNulo(parametro, mensagem);
        verificaStringVazia(parametro, mensagem);
    }

    public void verificaContem(Object chave, Map<Object, Object> mapa, String mensagem) {
        if (mapa.containsKey(chave)) {
            throw new IllegalArgumentException(msgGeral + mensagem);
        }
    }

    public void verificaNaoContem(Object chave, Map<Object, Object> mapa, String mensagem) {
        if (!mapa.containsKey(chave)) {
            throw new IllegalArgumentException(msgGeral + mensagem);
        }
    }

}
