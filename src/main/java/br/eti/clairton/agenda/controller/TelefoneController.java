package br.eti.clairton.agenda.controller;

import static br.com.caelum.vraptor.view.Results.http;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.agenda.model.Pessoa_;
import br.eti.clairton.agenda.model.Telefone;
import br.eti.clairton.agenda.model.Telefone_;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.http.QueryParser;

@Controller
@Path("telefones")
public class TelefoneController  extends AbstractController{
	private static final Logger logger = getLogger(TelefoneController.class);
	private final Repository repository;
	private final Result result;
	
	@Deprecated
	public TelefoneController() {
		this(null, null, null, null);
	}

	@Inject
	public TelefoneController(
			final Repository repository, 
			final Result result, 
			final ServletRequest request, 
			final QueryParser queryParser) {
		super(repository, result, request, queryParser);
		this.repository = repository;
		this.result = result;
	}

	@Override
	protected void removeAndSerialize(Long id){
		logger.debug("Removendo {} id {}", tag(), id);
		Telefone telefone = repository.byId(Telefone.class, id);
		Pessoa pessoa = repository
							.from(Pessoa.class)
							.where(id, Pessoa_.telefones, Telefone_.id)
							.first();
		pessoa.remover(telefone);
		repository.save(pessoa);
		result.use(http()).setStatusCode(204);
	}
	
	@Override
	protected Class<?> type() {
		return Telefone.class;
	}

	@Override
	protected String tag() {
		return "telefones";
	}
}
