package edoe;

import static org.junit.jupiter.api.Assertions.*;

import edoe.testUtil.UsuarioTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.DoadosController;

class DoadosControllerTest {
	
	DoadosController doados;
	
	@BeforeEach
	void antesDeTudo() {
		doados = new DoadosController();
	}
	
	@Test
	void testaListaDescritoresVazio() {
		assertEquals(doados.listaDescritorDeItensParaDoacao(), "");
	}
	
	@Test
	void testaItensParaDoacaoVazio() {
		assertEquals(doados.listaItensParaDoacao(), "");
	}
	
	@Test
	void testaPesquisaItemParaDoacaoVazio() {
		assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("a"), "");
	}
	
	@Test
	void testaPesquisaItemParametroVazio() {
		try {
			doados.pesquisaItemParaDoacaoPorDescricao("       ");
			fail("DEVIA TER QUEBRADO");
		}catch(IllegalArgumentException o) {
			assert(true);
		}
	}
	

	@Test
	void testaPesquisaItemParametroNulo() {
		try {
			doados.pesquisaItemParaDoacaoPorDescricao(null);
			fail("DEVIA TER QUEBRADO");
		}catch(NullPointerException o) {
			assert(true);
		}
	}
	
	@Test
	void testaListaDescritoresUm() {
	    this.doados.adicionaDescritor("bola de tenis");
		assertEquals(doados.listaDescritorDeItensParaDoacao(), "0 - bola de tenis");
	}
	
	@Test
	void testaListaDescritoresDois() {
	    this.doados.adicionaDescritor("camisa");
	    this.doados.adicionaDescritor("bola de tenis");
		assertEquals(doados.listaDescritorDeItensParaDoacao(), "0 - bola de tenis | 0 - camisa");
	}
	
	@Test
	void testaListaDescritoresComItens() {
	    
		this.doados.adicionaDescritor("camisa");
	    this.doados.adicionaDescritor("bola de tenis");
	    
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(1), "camisa", 10, "M,BL");
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(1), "bola de tenis", 5, "M,BL");
	
		assertEquals(doados.listaDescritorDeItensParaDoacao(), "5 - bola de tenis | 10 - camisa");
	}
	
	@Test
	void testaItensParaDoacaoAlguns() {
		this.doados.adicionaDescritor("camisa");
	    this.doados.adicionaDescritor("bola de tenis");
	    
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(1), "camisa", 3, "M,BL");
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(2), "bola de tenis", 5, "M,BL");
		
		assertEquals(doados.listaItensParaDoacao(), "2 - bola de tenis, tags: [M, BL], quantidade: 5, doador: Teste/12345 | 1 - camisa, tags: [M, BL], quantidade: 3, doador: Teste/12345");
	}
	
	@Test
	void testaPesquisaItemParaDoacaoUnico() {
		this.doados.adicionaDescritor("camisa");
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(1), "camisa", 10, "M,BL");
		assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("   CAM        "), "1 - camisa, tags: [M, BL], quantidade: 10");
	}
	
	@Test
	void testaPesquisaItemParaDoacaoDois() {
		this.doados.adicionaDescritor("camisa");
		this.doados.adicionaDescritor("camisola");
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(2), "camisola", 1, "M,BL");
		this.doados.adicionaItemParaDoacao(new UsuarioTeste(1), "camisa", 3, "M,BL");
		assertEquals(doados.pesquisaItemParaDoacaoPorDescricao("      CaM      "), "2 - camisa, tags: [M, BL], quantidade: 3 | 1 - camisola, tags: [M, BL], quantidade: 1");
	}
	
	
	

}
