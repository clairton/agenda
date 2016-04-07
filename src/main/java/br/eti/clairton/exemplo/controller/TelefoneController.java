package br.eti.clairton.exemplo.controller;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.eti.clairton.exemplo.model.Telefone;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.vraptor.QueryParser;

@Controller
@Path("telefones")
public class TelefoneController  extends AbstractController{
	
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
