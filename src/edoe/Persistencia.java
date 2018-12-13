package edoe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Persistencia {
	public void salvar(Object salvavel, String caminho) {
		try {
		FileOutputStream arquivoSaida = new FileOutputStream(caminho);
		ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
		saida.writeObject(salvavel);
		saida.close();
		arquivoSaida.close();
		}catch(IOException e) {
			throw new RuntimeException("NAO FOI POSSIVEL SALVAR");
		}
	}

	public Object iniciar(String caminho){
		try {
			FileInputStream arquivoEntrada = new FileInputStream(caminho);
			ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada);
			Object objetoRetorno = entrada.readObject(); 
			entrada.close();
			arquivoEntrada.close();
			return objetoRetorno;
		}catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage());	
		}
	
	}

	public void iniciarSistema(Mediator mediator) {
		try {
			Map<String, Usuario> usuarios = (HashMap<String, Usuario>)this.iniciar("GuardaDados/Usuarios.txt");
			usuarios.values().stream()
					.forEach(usr -> mediator.adicionaDoador(usr.getId(), usr.getNome(), usr.getEmail(), usr.getTelefone(), usr.getClasse()));
			
			Set<String> descritoresDoados = (HashSet<String>)this.iniciar("GuardaDados/DescricoesDoados.txt");
			descritoresDoados.stream().forEach(desc -> mediator.adicionaDescritor(desc));
			
			Map<Usuario, Map<Integer, Item>> doados= (HashMap<Usuario, Map<Integer, Item>>)this.iniciar("GuardaDados/ItensDoados.txt");
			doados.values().stream()
					.flatMap(mapa -> mapa.values().stream())
					.forEach(item -> mediator.adicionaItemParaDoacao(item.getUsuario().getId(), item.getDescricao(), item.getQuantidade(), item.getTagsComoEntrada()));
			
			Map<Usuario, Map<Integer, Item>> necessitados = (HashMap<Usuario, Map<Integer, Item>>)this.iniciar("GuardaDados/ItensNecessitados.txt");
			necessitados.values().stream()
					.flatMap(mapa -> mapa.values().stream())
					.forEach(item -> mediator.adicionaItemNecessario(item.getUsuario().getId(), item.getDescricao(), item.getQuantidade(), item.getTagsComoEntrada()));
			
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}