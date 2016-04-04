package br.eti.clairton.exemplo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.eti.clairton.repository.Model;

@Entity
@Table(name = "telefones")
public class Telefone extends Model {
	private static final long serialVersionUID = 1L;

	private String prefixo;
	
	private String numero;

	@Deprecated
	public Telefone() {
		this(null, null);
	}
	
	public Telefone(final String prefixo, final String numero) {
		this.prefixo = prefixo;
		this.numero = numero;
	}
	
	public String getPrefixo() {
		return prefixo;
	}
	
	public String getNumero() {
		return numero;
	}
}
