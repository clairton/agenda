package br.eti.clairton.agenda.serializer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;

import br.eti.clairton.gson.hypermedia.HypermediableRule;
import br.eti.clairton.gson.hypermedia.Link;

@Default
public class HypermediableRuleAgenda implements HypermediableRule {

	@Override
	public <T> Set<Link> from(final Collection<T> target, final String resource, final String operation) {
		return new HashSet<>();
	}

	@Override
	public <T> Set<Link> from(final T target, final String resource, final String operation) {
		return new HashSet<>();
	}

}
