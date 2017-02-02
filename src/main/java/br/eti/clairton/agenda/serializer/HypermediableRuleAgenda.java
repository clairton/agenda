package br.eti.clairton.agenda.serializer;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;

import br.eti.clairton.gson.hypermedia.HypermediableRule;
import br.eti.clairton.gson.hypermedia.Link;

@Default
public class HypermediableRuleAgenda implements HypermediableRule {

	private Set<Link> links = new HashSet<>(asList(
			new Link(null, "new", null, null, null),
			new Link(null, "show", null, null, null),
			new Link(null, "index", null, null, null),
			new Link(null, "create", null, null, null),
			new Link(null, "update", null, null, null),
			new Link(null, "edit", null, null, null),
			new Link(null, "remove", null, null, null)
	));

	@Override
	public <T> Set<Link> from(final Collection<T> target, final String resource, final String operation) {
		return links;
	}

	@Override
	public <T> Set<Link> from(final T target, final String resource, final String operation) {
		return links;
	}

}
