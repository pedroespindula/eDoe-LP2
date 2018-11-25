package edoe;

import java.io.FileNotFoundException;

public class EDoeFacade {

    private Mediator mediator;

    public EDoeFacade() {
        this.mediator = new Mediator();
    }

    // US1
    public String adicionaDoador (String id, String nome, String email, String celular, String classe) {
        return this.mediator.adicionaDoador(id, nome, email, celular, classe);
    }

    public String pesquisaUsuarioPorId (String id) {
        return this.mediator.pesquisaUsuarioPorId(id);
    }

    public String pesquisaUsuarioPorNome (String nome) {
        return this.mediator.pesquisaUsuarioPorNome(nome);
    }

    public String atualizaUsuario (String id, String nome, String email, String celular) {
        return this.mediator.atualizaUsuario(id, nome, email, celular);
    }

    public void removeUsuario (String id) {
        this.mediator.removeUsuario(id);
    }

    public void lerReceptores(String caminho) throws FileNotFoundException {
        this.mediator.lerReceptores(caminho);
    }

    // US2
    public void adicionaDescritor (String descricao) {

    }

    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags) {
        return "";
    }

    public String exibeItem (String id, String idDoador) {
        return "";
    }

    public String atualizaItemParaDoacao (String id, String idDoador, int quantidade, String tags) {
        return "";
    }

    public void removeItemParaDoacao (String id, String idDoador) {

    }

    // US3
    public String listaDescritorDeItensParaDoacao () {
        return "";
    }

    public  String listaItensParaDoacao () {
        return "";
    }

    public String pesquisaItemParaDoacaoPorDescricao (String desc) {
        return "";
    }

}
