package br.eti.clairton.agenda.controller;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.Serializer;
import br.eti.clairton.agenda.model.Telefone;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedCollection;
import br.eti.clairton.repository.Order;
import br.eti.clairton.repository.Predicate;
import br.eti.clairton.repository.Repository;
import br.eti.clairton.repository.http.Page;
import br.eti.clairton.repository.http.QueryParser;

public abstract class AbstractController {
	private static final Logger logger = getLogger(AbstractController.class);
	private final QueryParser queryParser;
	private final ServletRequest request;
	private final Repository repository;
	private final Result result;

	@Inject
	public AbstractController(
			final Repository repository, 
			final Result result, 
			final ServletRequest request, 
			final QueryParser queryParser) {
		this.queryParser = queryParser;
		this.request = request;
		this.repository = repository;
		this.result = result;
	}

	@Delete
	@Path({ "/{id}" })
	public void remove(Long id) {
		logger.debug("Removendo {} id {}", tag(), id);
		repository.remove(type(), id);
		result.use(http()).setStatusCode(204);
	}


	@Get
	@Path({ "", "/" })
	public void index() {
		logger.debug("Recuperando telefones");
		final Page paginate = queryParser.paginate(request.getParameterMap(), type());
		final Collection<Predicate> predicates = queryParser.parse(request.getParameterMap(), type());
		repository.from(type());
		repository.distinct();
		if (!predicates.isEmpty()) {
			repository.where(predicates);
		}
		final List<Order> orders = queryParser.order(request.getParameterMap(), Telefone.class);
		repository.orderBy(orders);
		final PaginatedCollection<Telefone, Meta> collection = repository.collection(paginate.offset, paginate.limit);
		final Serializer serializer = result.use(json()).from(collection, tag());
		serializer.serialize();
	}


	@Get
	@Path({ "/{id}" })
	public void show(Long id) {
		logger.debug("Recuperando telefone id {}", id);
		final Object object = repository.byId(type(), id);
		final Serializer serializer = result.use(json()).from(object);
		serializer.serialize();
	}
	
	protected abstract Class<?> type();
	protected abstract String tag();
}
