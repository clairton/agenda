package br.eti.clairton.exemplo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.eti.clairton.repository.Model;

@Entity
@Table(name = "telefones")
public class Telefone extends Model {
	private static final long serialVersionUID = 1L;

	private Integer prefixo;
	
	private Integer numero;

	@Deprecated
	public Telefone() {
		this(null, null);
	}
	
	public Telefone(final Integer prefixo, final Integer numero) {
		this.prefixo = prefixo;
		this.numero = numero;
	}
	
	public Integer getPrefixo() {
		return prefixo;
	}
	
	public Integer getNumero() {
		return numero;
	}
}
