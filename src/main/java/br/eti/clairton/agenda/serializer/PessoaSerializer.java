package br.eti.clairton.agenda.serializer;

import static br.com.caelum.vraptor.serialization.gson.RegisterType.SINGLE;
import static br.eti.clairton.jpa.serializer.Operation.DESERIALIZE;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import br.com.caelum.vraptor.serialization.gson.RegisterStrategy;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.gson.hypermedia.HypermediableRule;
import br.eti.clairton.gson.hypermedia.HypermediableSerializer;
import br.eti.clairton.inflector.Inflector;

@RegisterStrategy(SINGLE)
public class PessoaSerializer extends HypermediableSerializer<Pessoa> implements JsonSerializer<Pessoa>, JsonDeserializer<Pessoa> {
	private static final long serialVersionUID = 1L;

	@Inject
	public PessoaSerializer(final HypermediableRule navigator, final EntityManager manager, final Inflector inflector) {
		super(navigator, manager, inflector);
		record("telefones", DESERIALIZE);
	}

	@Override
	public String getResource(final Pessoa src) {
		return "pessoa";
	}

	@Override
	public String getOperation(final Pessoa src) {
		return "index";
	}
}
