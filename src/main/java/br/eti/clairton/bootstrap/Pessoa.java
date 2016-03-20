package br.eti.clairton.bootstrap;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.eti.clairton.repository.Model;

@Entity
@Table(name = "pessoas")
public class Pessoa extends Model {
	private static final long serialVersionUID = 1L;

	private String nome;

	public Pessoa(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
