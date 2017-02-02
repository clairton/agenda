package br.eti.clairton.agenda.serializer;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.eti.clairton.agenda.model.Pessoa;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedMetaList;

public class PaginatedMetaListSerializerTest {
	private final EntityManager em = mock(EntityManager.class);
	private Gson gson;

	@Before
	public void init() {
		final GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Pessoa.class, new PessoaSerializer(em));
		builder.registerTypeAdapter(PaginatedMetaList.class, new PaginatedMetaListSerializer());
		gson = builder.create();
	}

	@Test
	public void testSerialiaze() {
		Pessoa pessoa = new Pessoa("João", "Silva");
		PaginatedMetaList<Pessoa> list = new PaginatedMetaList<>(asList(pessoa), new Meta(10l, 1l));
		final String result = gson.toJson(list);
		assertEquals("{\"pessoas\":[{\"nome\":\"João\",\"sobrenome\":\"Silva\",\"telefones\":[]}],\"links\":[{\"rel\":\"index\"}],\"meta\":{\"total\":10,\"page\":1}}", result);
	}
}
