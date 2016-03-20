package br.eti.clairton.bootstrap;

import br.eti.clairton.repository.TenantValue;

public class Tenant implements TenantValue<String>{

	@Override
	public String get() {
		return "";
	}

}
