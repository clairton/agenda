package br.eti.clairton.bootstrap;

import static br.eti.clairton.inflector.Inflector.getForLocale;
import static br.eti.clairton.inflector.Locale.pt_BR;
import static javax.persistence.Persistence.createEntityManagerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.naming.InitialContext;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.eti.clairton.inflector.Inflector;
import br.eti.clairton.inflector.Language;
import br.eti.clairton.migrator.Config;
import br.eti.clairton.repository.AttributeBuilder;
import net.vidageek.mirror.dsl.Mirror;

/**
 * Produz os recursos.
 * 
 * @author Clairton Rodrigo Heinzen<clairton.rodrigo@gmail.com>
 */
@ApplicationScoped
public class Resource {
	public static final String UNIT_NAME = "bootstrap";

	public static final String ENVIROMENT_PARAM = "br.com.caelum.vraptor.environment";

	private final Mirror mirror = new Mirror();

	@PersistenceUnit(unitName = UNIT_NAME)
	private EntityManagerFactory emf;
	
	@PostConstruct
	public void init(){
		//for tests
		if(emf == null){
			emf = createEntityManagerFactory(UNIT_NAME);
		}
	}

	@Produces
	@RequestScoped
	public EntityManager getEm() {
		return emf.createEntityManager();
	}

	@Produces
	public Metamodel getMetamodel(final @Default EntityManager em) {
		return em.getMetamodel();
	}

	@Produces
	public Cache getCache() {
		return emf.getCache();
	}
	
	@Produces
	public Config getConfig() {
		return new Config("datasets");
	}

	@Produces
	public EntityManagerFactory getEmf() {
		return emf;
	}

	@Produces
	public Connection getConnection() {
		try {
			final InitialContext context = new InitialContext();
			final String jndi = "java:/jdbc/datasources/BootstrapDS";
			final DataSource dataSource = (DataSource) context.lookup(jndi);
			return dataSource.getConnection();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Produces
	public Logger produceLogger(final InjectionPoint injectionPoint) {
		final Class<?> type = injectionPoint.getMember().getDeclaringClass();
		final String klass = type.getName();
		final Logger logger = LogManager.getLogger(klass);
		return logger;
	}

	@Produces
	public Mirror getMirror() {
		return mirror;
	}

	@Produces
	public Inflector getForLanguage(final InjectionPoint ip) {
		final String language;
		if (ip.getAnnotated().isAnnotationPresent(Language.class)) {
			language = ip.getAnnotated().getAnnotation(Language.class).value();
		} else {
			language = pt_BR;
		}
		final Inflector inflector = getForLocale(language);
		return inflector;
	}

	@Produces
	public AttributeBuilder getAttributeBuilder(final @Default EntityManager em) {
		return new AttributeBuilder(em);
	}


	public void dispose(final @Disposes @Default Connection connection) {
		try{
			if (!connection.isClosed()) {
				connection.close();
			}			
		}catch(SQLException e){ }
	}

	public void dispose(final @Disposes @Default EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
	
	public void dispose(final @Disposes @Default EntityManagerFactory factory) {
		if (factory.isOpen()) {
			factory.close();
		}
	}
}
