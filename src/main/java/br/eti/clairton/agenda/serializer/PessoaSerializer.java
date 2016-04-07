package br.eti.clairton.agenda.serializer;

import static br.com.caelum.vraptor.serialization.gson.RegisterType.SINGLE;
import static br.eti.clairton.jpa.serializer.Operation.DESERIALIZE;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import br.com.caelum.vraptor.serialization.gson.RegisterStrategy;
import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.jpa.serializer.GsonJpaSerializer;

@RegisterStrategy(SINGLE)
public class PessoaSerializer extends GsonJpaSerializer<Pessoa> implements JsonSerializer<Pessoa>, JsonDeserializer<Pessoa> {
	private static final long serialVersionUID = 1L;

	@Inject
	public PessoaSerializer(final EntityManager manager) {
		super(manager);
		record("telefone", DESERIALIZE);
	}
}
