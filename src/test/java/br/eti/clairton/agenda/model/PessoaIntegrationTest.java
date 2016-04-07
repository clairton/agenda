package br.eti.clairton.agenda.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.eti.clairton.agenda.FixtureRule;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.repository.Repository;


@RunWith(CdiTestRunner.class)
public class PessoaIntegrationTest {
	
	public static @ClassRule TestRule rule = new FixtureRule();

	private @Inject Repository repository;
	
	@Test
	public void testCount() {
		assertEquals(Long.valueOf(5l), repository.from(Pessoa.class).count());
	}
	
	
	@Test
	public void testCountTelefones() {
		assertEquals(2, repository.byId(Pessoa.class, 1l).getTelefones().size());
	}
}
