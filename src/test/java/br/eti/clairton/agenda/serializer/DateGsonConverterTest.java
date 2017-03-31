package br.eti.clairton.agenda.serializer;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class DateGsonConverterTest {
	private final DateGsonConverter serializer = new DateGsonConverter();
	private final DateFormat datetime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private final DateFormat date = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testDateTime() throws Exception {
		final String data = "15/12/2012 13:59";
		final Date object = serializer.deserialize(new JsonPrimitive(data), Date.class, null);
		assertEquals(datetime.parse(data), object);
	}

	@Test
	public void testDate() throws Exception {
		final String data = "15/12/2012";
		final Date object = serializer.deserialize(new JsonPrimitive(data), Date.class, null);
		assertEquals(date.parse(data), object);
	}
	


	@Test
	public void testSerializer() throws Exception {
		final Date data = new Date();
		JsonElement object = serializer.serialize(data, Date.class, null);
		assertEquals(datetime.format(data), object.getAsString());
	}

}
