package br.eti.clairton.agenda;

import static br.eti.clairton.migrator.Config.DROP;
import static br.eti.clairton.migrator.Config.POPULATE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.setProperty;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FixtureRule implements TestRule {
	private static Boolean first = TRUE;
	
	@Override
	public Statement apply(final Statement base, final Description description) {
		setProperty(DROP, first.toString());
		setProperty(POPULATE, first.toString());
		setProperty("jboss.server.log.dir", "target/logs");
		//somente deleta o banco e cria na primeira vez
		first = FALSE;
		return base;
	}
}