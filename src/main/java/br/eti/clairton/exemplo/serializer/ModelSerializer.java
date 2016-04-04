package br.eti.clairton.exemplo.serializer;

import static br.com.caelum.vraptor.serialization.gson.RegisterType.INHERITANCE;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import br.com.caelum.vraptor.serialization.gson.RegisterStrategy;
import br.eti.clairton.jpa.serializer.GsonJpaSerializer;
import br.eti.clairton.repository.Model;

@RegisterStrategy(INHERITANCE)
public class ModelSerializer extends GsonJpaSerializer<Model> implements JsonSerializer<Model>, JsonDeserializer<Model>{
	private static final long serialVersionUID = 1L;

	@Inject
	public ModelSerializer(final EntityManager manager) {
		super(manager);
	}
}
