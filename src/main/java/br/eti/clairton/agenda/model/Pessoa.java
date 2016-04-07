package br.eti.clairton.agenda.model;

import static java.util.Collections.unmodifiableCollection;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.eti.clairton.repository.Model;

@Entity
@Table(name = "pessoas")
public class Pessoa extends Model {
	private static final long serialVersionUID = 1L;

	private String nome;

	private String sobrenome;

	@OneToMany(fetch = EAGER, cascade = ALL)
	@JoinTable(name = "pessoas_telefones", joinColumns = @JoinColumn(name = "pessoa_id"), inverseJoinColumns = @JoinColumn(name = "telefone_id"))
	private Collection<Telefone> telefones = new ArrayList<>();

	@Deprecated
	public Pessoa() {
		this(null);
	}

	public Pessoa(final String nome) {
		this(nome, null);
	}

	public Pessoa(final String nome, final String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	
	public Collection<Telefone> getTelefones() {
		return unmodifiableCollection(telefones);
	}

	public void adicionar(final Telefone telefone) {
		telefones.add(telefone);
	}
}
