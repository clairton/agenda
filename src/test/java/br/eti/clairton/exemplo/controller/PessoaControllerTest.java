package br.eti.clairton.exemplo.controller;

import static br.eti.clairton.inflector.Inflector.getForLocale;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import br.com.caelum.vraptor.test.VRaptorIntegration;
import br.com.caelum.vraptor.util.test.MockSerializationResult;
import br.eti.clairton.inflector.Inflector;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.vraptor.QueryParser;

public class PessoaControllerTest extends VRaptorIntegration{
    private MockSerializationResult result = new MockSerializationResult();
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private PessoaController controller;
    private Repository repository;
    private Inflector inflector = getForLocale("pt-BR");
    private QueryParser queryParser;
	
	
	@Test
	public void testIndex() {
		controller = new PessoaController(repository, result, inflector, request, queryParser);
	}

}
