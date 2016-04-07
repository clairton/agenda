package br.eti.clairton.agenda.serializer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.agenda.model.Telefone;
import br.eti.clairton.agenda.serializer.ModelSerializer;
import br.eti.clairton.agenda.serializer.PessoaSerializer;
import br.eti.clairton.repository.Model;
import net.vidageek.mirror.dsl.Mirror;

public class PessoaSerializerTest {
	private final EntityManager em = mock(EntityManager.class);
	private Gson gson;

	@Before
	public void init() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeHierarchyAdapter(Model.class, new ModelSerializer(em));
		builder.registerTypeAdapter(Pessoa.class, new PessoaSerializer(em) {
			private static final long serialVersionUID = 1L;

			@Override
			@SuppressWarnings("unchecked")
			protected <W> Collection<W> toMany(final JsonDeserializationContext context, final Field field, final JsonElement element) {
				return (Collection<W>) Arrays.asList(new Telefone(49, 55));

			}
		});
		gson = builder.create();
	}

	@Test
	public void testSerialiaze() {
		Pessoa pessoa = new Pessoa("João", "Silva");
		Telefone telefone = new Telefone(48, 88888888);
		new Mirror().on(telefone).set().field("id").withValue(2l);
		pessoa.adicionar(telefone);
		final String result = gson.toJson(pessoa);
		assertEquals("{\"nome\":\"João\",\"sobrenome\":\"Silva\",\"telefones\":[2]}", result);
	}

	@Test
	public void testDeserialiaze() {
		String json = "{nome:'João',sobrenome:'Silva',telefones:[{prefixo: 49, numero: 55}]}";
		final Pessoa pessoa = gson.fromJson(json, Pessoa.class);
		assertEquals("João", pessoa.getNome());
		assertEquals("Silva", pessoa.getSobrenome());
		assertEquals(1, pessoa.getTelefones().size());
	}

}
