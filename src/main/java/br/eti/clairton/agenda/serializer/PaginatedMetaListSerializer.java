package br.eti.clairton.agenda.serializer;

import static br.com.caelum.vraptor.serialization.gson.RegisterType.INHERITANCE;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.com.caelum.vraptor.serialization.gson.RegisterStrategy;
import br.eti.clairton.paginated.collection.Meta;
import br.eti.clairton.paginated.collection.PaginatedMetaList;

@RegisterStrategy(INHERITANCE)
public class PaginatedMetaListSerializer implements JsonSerializer<PaginatedMetaList<?>>{

	@Override
	public JsonElement serialize(final PaginatedMetaList<?> src, final Type type, final JsonSerializationContext context) {
		final JsonObject json = new JsonObject();
		final JsonArray collection = new JsonArray();
		for (final Object object : src) {
			final JsonElement element = context.serialize(object);
			collection.add(element);
		}
		final JsonArray links = new JsonArray();
		JsonObject index = new JsonObject();
		index.add("rel", new JsonPrimitive("index"));
		links.add(index);
		json.add("pessoas", collection);
		json.add("links", links);
		final Meta meta = src.getMeta();
		json.add("meta", context.serialize(meta));
		return json;
	}
}
