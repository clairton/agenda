package br.eti.clairton.bootstrap;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.eti.clairton.repository.Repository;


@RunWith(CdiTestRunner.class)
public class PessoaIntegrationTest {
	
	public static @ClassRule TestRule rule = new FixtureRule();

	private @Inject Repository repository;
	
	@Test
	public void testGetNome() {
		assertEquals(Long.valueOf(3l), repository.from(Pessoa.class).count());
	}

}
