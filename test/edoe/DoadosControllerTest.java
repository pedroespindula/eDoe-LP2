import static org.junit.jupiter.api.Assertions.assertEquals;

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
	

}
