package br.eti.clairton.exemplo.controller;

import org.junit.Test;

import br.com.caelum.vraptor.test.VRaptorIntegration;
import br.com.caelum.vraptor.test.VRaptorTestResult;

public class PessoaControllerIntegrationTest extends VRaptorIntegration{

	@Test
	public void testIndex() throws Exception {
        VRaptorTestResult result = navigate().get("/pessoa?nome=Clairton").execute();
	}

}
