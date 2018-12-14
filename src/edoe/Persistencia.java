package edoe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {
  public void salvar(Object salvavel, String caminho) throws IOException {
    FileOutputStream arquivoSaida = new FileOutputStream(caminho);
    ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
    saida.writeObject(salvavel);
    saida.close();
    arquivoSaida.close();

  }

  public Object iniciar(String caminho) throws IOException, ClassNotFoundException {
    FileInputStream arquivoEntrada = new FileInputStream(caminho);
    ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada);
    Object objetoRetorno = entrada.readObject();
    entrada.close();
    arquivoEntrada.close();
    return objetoRetorno;
  }
}
