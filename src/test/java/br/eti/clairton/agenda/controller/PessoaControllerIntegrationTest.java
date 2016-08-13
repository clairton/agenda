package br.eti.clairton.agenda.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.vraptor.test.VRaptorIntegration;
import br.com.caelum.vraptor.test.VRaptorTestResult;

public class PessoaControllerIntegrationTest extends VRaptorIntegration {

	@Test
	public void testIndex() {
		VRaptorTestResult result = navigate().get("/pessoas?nome=Clairton").execute();
		assertEquals(200, result.getResponse().getStatus());
	}

}
