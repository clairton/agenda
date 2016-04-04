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
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.eti.clairton.exemplo.model.Telefone;
import br.eti.clairton.inflector.Inflector;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedCollection;
import br.eti.clairton.repository.Order;
import br.eti.clairton.repository.Predicate;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.vraptor.Page;
import br.eti.clairton.repository.vraptor.QueryParser;

@Controller
@Path("pessoa")
public class TelefoneController  {
	private static final Logger logger = getLogger(TelefoneController.class);
	private final QueryParser queryParser;
	private final ServletRequest request;
	private final Repository repository;
	private final Result result;
	
	@Deprecated
	public TelefoneController() {
		this(null, null, null, null, null);
	}

	@Inject
	public TelefoneController(
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
	@Path({ "", "/" })
	public void index() {
		logger.debug("Recuperando telefones");
		final Page paginate = queryParser.paginate(request, Telefone.class);
		final Collection<Predicate> predicates = queryParser.parse(request, Telefone.class);
		repository.from(Telefone.class);
		repository.distinct();
		if (!predicates.isEmpty()) {
			repository.where(predicates);
		}
		final List<Order> orders = queryParser.order(request, Telefone.class);
		repository.orderBy(orders);
		final PaginatedCollection<Telefone, Meta> collection = repository.collection(paginate.offset, paginate.limit);
		final Serializer serializer = result.use(json()).from(collection, "telefones");
		serializer.serialize();
	}

	@Get
	@Path({ "/{id}" })
	public void show(Long id) {
		logger.debug("Recuperando telefone id {}", id);
		final Telefone telefone = repository.byId(Telefone.class, id);
		final Serializer serializer = result.use(json()).from(telefone);
		serializer.serialize();
	}
}
