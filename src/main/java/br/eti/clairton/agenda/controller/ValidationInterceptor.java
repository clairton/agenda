package br.eti.clairton.agenda.controller;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.com.caelum.vraptor.Result;

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
		} catch (final ConstraintViolationException e) {
			final Map<String, List<String>> errors = new HashMap<String, List<String>>();
			for (final ConstraintViolation<?> violation : e.getConstraintViolations()) {
				final String key = violation.getPropertyPath().toString().replaceAll("\\[\\]", "");
				if (!errors.containsKey(key)) {
					errors.put(key, new ArrayList<String>());
				}
				errors.get(key).add(violation.getMessage());
			}
			result.use(http()).setStatusCode(422);
			result.use(json()).from(errors, "errors").serialize();
			return null;
		}
    }
	
}
