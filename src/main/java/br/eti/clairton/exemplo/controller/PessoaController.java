package br.eti.clairton.exemplo.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.eti.clairton.exemplo.model.Pessoa;
import br.eti.clairton.inflector.Inflector;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedCollection;
import br.eti.clairton.repository.Order;
import br.eti.clairton.repository.Predicate;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.vraptor.Page;
import br.eti.clairton.repository.vraptor.QueryParser;

@Controller
public class PessoaController  {
	private static final Logger logger = getLogger(PessoaController.class);
	private QueryParser queryParser;
	private ServletRequest request;
	private Repository repository;
	private Result result;

	@Inject
	public PessoaController(
			Repository repository, 
			Result result, 
			Inflector inflector,
			ServletRequest request, 
			QueryParser queryParser) {
		this.queryParser = queryParser;
		this.request = request;
		this.repository = repository;
		this.result = result;
	}
	
	
	@Get
	public void index() {
		logger.debug("Recuperando pessoas");
		final Page paginate = queryParser.paginate(request, Pessoa.class);
		final Collection<Predicate> predicates = queryParser.parse(request, Pessoa.class);
		repository.from(Pessoa.class);
		repository.distinct();
		if (!predicates.isEmpty()) {
			repository.where(predicates);
		}
		final List<Order> orders = queryParser.order(request, Pessoa.class);
		repository.orderBy(orders);
		final PaginatedCollection<Pessoa, Meta> collection = repository.collection(paginate.offset, paginate.limit);
		
		
		final Serializer serializer = result.use(json()).from(collection);
		serializer.serialize();
	}

}
