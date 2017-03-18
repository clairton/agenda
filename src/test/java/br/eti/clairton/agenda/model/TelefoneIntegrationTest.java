package br.eti.clairton.agenda.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.eti.clairton.agenda.FixtureRule;
import br.eti.clairton.agenda.model.Telefone;
import br.eti.clairton.repository.Repository;


@RunWith(CdiTestRunner.class)
public class TelefoneIntegrationTest {
	
	public static @ClassRule TestRule rule = new FixtureRule();

	private @Inject Repository repository;
	
	@Test
	public void testCount() {
		assertEquals(Long.valueOf(10l), repository.from(Telefone.class).count());
	}
}
