package br.eti.clairton.agenda.serializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.Specializes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;


@Specializes
public class DateGsonConverter extends br.com.caelum.vraptor.serialization.gson.DateGsonConverter
	implements JsonDeserializer<Date>, JsonSerializer<Date>{
	private final DateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	private final DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		try {
			return super.deserialize(json, typeOfT, context);
		} catch (final JsonSyntaxException e) {
			try {
				return datetime.parse(json.getAsString());
			} catch (final ParseException e1) {
				try {
					return date.parse(json.getAsString());
				} catch (final ParseException e2) {					
					throw e;
				}
			}
		}
	}
	
	@Override
	public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(datetime.format(date));
	}
}