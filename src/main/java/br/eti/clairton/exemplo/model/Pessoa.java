package br.eti.clairton.exemplo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.eti.clairton.repository.Model;

@Entity
@Table(name = "pessoas")
public class Pessoa extends Model {
	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String sobrenome;

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
}
