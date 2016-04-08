package br.eti.clairton.agenda.controller;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.serialization.gson.WithRoot;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.vraptor.QueryParser;

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
	@Path({ "", "/" })
	@Consumes(value = "application/json", options = WithRoot.class)
	public void create(Pessoa pessoa) {
		logger.debug("Salvando pessoa{}", pessoa);
		pessoa = repository.save(pessoa);
		final Serializer serializer = result.use(json()).from(pessoa);
		serializer.serialize();
	}

	@Put
	@Path({ "", "/" })
	@Consumes(value = "application/json", options = WithRoot.class)
	public void update(Pessoa pessoa) {
		logger.debug("Salvando pessoa{}", pessoa);
		pessoa = repository.save(pessoa);
		final Serializer serializer = result.use(json()).from(pessoa);
		serializer.serialize();
	}

	@Delete
	@Path({ "/{id}" })
	public void remove(Long id) {
		logger.debug("Removendo pessoa id {}", id);
		repository.remove(repository.byId(Pessoa.class, id));
		result.use(http()).setStatusCode(204);
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
