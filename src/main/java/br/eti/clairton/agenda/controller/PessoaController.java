package br.eti.clairton.agenda.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Patch;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.serialization.gson.WithRoot;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.http.QueryParser;

@Controller
@Path("pessoas")
public class PessoaController  extends AbstractController{
	private static final Logger logger = getLogger(PessoaController.class);
	private final Repository repository;
	private final Result result;
	
	@Deprecated
	public PessoaController() {
		this(null, null, null, null);
	}

	@Inject
	public PessoaController(
			final Repository repository, 
			final Result result, 
			final ServletRequest request, 
			final QueryParser queryParser) {
		super(repository, result, request, queryParser);
		this.repository = repository;
		this.result = result;
	}

	@Post
	@Validate
	@Path({ "", "/" })
	@Consumes(value = "application/json", options = WithRoot.class)
	public void create(final Pessoa pessoa) {
		save(pessoa);
	}

	@Put
	@Patch
	@Validate
	@Path("/{pessoa.id}")
	@Consumes(value = "application/json", options = WithRoot.class)
	public void update(final Pessoa pessoa) {
		save(pessoa);
	}
	
	protected void save(final Pessoa pessoa) {
		logger.debug("Salvando pessoa {}", pessoa);
		final Pessoa saved = repository.save(pessoa);
		final Serializer serializer = result.use(json()).from(saved);
		serializer.serialize();
	}

	@Override
	protected Class<?> type() {
		return Pessoa.class;
	}

	@Override
	protected String tag() {
		return "pessoas";
	}
}