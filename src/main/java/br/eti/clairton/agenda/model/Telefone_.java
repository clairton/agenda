package br.eti.clairton.agenda.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.eti.clairton.repository.Model_;

@StaticMetamodel(Telefone.class)
public class Telefone_ extends Model_{
	public static volatile SingularAttribute<Telefone, Integer> prefixo;
	public static volatile SingularAttribute<Telefone, Integer> numero;
}
