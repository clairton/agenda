package br.eti.clairton.agenda.model;

import static java.util.Collections.unmodifiableCollection;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.DATE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.eti.clairton.identificator.Identificator;
import br.eti.clairton.repository.Model;

@Entity
@Table(name = "pessoas")
public class Pessoa extends Model {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 200)
	@Identificator
	private String nome;

	@Identificator
	private String sobrenome;

	@Identificator
	@Temporal(DATE)
	@Column(name = "nascido_em")
	private Date nascidoEm;

	@OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true)
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
	
	public Date getNascidoEm() {
		return nascidoEm;
	}
	
	public Collection<Telefone> getTelefones() {
		return unmodifiableCollection(telefones);
	}

	public void adicionar(final Telefone telefone) {
		telefones.add(telefone);
	}
	
	public void remover(final Telefone telefone) {
		telefones.remove(telefone);
	}
}
