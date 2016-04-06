package br.eti.clairton.exemplo.controller;

import static br.com.caelum.vraptor.view.Results.json;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

import org.apache.logging.log4j.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
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
@Path("pessoas")
public class PessoaController  {
	private static final Logger logger = getLogger(PessoaController.class);
	private final QueryParser queryParser;
	private final ServletRequest request;
	private final Repository repository;
	private final Result result;
	
	@Deprecated
	public PessoaController() {
		this(null, null, null, null, null);
	}

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
	@Path({ "", "/" })
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
		final Serializer serializer = result.use(json()).from(collection, "pessoas");
		serializer.serialize();
	}

	@Get
	@Path({ "/{id}" })
	public void show(Long id) {
		logger.debug("Recuperando pessoa id {}", id);
		final Pessoa pessoa = repository.byId(Pessoa.class, id);
		final Serializer serializer = result.use(json()).from(pessoa);
		serializer.serialize();
	}

	@Post
	@Path({ "", "/" })
	public void create(Pessoa pessoa) {
		logger.debug("Salvando pessoa{}", pessoa);
		pessoa = repository.save(pessoa);
		final Serializer serializer = result.use(json()).from(pessoa);
		serializer.serialize();
	}

	@Put
	@Path({ "", "/" })
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
	}
}
