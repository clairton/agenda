package br.eti.clairton.agenda.serializer;

import static com.google.common.io.Flushables.flushQuietly;
import static java.util.Collections.singletonMap;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.serialization.gson.Exclusions;
import br.com.caelum.vraptor.serialization.gson.GsonSerializer;
import br.com.caelum.vraptor.serialization.gson.GsonSerializerBuilder;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedCollection;

public class GsonSerializerHypermediable  extends GsonSerializer{

	private final GsonSerializerBuilder builder;
	private final Writer writer;

	public GsonSerializerHypermediable(
			final GsonSerializerBuilder builder, 
			final Writer writer, 
			final TypeNameExtractor extractor) {
		super(builder, writer, extractor);
		this.builder = builder;
		this.writer = writer;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void serialize() {
		builder.setExclusionStrategies(new Exclusions(builder.getSerializee()));
		Gson gson = builder.create();
		
		String alias = builder.getAlias();
		Object root = builder.getSerializee().getRoot();

		if (builder.isWithoutRoot()) {
			gson.toJson(root, writer);
		} else {
			final Map<String, Object> object;
			if(PaginatedCollection.class.isInstance(root)){
				object = new HashMap<>();
				object.put(alias, root);
				object.put("meta", ((PaginatedCollection<?, Meta>) root).unwrap(Meta.class));
				object.put("links", new ArrayList<>());
			} else {
				object = singletonMap(alias, root);
			}
			gson.toJson(object, writer);
		}
		
		flushQuietly(writer);
	}
}
