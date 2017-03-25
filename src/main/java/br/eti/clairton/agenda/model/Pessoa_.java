package br.eti.clairton.agenda.model;

import java.util.Date;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.eti.clairton.repository.Model_;

@StaticMetamodel(Pessoa.class)
public class Pessoa_ extends Model_ {
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> sobrenome;
	public static volatile SingularAttribute<Pessoa, Date> nascidoEm;
	public static volatile CollectionAttribute<Pessoa, Telefone> telefones;
}
