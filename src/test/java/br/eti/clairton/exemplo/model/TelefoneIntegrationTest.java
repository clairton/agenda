package br.eti.clairton.exemplo.model;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.eti.clairton.exemplo.FixtureRule;
import br.eti.clairton.repository.Repository;


@RunWith(CdiTestRunner.class)
public class TelefoneIntegrationTest {
	
	public static @ClassRule TestRule rule = new FixtureRule();

	private @Inject Repository repository;
	
	@Test
	public void testCount() {
		assertEquals(Long.valueOf(3l), repository.from(Telefone.class).count());
	}
}
