package br.eti.clairton.agenda.controller;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Interceptor
@Validate
public class ValidationInterceptor {
	private final Result result;
	
	@Deprecated
	public ValidationInterceptor() {
		this(null);
	}
	
	@Inject
	public ValidationInterceptor(final Result result) {
		this.result = result;
	}

	@AroundInvoke
    public Object forValidationFailed(final InvocationContext context) throws Exception {
		try{			
			return context.proceed();
		} catch (final Exception e) {
			result.use(Results.http()).setStatusCode(422);
			return null;
		}
    }
	
}
